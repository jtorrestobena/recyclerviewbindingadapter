[recyclerviewbindinglib](../../index.md) / [com.bytecoders.recyclerviewbindinglib](../index.md) / [RecyclerViewConfiguration](./index.md)

# RecyclerViewConfiguration

`class RecyclerViewConfiguration`

Model that configures how the RecyclerView should be presented

### Parameters

`layoutIds` - Map each model class to its layout

`recyclerViewType` - Type of RecyclerView layout

`viewHolderConfiguration` - Configuration for individual items

`snap` - How items shall be snapped

`swipeConfiguration` - Configuration for item swipe functionality

`dragConfiguration` - Configuration for item dragging / reordering functionality

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `RecyclerViewConfiguration(layoutIds: `[`ClassLayoutMapping`](../-class-layout-mapping.md)`, recyclerViewType: `[`RecyclerViewType`](../-recycler-view-type.md)`, viewHolderConfiguration: `[`ViewHolderConfiguration`](../../com.bytecoders.recyclerviewbindinglib.viewholder/-view-holder-configuration.md)`, snap: `[`Snap`](../-snap/index.md)`? = null, swipeConfiguration: `[`SwipeConfiguration`](../../com.bytecoders.recyclerviewbindinglib.touchhelper/-swipe-configuration/index.md)`? = null, dragConfiguration: `[`DragConfiguration`](../../com.bytecoders.recyclerviewbindinglib.touchhelper/-drag-configuration/index.md)`? = null)`<br>Model that configures how the RecyclerView should be presented |

### Properties

| Name | Summary |
|---|---|
| [dragConfiguration](drag-configuration.md) | `val dragConfiguration: `[`DragConfiguration`](../../com.bytecoders.recyclerviewbindinglib.touchhelper/-drag-configuration/index.md)`?`<br>Configuration for item dragging / reordering functionality |
| [layoutIds](layout-ids.md) | `val layoutIds: `[`ClassLayoutMapping`](../-class-layout-mapping.md)<br>Map each model class to its layout |
| [snap](snap.md) | `val snap: `[`Snap`](../-snap/index.md)`?`<br>How items shall be snapped |
| [swipeConfiguration](swipe-configuration.md) | `val swipeConfiguration: `[`SwipeConfiguration`](../../com.bytecoders.recyclerviewbindinglib.touchhelper/-swipe-configuration/index.md)`?`<br>Configuration for item swipe functionality |
| [viewHolderConfiguration](view-holder-configuration.md) | `val viewHolderConfiguration: `[`ViewHolderConfiguration`](../../com.bytecoders.recyclerviewbindinglib.viewholder/-view-holder-configuration.md)<br>Configuration for individual items |
