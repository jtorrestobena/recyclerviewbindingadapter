[recyclerviewbindinglib](../../index.md) / [com.bytecoders.recyclerviewbindinglib](../index.md) / [RecyclerViewBindingAdapter](./index.md)

# RecyclerViewBindingAdapter

`class RecyclerViewBindingAdapter : Adapter<`[`BindingViewHolder`](../../com.bytecoders.recyclerviewbindinglib.viewholder/-binding-view-holder/index.md)`>`

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `RecyclerViewBindingAdapter(_items: `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`Any`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)`>, recyclerViewConfiguration: `[`RecyclerViewConfiguration`](../-recycler-view-configuration/index.md)`)` |

### Functions

| Name | Summary |
|---|---|
| [getItemCount](get-item-count.md) | `fun getItemCount(): `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [getItemViewType](get-item-view-type.md) | `fun getItemViewType(position: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [onBindViewHolder](on-bind-view-holder.md) | `fun onBindViewHolder(holder: `[`BindingViewHolder`](../../com.bytecoders.recyclerviewbindinglib.viewholder/-binding-view-holder/index.md)`, position: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [onCreateViewHolder](on-create-view-holder.md) | `fun onCreateViewHolder(parent: `[`ViewGroup`](https://developer.android.com/reference/android/view/ViewGroup.html)`, viewType: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`BindingViewHolder`](../../com.bytecoders.recyclerviewbindinglib.viewholder/-binding-view-holder/index.md) |
| [onViewDetachedFromWindow](on-view-detached-from-window.md) | `fun onViewDetachedFromWindow(holder: `[`BindingViewHolder`](../../com.bytecoders.recyclerviewbindinglib.viewholder/-binding-view-holder/index.md)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [swapItems](swap-items.md) | `fun swapItems(fromPosition: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, toPosition: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [updateData](update-data.md) | `fun updateData(newItems: `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`Any`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)`>): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
