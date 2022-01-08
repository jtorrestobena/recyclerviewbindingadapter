package com.bytecoders.recyclerviewbindinglib

import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView

/**
 * Binds the model. This function can be called as many times as needed.
 *
 * Ideally will be called by Android Data Binding system itself.
 *
 * See the binding generator plugin for automating this process
 *
 * @param model list of models for the items
 * @param recyclerViewConfiguration model for RecyclerView presentation
 */
fun RecyclerView.bindModel(
    model: List<Any>?,
    recyclerViewConfiguration: RecyclerViewConfiguration
) {
    if (model == null) return

    // If there's an adapter update data
    (adapter as? RecyclerViewBindingAdapter)?.let {
        it.clearTouchHelpers()
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

    if (onFlingListener == null && recyclerViewConfiguration.snap != null) {
        when (recyclerViewConfiguration.snap) {
            Snap.LINEAR -> LinearSnapHelper().attachToRecyclerView(this)
            Snap.PAGER -> PagerSnapHelper().attachToRecyclerView(this)
        }
    }

    (adapter as? RecyclerViewBindingAdapter)?.let { recyclerViewBindingAdapter ->
        recyclerViewBindingAdapter.createTouchHelper(recyclerViewConfiguration.swipeConfiguration)
            ?.attachToRecyclerView(this)
        recyclerViewBindingAdapter.createTouchHelper(recyclerViewConfiguration.dragConfiguration)
            ?.attachToRecyclerView(this)
    }
}
