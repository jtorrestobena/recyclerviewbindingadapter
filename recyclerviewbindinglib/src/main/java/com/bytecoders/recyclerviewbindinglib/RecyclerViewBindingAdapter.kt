package com.bytecoders.recyclerviewbindinglib

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bytecoders.recyclerviewbindinglib.viewholder.*
import kotlin.reflect.KClass

typealias ClassLayoutMapping = Map<KClass<*>, Int>


sealed class RecyclerViewType
object RecyclerViewVertical : RecyclerViewType()

class RecyclerViewConfiguration(val layoutIds: ClassLayoutMapping, private val recyclerViewType: RecyclerViewType, val viewHolderConfiguration: ViewHolderConfiguration) {
    fun getLayoutManager(context: Context) = when(recyclerViewType){
        RecyclerViewVertical -> LinearLayoutManager(context)
    }
}

class RecyclerViewBindingAdapter(private val items: List<Any>, private val recyclerViewConfiguration: RecyclerViewConfiguration)
    : RecyclerView.Adapter<BindingViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): BindingViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<ViewDataBinding>(
                layoutInflater, viewType, parent, false)
        return when(recyclerViewConfiguration.viewHolderConfiguration) {
            is StandardViewHolderConfiguration -> BindingViewHolder(binding, recyclerViewConfiguration.viewHolderConfiguration)
            is ExpandableViewHolderConfiguration -> ExpandableViewHolder(binding, recyclerViewConfiguration.viewHolderConfiguration)
        }
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