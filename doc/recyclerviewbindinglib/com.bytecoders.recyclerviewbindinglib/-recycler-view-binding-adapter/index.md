[recyclerviewbindinglib](../../index.md) / [com.bytecoders.recyclerviewbindinglib](../index.md) / [RecyclerViewBindingAdapter](./index.md)

# RecyclerViewBindingAdapter

`class RecyclerViewBindingAdapter : Adapter<`[`BindingViewHolder`](../../com.bytecoders.recyclerviewbindinglib.viewholder/-binding-view-holder/index.md)`>`

RecyclerView.Adapter that creates the BindingViewHolder according to the
given RecyclerViewConfiguration

### Parameters

`items` - List containing the model for all items

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `RecyclerViewBindingAdapter(items: `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`Any`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)`>, recyclerViewConfiguration: `[`RecyclerViewConfiguration`](../-recycler-view-configuration/index.md)`)`<br>RecyclerView.Adapter that creates the BindingViewHolder according to the given RecyclerViewConfiguration |

### Functions

| Name | Summary |
|---|---|
| [getItemCount](get-item-count.md) | `fun getItemCount(): `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>Returns the number of items in this model |
| [getItemViewType](get-item-view-type.md) | `fun getItemViewType(position: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>Get the layout id for a given class model |
| [onBindViewHolder](on-bind-view-holder.md) | `fun onBindViewHolder(holder: `[`BindingViewHolder`](../../com.bytecoders.recyclerviewbindinglib.viewholder/-binding-view-holder/index.md)`, position: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Binds the model to the [BindingViewHolder](../../com.bytecoders.recyclerviewbindinglib.viewholder/-binding-view-holder/index.md) |
| [onCreateViewHolder](on-create-view-holder.md) | `fun onCreateViewHolder(parent: `[`ViewGroup`](https://developer.android.com/reference/android/view/ViewGroup.html)`, viewType: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`BindingViewHolder`](../../com.bytecoders.recyclerviewbindinglib.viewholder/-binding-view-holder/index.md)<br>Inflates the layout, creates the binding and creates the [BindingViewHolder](../../com.bytecoders.recyclerviewbindinglib.viewholder/-binding-view-holder/index.md) |
| [onViewDetachedFromWindow](on-view-detached-from-window.md) | `fun onViewDetachedFromWindow(holder: `[`BindingViewHolder`](../../com.bytecoders.recyclerviewbindinglib.viewholder/-binding-view-holder/index.md)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Close [ExpandableViewHolder](../../com.bytecoders.recyclerviewbindinglib.viewholder/-expandable-view-holder/index.md) when user scrolls away |
| [updateData](update-data.md) | `fun updateData(newItems: `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`Any`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)`>): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Set a new list of items and calculate the change diff |
