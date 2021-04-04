package com.bytecoders.recyclerviewbindinglib.touchhelper

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.ItemTouchHelper.ACTION_STATE_DRAG
import androidx.recyclerview.widget.RecyclerView
import com.bytecoders.recyclerviewbindinglib.RecyclerViewBindingAdapter

enum class DragDirection {
    UP,
    DOWN,
    VERTICAL,
    ALL_DIRECTIONS
}

data class DragConfiguration(
    val dragDirection: DragDirection = DragDirection.VERTICAL,
    val longPressDragEnabled: Boolean = true,
    val moveAlpha: Float = 0.5F,
    val dragCallback: (Int, Int) -> Unit
) {
    val dragFlags: Int
        get() = when (dragDirection) {
            DragDirection.UP -> ItemTouchHelper.UP
            DragDirection.DOWN -> ItemTouchHelper.DOWN
            DragDirection.VERTICAL -> ItemTouchHelper.UP or ItemTouchHelper.DOWN
            DragDirection.ALL_DIRECTIONS -> ItemTouchHelper.UP or ItemTouchHelper.DOWN or ItemTouchHelper.START or ItemTouchHelper.END
        }
}

class DragTouchHelper(
    private val adapter: RecyclerViewBindingAdapter,
    private val dragConfiguration: DragConfiguration
) :
    ItemTouchHelper.SimpleCallback(dragConfiguration.dragFlags, 0) {

    override fun isLongPressDragEnabled(): Boolean = dragConfiguration.longPressDragEnabled

    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        dragConfiguration.dragCallback(viewHolder.adapterPosition, target.adapterPosition)
        adapter.swapItems(viewHolder.adapterPosition, target.adapterPosition)
        return true
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {}
    override fun onSelectedChanged(
        viewHolder: RecyclerView.ViewHolder?,
        actionState: Int
    ) {
        super.onSelectedChanged(viewHolder, actionState)

        if (actionState == ACTION_STATE_DRAG) {
            viewHolder?.itemView?.alpha = dragConfiguration.moveAlpha
        }
    }

    override fun clearView(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder
    ) {
        super.clearView(recyclerView, viewHolder)
        viewHolder.itemView.alpha = 1.0f
    }
}


