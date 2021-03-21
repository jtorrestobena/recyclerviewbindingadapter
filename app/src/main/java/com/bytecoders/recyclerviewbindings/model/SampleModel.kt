package com.bytecoders.recyclerviewbindings.model

import com.bytecoders.recyclerviewbindings.util.SingleLiveEvent

data class SampleModel(val number: Int, val text: String, val itemClicked: SingleLiveEvent<SampleModel>) {
    fun numberString(): String = number.toString()

    fun onClick() {
        itemClicked.value = this
    }
}