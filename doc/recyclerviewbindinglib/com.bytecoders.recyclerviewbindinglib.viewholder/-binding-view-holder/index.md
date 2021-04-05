[recyclerviewbindinglib](../../index.md) / [com.bytecoders.recyclerviewbindinglib.viewholder](../index.md) / [BindingViewHolder](./index.md)

# BindingViewHolder

`open class BindingViewHolder : ViewHolder`

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `BindingViewHolder(binding: ViewDataBinding, viewHolderConfiguration: `[`MainViewHolderConfig`](../-main-view-holder-config/index.md)`)` |

### Properties

| Name | Summary |
|---|---|
| [item](item.md) | `var item: `[`Any`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html) |

### Functions

| Name | Summary |
|---|---|
| [bind](bind.md) | `open fun bind(item: `[`Any`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |

### Inheritors

| Name | Summary |
|---|---|
| [DragHandleViewHolder](../-drag-handle-view-holder/index.md) | `class DragHandleViewHolder : `[`BindingViewHolder`](./index.md) |
| [ExpandableViewHolder](../-expandable-view-holder/index.md) | `class ExpandableViewHolder : `[`BindingViewHolder`](./index.md)<br>Allows expanding a TextView when clicking on it, also takes care of closing it when users scroll away and it is no longer visible |
