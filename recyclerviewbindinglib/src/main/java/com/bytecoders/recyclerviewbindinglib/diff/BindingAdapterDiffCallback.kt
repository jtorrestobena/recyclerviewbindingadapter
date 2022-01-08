package com.bytecoders.recyclerviewbindinglib.diff

import android.os.Bundle
import androidx.recyclerview.widget.DiffUtil
import java.io.Serializable

class BindingAdapterDiffCallback(private val oldItems: List<Any>, private val newItems: List<Any>) :
    DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldItems.size
    override fun getNewListSize(): Int = newItems.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldItems.getOrNull(oldItemPosition) == newItems.getOrNull(newItemPosition)
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldItems.getOrNull(oldItemPosition)
        val newItem = newItems.getOrNull(newItemPosition)
        if (oldItem is ContentChecker && newItem is ContentChecker) {
            return oldItem.sameContent(newItem)
        }
        return oldItem == newItem
    }

    override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int): Any? {
        val oldItem = oldItems.getOrNull(oldItemPosition)
        val newItem = newItems.getOrNull(newItemPosition)
        if (oldItem is ContentChecker && newItem is ContentChecker) {
            return oldItem.changePayload(newItem)
        }

        return super.getChangePayload(oldItemPosition, newItemPosition)
    }
}

interface ContentChecker {
    val comparableFields: Map<String, Serializable>
    fun sameContent(newItem: ContentChecker): Boolean {
        return !comparableFields.any {
            newItem.comparableFields[it.key] != it.value
        }
    }

    fun changePayload(newItem: ContentChecker): Any? {
        val bundle = Bundle()
        comparableFields.forEach {
            if (newItem.comparableFields[it.key] != it.value) {
                bundle.putSerializable(it.key, newItem.comparableFields[it.key])
            }
        }
        return bundle
    }
}
