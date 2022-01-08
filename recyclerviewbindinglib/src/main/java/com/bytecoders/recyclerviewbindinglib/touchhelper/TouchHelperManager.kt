package com.bytecoders.recyclerviewbindinglib.touchhelper

import androidx.recyclerview.widget.ItemTouchHelper
import com.bytecoders.recyclerviewbindinglib.RecyclerViewBindingAdapter
import com.bytecoders.recyclerviewbindinglib.viewholder.TouchHelperProvider
import java.util.EnumMap

class TouchHelperManager(private val adapter: RecyclerViewBindingAdapter) {
    private enum class TouchHelpers {
        SWIPE,
        DRAG
    }

    private val touchHelpers: EnumMap<TouchHelpers, ItemTouchHelper> =
        EnumMap(TouchHelpers::class.java)

    internal var dragHelperProvider: TouchHelperProvider = { touchHelpers[TouchHelpers.DRAG] }

    internal fun createTouchHelper(swipeConfiguration: SwipeConfiguration? = null): ItemTouchHelper? {
        swipeConfiguration ?: return null
        val itemTouchHelper = ItemTouchHelper(SwipeTouchHelper(adapter, swipeConfiguration))
        touchHelpers[TouchHelpers.SWIPE] = itemTouchHelper
        return itemTouchHelper
    }

    internal fun createTouchHelper(dragConfiguration: DragConfiguration? = null): ItemTouchHelper? {
        dragConfiguration ?: return null
        val itemTouchHelper = ItemTouchHelper(DragTouchHelper(adapter, dragConfiguration))
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
