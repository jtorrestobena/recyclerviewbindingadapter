[recyclerviewbindinglib](../../index.md) / [com.bytecoders.recyclerviewbindinglib.diff](../index.md) / [BindingAdapterDiffCallback](./index.md)

# BindingAdapterDiffCallback

`class BindingAdapterDiffCallback : Callback`

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `BindingAdapterDiffCallback(oldItems: `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`Any`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)`>, newItems: `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`Any`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)`>)` |

### Functions

| Name | Summary |
|---|---|
| [areContentsTheSame](are-contents-the-same.md) | `fun areContentsTheSame(oldItemPosition: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, newItemPosition: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [areItemsTheSame](are-items-the-same.md) | `fun areItemsTheSame(oldItemPosition: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, newItemPosition: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [getChangePayload](get-change-payload.md) | `fun getChangePayload(oldItemPosition: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, newItemPosition: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`Any`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)`?` |
| [getNewListSize](get-new-list-size.md) | `fun getNewListSize(): `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [getOldListSize](get-old-list-size.md) | `fun getOldListSize(): `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
