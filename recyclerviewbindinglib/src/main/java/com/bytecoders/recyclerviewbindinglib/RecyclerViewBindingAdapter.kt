package com.bytecoders.recyclerviewbindinglib

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.*
import com.bytecoders.recyclerviewbindinglib.diff.BindingAdapterDiffCallback
import com.bytecoders.recyclerviewbindinglib.layoutmanager.ArcLayoutManager
import com.bytecoders.recyclerviewbindinglib.touchhelper.DragConfiguration
import com.bytecoders.recyclerviewbindinglib.touchhelper.DragTouchHelper
import com.bytecoders.recyclerviewbindinglib.touchhelper.SwipeConfiguration
import com.bytecoders.recyclerviewbindinglib.touchhelper.SwipeTouchHelper
import com.bytecoders.recyclerviewbindinglib.viewholder.*
import java.util.*
import kotlin.reflect.KClass


typealias ClassLayoutMapping = Map<KClass<*>, Int>


sealed class RecyclerViewType
object RecyclerViewVertical : RecyclerViewType()
object RecyclerViewHorizontal : RecyclerViewType()
open class RecyclerViewGrid(val spanCount: Int) : RecyclerViewType()
class RecyclerViewGridStaggeredVertical(spanCount: Int) : RecyclerViewGrid(spanCount)
class RecyclerViewGridStaggeredHorizontal(spanCount: Int) : RecyclerViewGrid(spanCount)
data class RecyclerViewCurved(val horizontalOffset: Int = 0) : RecyclerViewType()

enum class Snap {
    LINEAR,
    PAGER
}

class RecyclerViewConfiguration(
    val layoutIds: ClassLayoutMapping,
    private val recyclerViewType: RecyclerViewType,
    val viewHolderConfiguration: ViewHolderConfiguration,
    val snap: Snap? = null,
    val swipeConfiguration: SwipeConfiguration? = null,
    val dragConfiguration: DragConfiguration? = null
) {
    fun getLayoutManager(context: Context): RecyclerView.LayoutManager = when (recyclerViewType) {
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

class RecyclerViewBindingAdapter(
    _items: List<Any>,
    internal val recyclerViewConfiguration: RecyclerViewConfiguration
) : RecyclerView.Adapter<BindingViewHolder>() {
    private enum class TouchHelpers {
        SWIPE,
        DRAG
    }

    private val items: MutableList<Any> = _items.toMutableList()
    private val touchHelpers: EnumMap<TouchHelpers, ItemTouchHelper> =
        EnumMap(TouchHelpers::class.java)

    private var dragHelperProvider: TouchHelperProvider = { touchHelpers[TouchHelpers.DRAG] }

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

    fun updateData(newItems: List<Any>) {
        val diffResult = DiffUtil.calculateDiff(BindingAdapterDiffCallback(items, newItems))
        items.clear()
        items.addAll(newItems)
        diffResult.dispatchUpdatesTo(this)
    }

    override fun onBindViewHolder(holder: BindingViewHolder, position: Int) {
        holder.item = items[position]
    }

    override fun getItemViewType(position: Int): Int {
        val item = items[position]
        return recyclerViewConfiguration.layoutIds.getOrElse(item::class) {
            throw IllegalStateException("No layout id defined for class ${item::class}")
        }
    }

    override fun getItemCount(): Int = items.size

    override fun onViewDetachedFromWindow(holder: BindingViewHolder) {
        super.onViewDetachedFromWindow(holder)
        (holder as? ExpandableViewHolder)?.close()
    }

    internal fun getItemOnPosition(position: Int): Any = items[position]

    internal fun deleteItemOnPosition(position: Int) {
        items.removeAt(position)
        notifyItemRemoved(position)
    }

    internal fun addItemOnPosition(position: Int, item: Any) {
        items.add(position, item)
        notifyItemInserted(position)
    }

    fun swapItems(fromPosition: Int, toPosition: Int) {
        val swapped = items[fromPosition]
        items.removeAt(fromPosition)
        items.add(toPosition, swapped)
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