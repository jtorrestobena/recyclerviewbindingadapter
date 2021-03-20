package com.bytecoders.recyclerviewbindings.model

data class SampleModel(val number: Int, val text: String) {
    fun numberString(): String = number.toString()
}