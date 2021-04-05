[recyclerviewbindinglib](../../index.md) / [com.bytecoders.recyclerviewbindinglib](../index.md) / [RecyclerViewBindingAdapter](index.md) / [onCreateViewHolder](./on-create-view-holder.md)

# onCreateViewHolder

`fun onCreateViewHolder(parent: `[`ViewGroup`](https://developer.android.com/reference/android/view/ViewGroup.html)`, viewType: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`BindingViewHolder`](../../com.bytecoders.recyclerviewbindinglib.viewholder/-binding-view-holder/index.md)

Inflates the layout, creates the binding and creates the [BindingViewHolder](../../com.bytecoders.recyclerviewbindinglib.viewholder/-binding-view-holder/index.md)

### Parameters

`parent` - The ViewGroup into which the new View will be added after it is bound to
    an adapter position.

`viewType` - The specified layout for the model as defined in [ClassLayoutMapping](../-class-layout-mapping.md)