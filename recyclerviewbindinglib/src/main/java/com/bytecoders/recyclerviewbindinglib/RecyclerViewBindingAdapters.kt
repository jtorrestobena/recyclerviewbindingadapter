package com.bytecoders.recyclerviewbindinglib

import androidx.recyclerview.widget.RecyclerView

fun RecyclerView.bindModel(model: List<Any>?, recyclerViewConfiguration: RecyclerViewConfiguration) {
    if (model == null) return

    adapter = RecyclerViewBindingAdapter(model, recyclerViewConfiguration)
    layoutManager = recyclerViewConfiguration.getLayoutManager(context)
}