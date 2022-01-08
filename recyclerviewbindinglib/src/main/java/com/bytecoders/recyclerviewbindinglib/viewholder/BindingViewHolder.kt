package com.bytecoders.recyclerviewbindinglib.viewholder

import android.view.animation.AnimationUtils
import androidx.annotation.AnimRes
import androidx.annotation.IdRes
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

/**
 * Takes care of abstracting all View configuration
 * parameters and binding the model for a given item
 */
interface MainViewHolderConfig {
    val variableId: Int
    val itemAnimation: Int?
}

sealed class ViewHolderConfiguration
class StandardViewHolderConfiguration(
    override val variableId: Int,
    @AnimRes override val itemAnimation: Int? = null
) : ViewHolderConfiguration(), MainViewHolderConfig

class ExpandableViewHolderConfiguration(
    override val variableId: Int,
    @AnimRes override val itemAnimation: Int? = null,
    @IdRes val expandableTextResource: Int,
    val collapsedLines: Int = 10
) : ViewHolderConfiguration(), MainViewHolderConfig

class DragHandleViewHolderConfiguration(
    override val variableId: Int,
    @IdRes val handleResource: Int,
    override val itemAnimation: Int? = null
) : ViewHolderConfiguration(), MainViewHolderConfig

open class BindingViewHolder(
    private val binding: ViewDataBinding,
    private val viewHolderConfiguration: MainViewHolderConfig
) : RecyclerView.ViewHolder(binding.root) {
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
