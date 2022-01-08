package com.bytecoders.recyclerviewbindinglib

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.bytecoders.recyclerviewbindinglib.diff.BindingAdapterDiffCallback
import com.bytecoders.recyclerviewbindinglib.layoutmanager.ArcLayoutManager
import com.bytecoders.recyclerviewbindinglib.touchhelper.DragConfiguration
import com.bytecoders.recyclerviewbindinglib.touchhelper.DragTouchHelper
import com.bytecoders.recyclerviewbindinglib.touchhelper.SwipeConfiguration
import com.bytecoders.recyclerviewbindinglib.touchhelper.SwipeTouchHelper
import com.bytecoders.recyclerviewbindinglib.viewholder.BindingViewHolder
import com.bytecoders.recyclerviewbindinglib.viewholder.DragHandleViewHolder
import com.bytecoders.recyclerviewbindinglib.viewholder.DragHandleViewHolderConfiguration
import com.bytecoders.recyclerviewbindinglib.viewholder.ExpandableViewHolder
import com.bytecoders.recyclerviewbindinglib.viewholder.ExpandableViewHolderConfiguration
import com.bytecoders.recyclerviewbindinglib.viewholder.StandardViewHolderConfiguration
import com.bytecoders.recyclerviewbindinglib.viewholder.TouchHelperProvider
import com.bytecoders.recyclerviewbindinglib.viewholder.ViewHolderConfiguration
import java.util.*
import kotlin.reflect.KClass

/**
 * Creates a mapping between a model Class and its
 * corresponding layout resource id
 */
typealias ClassLayoutMapping = Map<KClass<*>, Int>

/**
 * Base sealed class for all RecyclerView types available
 */
sealed class RecyclerViewType

/**
 * Layout items vertically
 */
object RecyclerViewVertical : RecyclerViewType()

/**
 * Layout items horizontally
 */
object RecyclerViewHorizontal : RecyclerViewType()

/**
 * Layout items in a Grid
 *
 * @param spanCount Number of items in the same line / row
 */
open class RecyclerViewGrid(val spanCount: Int) : RecyclerViewType()

/**
 * Layout items in a staggered grid formation. Vertical layout
 *
 * @param spanCount Number of items in the same line / row
 */
class RecyclerViewGridStaggeredVertical(spanCount: Int) : RecyclerViewGrid(spanCount)

/**
 * Layout items in a staggered grid formation. Horizontal layout
 *
 * @param spanCount Number of items in the same line / row
 */
class RecyclerViewGridStaggeredHorizontal(spanCount: Int) : RecyclerViewGrid(spanCount)

/**
 * Items are layout showing an Arc
 *
 * @param horizontalOffset initial offset for drawing items
 */
data class RecyclerViewCurved(val horizontalOffset: Int = 0) : RecyclerViewType()

/**
 * Defines how a item View is snapped in the RecyclerView and creates
 * the necessary model for [RecyclerView.bindModel] function
 */
enum class Snap {
    /**
     * Setup LinearSnapHelper
     */
    LINEAR,

    /**
     * Setup PagerSnapHelper
     */
    PAGER
}

/**
 * Model that configures how the RecyclerView should be presented
 *
 * @param layoutIds Map each model class to its layout
 * @param recyclerViewType Type of RecyclerView layout
 * @param viewHolderConfiguration Configuration for individual items
 * @param snap How items shall be snapped
 * @param swipeConfiguration Configuration for item swipe functionality
 * @param dragConfiguration Configuration for item dragging / reordering functionality
 */
class RecyclerViewConfiguration(
    val layoutIds: ClassLayoutMapping,
    private val recyclerViewType: RecyclerViewType,
    val viewHolderConfiguration: ViewHolderConfiguration,
    val snap: Snap? = null,
    val swipeConfiguration: SwipeConfiguration? = null,
    val dragConfiguration: DragConfiguration? = null
) {
    internal fun getLayoutManager(context: Context): RecyclerView.LayoutManager = when (recyclerViewType) {
        is RecyclerViewVertical -> LinearLayoutManager(context)
        is RecyclerViewHorizontal -> LinearLayoutManager(
            context,
            LinearLayoutManager.HORIZONTAL,
            false
        )
        is RecyclerViewGrid -> GridLayoutManager(context, recyclerViewType.spanCount)
        is RecyclerViewGridStaggeredVertical -> StaggeredGridLayoutManager(
            recyclerViewType.spanCount,
            StaggeredGridLayoutManager.VERTICAL
        )
        is RecyclerViewGridStaggeredHorizontal -> StaggeredGridLayoutManager(
            recyclerViewType.spanCount,
            StaggeredGridLayoutManager.HORIZONTAL
        )
        is RecyclerViewCurved -> ArcLayoutManager(context, recyclerViewType.horizontalOffset)
    }
}

/**
 * RecyclerView.Adapter that creates the BindingViewHolder according to the
 * given RecyclerViewConfiguration
 *
 * @param items List containing the model for all items
 */
class RecyclerViewBindingAdapter(
    items: List<Any>,
    internal val recyclerViewConfiguration: RecyclerViewConfiguration
) : RecyclerView.Adapter<BindingViewHolder>() {
    private enum class TouchHelpers {
        SWIPE,
        DRAG
    }

    private val _items: MutableList<Any> = items.toMutableList()
    private val touchHelpers: EnumMap<TouchHelpers, ItemTouchHelper> =
        EnumMap(TouchHelpers::class.java)

    private var dragHelperProvider: TouchHelperProvider = { touchHelpers[TouchHelpers.DRAG] }

    /**
     * Inflates the layout, creates the binding and creates the [BindingViewHolder]
     * @param parent The ViewGroup into which the new View will be added after it is bound to
     *               an adapter position.
     * @param viewType The specified layout for the model as defined in [ClassLayoutMapping]
     */
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BindingViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<ViewDataBinding>(
            layoutInflater, viewType, parent, false
        )
        return when (recyclerViewConfiguration.viewHolderConfiguration) {
            is StandardViewHolderConfiguration -> BindingViewHolder(
                binding,
                recyclerViewConfiguration.viewHolderConfiguration
            )
            is ExpandableViewHolderConfiguration -> ExpandableViewHolder(
                binding,
                recyclerViewConfiguration.viewHolderConfiguration
            )
            is DragHandleViewHolderConfiguration ->
                DragHandleViewHolder(
                    binding,
                    recyclerViewConfiguration.viewHolderConfiguration,
                    dragHelperProvider
                )
        }
    }

    /**
     * Set a new list of items and calculate the change diff
     *
     * @param newItems List of new items
     */
    fun updateData(newItems: List<Any>) {
        val diffResult = DiffUtil.calculateDiff(BindingAdapterDiffCallback(_items, newItems))
        _items.clear()
        _items.addAll(newItems)
        diffResult.dispatchUpdatesTo(this)
    }

    /**
     * Binds the model to the [BindingViewHolder]
     *
     * @param holder View holder
     * @param position Where to fetch the model that must be bound to this View
     */
    override fun onBindViewHolder(holder: BindingViewHolder, position: Int) {
        holder.item = _items[position]
    }

    /**
     * Get the layout id for a given class model
     *
     * @param position Where to fetch the model
     * @return Layout resource
     */
    override fun getItemViewType(position: Int): Int {
        val item = _items[position]
        return recyclerViewConfiguration.layoutIds.getOrElse(item::class) {
            throw IllegalStateException("No layout id defined for class ${item::class}")
        }
    }

    /**
     * Returns the number of items in this model
     */
    override fun getItemCount(): Int = _items.size

    /**
     * Close [ExpandableViewHolder] when user scrolls away
     */
    override fun onViewDetachedFromWindow(holder: BindingViewHolder) {
        super.onViewDetachedFromWindow(holder)
        (holder as? ExpandableViewHolder)?.close()
    }

    internal fun getItemOnPosition(position: Int): Any = _items[position]

    internal fun deleteItemOnPosition(position: Int) {
        _items.removeAt(position)
        notifyItemRemoved(position)
    }

    internal fun addItemOnPosition(position: Int, item: Any) {
        _items.add(position, item)
        notifyItemInserted(position)
    }

    internal fun swapItems(fromPosition: Int, toPosition: Int) {
        val swapped = _items[fromPosition]
        _items.removeAt(fromPosition)
        _items.add(toPosition, swapped)
        notifyItemMoved(fromPosition, toPosition)
    }

    internal fun createTouchHelper(swipeConfiguration: SwipeConfiguration? = null): ItemTouchHelper? {
        swipeConfiguration ?: return null
        val itemTouchHelper = ItemTouchHelper(SwipeTouchHelper(this, swipeConfiguration))
        touchHelpers[TouchHelpers.SWIPE] = itemTouchHelper
        return itemTouchHelper
    }

    internal fun createTouchHelper(dragConfiguration: DragConfiguration? = null): ItemTouchHelper? {
        dragConfiguration ?: return null
        val itemTouchHelper = ItemTouchHelper(DragTouchHelper(this, dragConfiguration))
        touchHelpers[TouchHelpers.DRAG] = itemTouchHelper
        return itemTouchHelper
    }

    internal fun clearTouchHelpers() {
        touchHelpers.forEach {
            it.value.attachToRecyclerView(null)
        }
        touchHelpers.clear()
    }
}
