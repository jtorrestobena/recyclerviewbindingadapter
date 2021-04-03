package com.bytecoders.recyclerviewbindinglib.touchhelper

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.bytecoders.recyclerviewbindinglib.RecyclerViewBindingAdapter

enum class SwipeDirection {
    LEFT,
    RIGHT,
    BOTH
}

data class SwipeConfiguration(
    val allowDrag: Boolean = false,
    val swipeDirection: SwipeDirection = SwipeDirection.LEFT,
    val swipeCallback: (SwipedItem) -> Unit
) {
    val touchHelperFlags: Int
        get() = when (swipeDirection) {
            SwipeDirection.LEFT -> ItemTouchHelper.LEFT
            SwipeDirection.RIGHT -> ItemTouchHelper.RIGHT
            SwipeDirection.BOTH -> ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        }
}

data class SwipedItem(
    private val adapter: RecyclerViewBindingAdapter,
    private val position: Int,
    val swipeDirection: SwipeDirection
) {
    val item: Any = adapter.getItemOnPosition(position)
    fun delete() {
        adapter.deleteItemOnPosition(position)
    }

    fun undoDelete() {
        adapter.addItemOnPosition(position, item)
    }
}

class TouchHelper(
    private val adapter: RecyclerViewBindingAdapter,
    private val swipeConfiguration: SwipeConfiguration
) : ItemTouchHelper.SimpleCallback(0, swipeConfiguration.touchHelperFlags) {

    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        return false
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        val position = viewHolder.adapterPosition
        val swipeDir: SwipeDirection = when (direction) {
            ItemTouchHelper.LEFT -> SwipeDirection.LEFT
            ItemTouchHelper.RIGHT -> SwipeDirection.RIGHT
            else -> SwipeDirection.BOTH
        }
        swipeConfiguration.swipeCallback(SwipedItem(adapter, position, swipeDir))
    }
}