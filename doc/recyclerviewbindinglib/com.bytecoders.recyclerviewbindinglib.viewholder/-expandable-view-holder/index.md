[recyclerviewbindinglib](../../index.md) / [com.bytecoders.recyclerviewbindinglib.viewholder](../index.md) / [ExpandableViewHolder](./index.md)

# ExpandableViewHolder

`class ExpandableViewHolder : `[`BindingViewHolder`](../-binding-view-holder/index.md)

Allows expanding a TextView when clicking on it, also takes care of closing it
when users scroll away and it is no longer visible

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `ExpandableViewHolder(binding: ViewDataBinding, viewHolderConfiguration: `[`ExpandableViewHolderConfiguration`](../-expandable-view-holder-configuration/index.md)`)`<br>Allows expanding a TextView when clicking on it, also takes care of closing it when users scroll away and it is no longer visible |

### Inherited Properties

| Name | Summary |
|---|---|
| [item](../-binding-view-holder/item.md) | `var item: `[`Any`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html) |

### Functions

| Name | Summary |
|---|---|
| [bind](bind.md) | `fun bind(item: `[`Any`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [close](close.md) | `fun close(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
