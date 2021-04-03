package com.bytecoders.recyclerviewbindinglib

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.bytecoders.recyclerviewbindinglib.touchhelper.TouchHelper

fun RecyclerView.bindModel(
    model: List<Any>?,
    recyclerViewConfiguration: RecyclerViewConfiguration
) {
    if (model == null) return

    // If there's an adapter update data
    (adapter as? RecyclerViewBindingAdapter)?.let {
        if (it.recyclerViewConfiguration == recyclerViewConfiguration) {
            // Configuration has not changed so just update the model
            it.updateData(model)
        } else {
            adapter = RecyclerViewBindingAdapter(model, recyclerViewConfiguration)
        }
    } ?: run {
        adapter = RecyclerViewBindingAdapter(model, recyclerViewConfiguration)
    }

    layoutManager = recyclerViewConfiguration.getLayoutManager(context)

    if (onFlingListener == null) {
        when (recyclerViewConfiguration.snap) {
            Snap.LINEAR -> LinearSnapHelper().attachToRecyclerView(this)
            Snap.PAGER -> PagerSnapHelper().attachToRecyclerView(this)
        }
    }

    recyclerViewConfiguration.swipeConfiguration?.let {
        val itemTouchHelper = ItemTouchHelper(TouchHelper(adapter as RecyclerViewBindingAdapter, it))
        itemTouchHelper.attachToRecyclerView(this)
    }
}