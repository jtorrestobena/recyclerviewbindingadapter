package com.bytecoders.recyclerviewbindinglib

import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView

fun RecyclerView.bindModel(
    model: List<Any>?,
    recyclerViewConfiguration: RecyclerViewConfiguration
) {
    if (model == null) return

    adapter = RecyclerViewBindingAdapter(model, recyclerViewConfiguration)
    layoutManager = recyclerViewConfiguration.getLayoutManager(context)

    if (onFlingListener == null) {
        when (recyclerViewConfiguration.snap) {
            Snap.LINEAR -> LinearSnapHelper().attachToRecyclerView(this)
            Snap.PAGER -> PagerSnapHelper().attachToRecyclerView(this)
        }
    }
}