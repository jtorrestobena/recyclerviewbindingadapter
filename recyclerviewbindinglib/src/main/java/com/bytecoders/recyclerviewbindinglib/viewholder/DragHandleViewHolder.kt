package com.bytecoders.recyclerviewbindinglib.viewholder

import android.annotation.SuppressLint
import android.view.MotionEvent
import android.view.View
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.ItemTouchHelper

/**
 * Define a drag handler that will be used. This only works when
 * dragging is enabled
 */
typealias TouchHelperProvider = () -> ItemTouchHelper?
class DragHandleViewHolder(
    binding: ViewDataBinding,
    viewHolderConfiguration: DragHandleViewHolderConfiguration,
    private val dragTouchHelper: TouchHelperProvider,
) : BindingViewHolder(binding, viewHolderConfiguration) {
    private val handleView: View by lazy { itemView.findViewById(viewHolderConfiguration.handleResource) }

    @SuppressLint("ClickableViewAccessibility")
    override fun bind(item: Any) {
        super.bind(item)
        handleView.setOnTouchListener { _, motionEvent ->
            if (motionEvent.actionMasked == MotionEvent.ACTION_DOWN) {
                dragTouchHelper()?.startDrag(this)
                true
            } else false
        }
    }
}
