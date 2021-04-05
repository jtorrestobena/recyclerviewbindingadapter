package com.bytecoders.recyclerviewbindinglib.viewholder

import android.widget.TextView
import androidx.databinding.ViewDataBinding

/**
 * Allows expanding a TextView when clicking on it, also takes care of closing it
 * when users scroll away and it is no longer visible
 */
class ExpandableViewHolder(binding: ViewDataBinding, private val viewHolderConfiguration: ExpandableViewHolderConfiguration)
    : BindingViewHolder(binding, viewHolderConfiguration) {
    private var expanded = false

    private val expandableTextView: TextView by lazy { itemView.findViewById(viewHolderConfiguration.expandableTextResource) }

    override fun bind(item: Any) {
        super.bind(item)
        itemView.setOnClickListener {
            // Show any remaining lines on tap
            if (!expanded) {
                expandableTextView.maxLines = Int.MAX_VALUE
                expanded = true
            }
        }
    }

    fun close() {
        if (expanded) {
            expandableTextView.maxLines = viewHolderConfiguration.collapsedLines
            expanded = false
        }
    }
}