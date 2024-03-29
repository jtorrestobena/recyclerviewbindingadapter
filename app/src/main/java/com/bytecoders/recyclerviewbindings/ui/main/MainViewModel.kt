package com.bytecoders.recyclerviewbindings.ui.main

import android.content.SharedPreferences
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bytecoders.recyclerviewbindinglib.ClassLayoutMapping
import com.bytecoders.recyclerviewbindinglib.RecyclerViewConfiguration
import com.bytecoders.recyclerviewbindinglib.RecyclerViewCurved
import com.bytecoders.recyclerviewbindinglib.RecyclerViewGrid
import com.bytecoders.recyclerviewbindinglib.RecyclerViewGridStaggeredVertical
import com.bytecoders.recyclerviewbindinglib.RecyclerViewHorizontal
import com.bytecoders.recyclerviewbindinglib.RecyclerViewVertical
import com.bytecoders.recyclerviewbindinglib.Snap
import com.bytecoders.recyclerviewbindinglib.touchhelper.DragConfiguration
import com.bytecoders.recyclerviewbindinglib.touchhelper.DragDirection
import com.bytecoders.recyclerviewbindinglib.touchhelper.SwipeConfiguration
import com.bytecoders.recyclerviewbindinglib.touchhelper.SwipeDirection.BOTH
import com.bytecoders.recyclerviewbindinglib.touchhelper.SwipedItem
import com.bytecoders.recyclerviewbindinglib.viewholder.DragHandleViewHolderConfiguration
import com.bytecoders.recyclerviewbindinglib.viewholder.StandardViewHolderConfiguration
import com.bytecoders.recyclerviewbindings.BR
import com.bytecoders.recyclerviewbindings.R
import com.bytecoders.recyclerviewbindings.model.SampleModel
import com.bytecoders.recyclerviewbindings.util.SingleLiveEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

private const val NUM_ITEMS = 2000

enum class HelperConfig {
    NONE,
    SWIPE_DELETE,
    REORDER
}

class MainViewModel : ViewModel() {
    /**
     * If you forget to add mappings between model classes and layouts you will get
     *
     * java.lang.IllegalStateException: No layout id defined for class ...
     **/
    private val layoutMapping: ClassLayoutMapping =
        mapOf(SampleModel::class to R.layout.item_recyclerview_sample_model_text)
    val recyclerViewModel = MutableLiveData<List<SampleModel>>()
    val itemDeletedEvent = SingleLiveEvent<SwipedItem>()
    val itemMovedEvent = SingleLiveEvent<Pair<Int, Int>>()
    private val swipeBothSidesConfiguration = SwipeConfiguration(BOTH) {
        it.delete() // Delete item
        itemDeletedEvent.value = it
    }

    private val dragConfiguration = DragConfiguration(
        DragDirection.ALL_DIRECTIONS
    ) { from, to ->
        itemMovedEvent.value = Pair(from, to)
    }

    private var helperConfig = HelperConfig.NONE

    val recyclerViewConfiguration = MutableLiveData(
        RecyclerViewConfiguration(
            layoutMapping, RecyclerViewVertical, StandardViewHolderConfiguration(BR.model),
            swipeConfiguration = if (helperConfig == HelperConfig.SWIPE_DELETE) swipeBothSidesConfiguration else null,
            dragConfiguration = if (helperConfig == HelperConfig.REORDER) dragConfiguration else null
        )
    )

    val itemClicked = SingleLiveEvent<SampleModel>()

    fun load(preferences: SharedPreferences?) {
        setupRecyclerView(preferences)
        viewModelScope.launch(Dispatchers.Main) {
            recyclerViewModel.value = createItemList()
        }
    }

    private suspend fun createItemList(): MutableList<SampleModel> {
        val itemList = mutableListOf<SampleModel>()
        withContext(Dispatchers.IO) {
            // Load a list of items
            for (i in 1..NUM_ITEMS) {
                itemList.add(SampleModel(i, "Item $i", itemClicked))
            }
        }
        return itemList
    }

    private fun setupRecyclerView(preferences: SharedPreferences?) {
        if (preferences == null) return
        val snap = preferences.getBoolean("snap", false)
        val recyclerViewType = preferences.getString("recyclerview_type", null)

        val itemAnimation: Int? = when (preferences.getString("animation_type", null)) {
            "expand_center" -> R.anim.expand_center
            "right_to_left" -> R.anim.right_to_left
            "right_to_left_fade" -> R.anim.right_to_left_fade
            else -> null
        }

        var handle = false
        val layoutId: Int = when (preferences.getString("layout_type", null)) {
            "res/layout/item_recyclerview_sample_model_text_wrap_width.xml" ->
                R.layout.item_recyclerview_sample_model_text_wrap_width
            "res/layout/item_recyclerview_sample_model_text_circle.xml" ->
                R.layout.item_recyclerview_sample_model_text_circle
            "res/layout/item_recyclerview_sample_model_text_handle.xml" -> {
                handle = true
                R.layout.item_recyclerview_sample_model_text_handle
            }
            else -> R.layout.item_recyclerview_sample_model_text
        }

        helperConfig = when (preferences.getString("helper_type", null)) {
            "Swipe2delete" -> HelperConfig.SWIPE_DELETE
            "Reorder" -> HelperConfig.REORDER
            else -> HelperConfig.NONE
        }

        if (handle) {
            handle = (helperConfig == HelperConfig.REORDER)
        }

        setRecyclerViewConfiguration(handle, layoutId, recyclerViewType, itemAnimation, snap)
    }

    private fun setRecyclerViewConfiguration(
        handle: Boolean,
        layoutId: Int,
        recyclerViewType: String?,
        itemAnimation: Int?,
        snap: Boolean
    ) {
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
            if (handle)
                DragHandleViewHolderConfiguration(
                    BR.model,
                    R.id.dragHandle
                )
            else StandardViewHolderConfiguration(
                BR.model,
                itemAnimation
            ),
            if (snap) Snap.LINEAR else null,
            swipeConfiguration = if (helperConfig == HelperConfig.SWIPE_DELETE) swipeBothSidesConfiguration else null,
            dragConfiguration = if (helperConfig == HelperConfig.REORDER) dragConfiguration else null
        )
    }
}
