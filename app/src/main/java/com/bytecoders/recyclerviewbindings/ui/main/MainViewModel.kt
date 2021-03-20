package com.bytecoders.recyclerviewbindings.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bytecoders.recyclerviewbindinglib.*
import com.bytecoders.recyclerviewbindinglib.viewholder.StandardViewHolderConfiguration
import com.bytecoders.recyclerviewbindings.BR
import com.bytecoders.recyclerviewbindings.R
import com.bytecoders.recyclerviewbindings.model.SampleModel
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
        RecyclerViewConfiguration(layoutMapping, RecyclerViewVertical, StandardViewHolderConfiguration(BR.model, itemAnimation = R.anim.expand_center)))

    fun load() {
        viewModelScope.launch(Dispatchers.IO) {
            // Load a list of items
            val itemList = mutableListOf<SampleModel>()
            for (i in 1 until NUM_ITEMS) {
                itemList.add(SampleModel(i, "Item $i"))
            }
            withContext(Dispatchers.Main) {
                recyclerViewModel.value = itemList
            }
        }
    }
}