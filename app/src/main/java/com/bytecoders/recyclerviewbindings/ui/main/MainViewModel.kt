package com.bytecoders.recyclerviewbindings.ui.main

import android.content.SharedPreferences
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bytecoders.recyclerviewbindinglib.*
import com.bytecoders.recyclerviewbindinglib.viewholder.StandardViewHolderConfiguration
import com.bytecoders.recyclerviewbindings.BR
import com.bytecoders.recyclerviewbindings.R
import com.bytecoders.recyclerviewbindings.model.SampleModel
import com.bytecoders.recyclerviewbindings.util.SingleLiveEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

private const val NUM_ITEMS = 2000
class MainViewModel : ViewModel() {
    /**
     * If you forget to add mappings between model classes and layouts you will get
     *
     * java.lang.IllegalStateException: No layout id defined for class ...
     **/
    private val layoutMapping: ClassLayoutMapping = mapOf(SampleModel::class to R.layout.item_recyclerview_sample_model_text)
    val recyclerViewModel = MutableLiveData<List<SampleModel>>()
    val recyclerViewConfiguration = MutableLiveData(
        RecyclerViewConfiguration(layoutMapping, RecyclerViewVertical, StandardViewHolderConfiguration(BR.model)))

    val itemClicked = SingleLiveEvent<SampleModel>()

    fun load(preferences: SharedPreferences?) {
        setupRecyclerView(preferences)
        viewModelScope.launch(Dispatchers.IO) {
            // Load a list of items
            val itemList = mutableListOf<SampleModel>()
            for (i in 1 .. NUM_ITEMS) {
                itemList.add(SampleModel(i, "Item $i", itemClicked))
            }
            withContext(Dispatchers.Main) {
                recyclerViewModel.value = itemList
            }
        }
    }

    private fun setupRecyclerView(preferences: SharedPreferences?) {
        if (preferences == null) return
        val snap = preferences.getBoolean("snap", false)
        val recyclerViewType = preferences.getString("recyclerview_type", null)

        val itemAnimation: Int? = when(preferences.getString("animation_type", null)) {
            "expand_center" -> R.anim.expand_center
            "right_to_left" -> R.anim.right_to_left
            "right_to_left_fade" -> R.anim.right_to_left_fade
            else -> null
        }

        val layoutId: Int = when (preferences.getString("layout_type", null)) {
            "res/layout/item_recyclerview_sample_model_text_wrap_width.xml" -> R.layout.item_recyclerview_sample_model_text_wrap_width
            "res/layout/item_recyclerview_sample_model_text_circle.xml" -> R.layout.item_recyclerview_sample_model_text_circle
            else -> R.layout.item_recyclerview_sample_model_text
        }
        recyclerViewConfiguration.value = RecyclerViewConfiguration(
            mapOf(SampleModel::class to layoutId),
            when (recyclerViewType) {
                "Horizontal" -> RecyclerViewHorizontal
                "Grid" -> RecyclerViewGrid(2)
                "GridStaggeredVertical" -> RecyclerViewGridStaggeredVertical(2)
                "GridStaggeredHorizontal" -> RecyclerViewGridStaggeredVertical(2)
                "Curved" -> RecyclerViewCurved()
                else -> RecyclerViewVertical
            },
            StandardViewHolderConfiguration(BR.model, itemAnimation),
            if (snap) Snap.LINEAR else null)
    }
}