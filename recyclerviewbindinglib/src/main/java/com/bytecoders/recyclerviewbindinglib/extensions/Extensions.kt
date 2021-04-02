package com.bytecoders.recyclerviewbindinglib.extensions

import android.content.Context

fun Float.dpToPixels(context: Context): Float = this * context.resources.displayMetrics.density