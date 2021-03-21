package com.bytecoders.recyclerviewbindinglib.viewholder

import android.view.animation.AnimationUtils
import androidx.annotation.AnimRes
import androidx.annotation.IdRes
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

sealed class ViewHolderConfiguration
open class StandardViewHolderConfiguration(val variableId: Int,
                                           @AnimRes val itemAnimation: Int? = null): ViewHolderConfiguration()

class ExpandableViewHolderConfiguration(variableId: Int,
                                        @IdRes val expandableTextResource: Int,
                                        val collapsedLines: Int = 10) : StandardViewHolderConfiguration(variableId)

open class BindingViewHolder(private val binding: ViewDataBinding, private val viewHolderConfiguration: StandardViewHolderConfiguration)
    : RecyclerView.ViewHolder(binding.root) {
    var item: Any = Any()
        set(value) {
            bind(value)
            field = value
        }

    protected open fun bind(item: Any) {
        with(viewHolderConfiguration) {
            itemAnimation?.let {
                itemView.animation = AnimationUtils.loadAnimation(itemView.context, it)
            }
        }
        if (binding.setVariable(viewHolderConfiguration.variableId, item)) {
            binding.executePendingBindings()
        } else {
            throw IllegalStateException("Binding layout does not declare a variable named item for $item")
        }
    }
}