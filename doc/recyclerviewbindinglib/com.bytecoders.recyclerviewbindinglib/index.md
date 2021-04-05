[recyclerviewbindinglib](../index.md) / [com.bytecoders.recyclerviewbindinglib](./index.md)

## Package com.bytecoders.recyclerviewbindinglib

Contains main adapter and binding functionality

## Main adapters

RecyclerViewBindingAdapter is the main RecyclerView.Adapter
RecyclerViewBindingAdapters contains binding functions

### Types

| Name | Summary |
|---|---|
| [RecyclerViewBindingAdapter](-recycler-view-binding-adapter/index.md) | `class RecyclerViewBindingAdapter : Adapter<`[`BindingViewHolder`](../com.bytecoders.recyclerviewbindinglib.viewholder/-binding-view-holder/index.md)`>`<br>RecyclerView.Adapter that creates the BindingViewHolder according to the given RecyclerViewConfiguration |
| [RecyclerViewConfiguration](-recycler-view-configuration/index.md) | `class RecyclerViewConfiguration`<br>Model that configures how the RecyclerView should be presented |
| [RecyclerViewCurved](-recycler-view-curved/index.md) | `data class RecyclerViewCurved : `[`RecyclerViewType`](-recycler-view-type.md)<br>Items are layout showing an Arc |
| [RecyclerViewGrid](-recycler-view-grid/index.md) | `open class RecyclerViewGrid : `[`RecyclerViewType`](-recycler-view-type.md)<br>Layout items in a Grid |
| [RecyclerViewGridStaggeredHorizontal](-recycler-view-grid-staggered-horizontal/index.md) | `class RecyclerViewGridStaggeredHorizontal : `[`RecyclerViewGrid`](-recycler-view-grid/index.md)<br>Layout items in a staggered grid formation. Horizontal layout |
| [RecyclerViewGridStaggeredVertical](-recycler-view-grid-staggered-vertical/index.md) | `class RecyclerViewGridStaggeredVertical : `[`RecyclerViewGrid`](-recycler-view-grid/index.md)<br>Layout items in a staggered grid formation. Vertical layout |
| [RecyclerViewHorizontal](-recycler-view-horizontal.md) | `object RecyclerViewHorizontal : `[`RecyclerViewType`](-recycler-view-type.md)<br>Layout items horizontally |
| [RecyclerViewType](-recycler-view-type.md) | `sealed class RecyclerViewType`<br>Base sealed class for all RecyclerView types available |
| [RecyclerViewVertical](-recycler-view-vertical.md) | `object RecyclerViewVertical : `[`RecyclerViewType`](-recycler-view-type.md)<br>Layout items vertically |
| [Snap](-snap/index.md) | `enum class Snap`<br>Defines how a item View is snapped in the RecyclerView and creates the necessary model for [RecyclerView.bindModel](androidx.recyclerview.widget.-recycler-view/bind-model.md) function |

### Type Aliases

| Name | Summary |
|---|---|
| [ClassLayoutMapping](-class-layout-mapping.md) | `typealias ClassLayoutMapping = `[`Map`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-map/index.html)`<`[`KClass`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.reflect/-k-class/index.html)`<*>, `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`>`<br>Creates a mapping between a model Class and its corresponding layout resource id |

### Extensions for External Classes

| Name | Summary |
|---|---|
| [androidx.recyclerview.widget.RecyclerView](androidx.recyclerview.widget.-recycler-view/index.md) |  |
