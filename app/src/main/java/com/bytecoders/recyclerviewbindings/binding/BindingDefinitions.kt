package com.bytecoders.recyclerviewbindings.binding

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bytecoders.recyclerviewbindinglib.RecyclerViewConfiguration
import com.bytecoders.recyclerviewbindinglib.bindModel

@BindingAdapter("model", "config")
fun RecyclerView.bindModel(model: List<Any>?, recyclerViewConfiguration: RecyclerViewConfiguration) = bindModel(model, recyclerViewConfiguration)