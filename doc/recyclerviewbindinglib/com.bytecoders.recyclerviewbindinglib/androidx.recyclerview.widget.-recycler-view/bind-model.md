[recyclerviewbindinglib](../../index.md) / [com.bytecoders.recyclerviewbindinglib](../index.md) / [androidx.recyclerview.widget.RecyclerView](index.md) / [bindModel](./bind-model.md)

# bindModel

`fun RecyclerView.bindModel(model: `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`Any`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)`>?, recyclerViewConfiguration: `[`RecyclerViewConfiguration`](../-recycler-view-configuration/index.md)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)

Binds the model. This function can be called as many times as needed.

Ideally will be called by Android Data Binding system itself.

See the binding generator plugin for automating this process

### Parameters

`model` - list of models for the items

`recyclerViewConfiguration` - model for RecyclerView presentation