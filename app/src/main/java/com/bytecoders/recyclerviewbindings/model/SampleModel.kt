package com.bytecoders.recyclerviewbindings.model

import com.bytecoders.recyclerviewbindinglib.diff.ContentChecker
import com.bytecoders.recyclerviewbindings.util.SingleLiveEvent
import java.io.Serializable

data class SampleModel(
    val number: Int,
    val text: String,
    val itemClicked: SingleLiveEvent<SampleModel>
) : ContentChecker {
    fun numberString(): String = number.toString()

    fun onClick() {
        itemClicked.value = this
    }

    override val comparableFields: Map<String, Serializable>
        get() = mapOf("number" to number, "text" to text)
}
