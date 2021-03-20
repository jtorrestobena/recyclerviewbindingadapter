package com.bytecoders.recyclerviewbindinglib.viewholder

import android.view.animation.AnimationUtils
import androidx.annotation.IdRes
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.bytecoders.recyclerviewbindinglib.R

interface ItemClickListener {
    fun itemClicked(position: Int, item: Any)
}

sealed class ViewHolderConfiguration
open class StandardViewHolderConfiguration(val variableId: Int,
                              val itemClick: ItemClickListener? = null): ViewHolderConfiguration()
class ExpandableViewHolderConfiguration(variableId: Int, itemClick: ItemClickListener? = null,
                                        @IdRes val expandableTextResource: Int,
                                        val collapsedLines: Int = 10) : StandardViewHolderConfiguration(variableId, itemClick)

open class BindingViewHolder(private val binding: ViewDataBinding, private val viewHolderConfiguration: StandardViewHolderConfiguration)
    : RecyclerView.ViewHolder(binding.root) {
    var item: Any = Any()
        set(value) {
            bind(value)
            field = value
        }

    protected open fun bind(item: Any) {
        itemView.animation = AnimationUtils.loadAnimation(itemView.context, R.anim.enter_from_right)
        viewHolderConfiguration.itemClick?.apply {
            itemView.setOnClickListener { itemClicked(adapterPosition, item) }
        }
        if (binding.setVariable(viewHolderConfiguration.variableId, item)) {
            binding.executePendingBindings()
        } else {
            throw IllegalStateException("Binding layout does not declare a variable named item for $item")
        }
    }
}