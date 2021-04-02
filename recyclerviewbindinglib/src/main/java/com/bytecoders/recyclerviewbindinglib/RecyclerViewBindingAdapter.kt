package com.bytecoders.recyclerviewbindinglib

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.*
import com.bytecoders.recyclerviewbindinglib.diff.BindingAdapterDiffCallback
import com.bytecoders.recyclerviewbindinglib.layoutmanager.ArcLayoutManager
import com.bytecoders.recyclerviewbindinglib.viewholder.*
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
    val snap: Snap? = null
) {
    fun getLayoutManager(context: Context): RecyclerView.LayoutManager = when(recyclerViewType){
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
    private var items: List<Any>,
    internal val recyclerViewConfiguration: RecyclerViewConfiguration
)
    : RecyclerView.Adapter<BindingViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BindingViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<ViewDataBinding>(
            layoutInflater, viewType, parent, false
        )
        return when(recyclerViewConfiguration.viewHolderConfiguration) {
            is StandardViewHolderConfiguration -> BindingViewHolder(
                binding,
                recyclerViewConfiguration.viewHolderConfiguration
            )
            is ExpandableViewHolderConfiguration -> ExpandableViewHolder(
                binding,
                recyclerViewConfiguration.viewHolderConfiguration
            )
        }
    }

    fun updateData(newItems: List<Any>) {
        val diffResult = DiffUtil.calculateDiff(BindingAdapterDiffCallback(items, newItems))
        items = newItems
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
}