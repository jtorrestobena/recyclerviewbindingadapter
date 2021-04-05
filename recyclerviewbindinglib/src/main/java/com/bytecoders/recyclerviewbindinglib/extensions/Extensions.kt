package com.bytecoders.recyclerviewbindinglib.extensions

import android.content.Context

/**
 * Converts a Float value expressed in dp to pixels
 * (absolute size according to screen density)
 *
 * @param context valid Android Context
 */
fun Float.dpToPixels(context: Context): Float = this * context.resources.displayMetrics.density