[recyclerviewbindinglib](../../index.md) / [com.bytecoders.recyclerviewbindinglib.touchhelper](../index.md) / [DragTouchHelper](./index.md)

# DragTouchHelper

`class DragTouchHelper : SimpleCallback`

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `DragTouchHelper(adapter: `[`RecyclerViewBindingAdapter`](../../com.bytecoders.recyclerviewbindinglib/-recycler-view-binding-adapter/index.md)`, dragConfiguration: `[`DragConfiguration`](../-drag-configuration/index.md)`)` |

### Functions

| Name | Summary |
|---|---|
| [clearView](clear-view.md) | `fun clearView(recyclerView: RecyclerView, viewHolder: ViewHolder): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [isLongPressDragEnabled](is-long-press-drag-enabled.md) | `fun isLongPressDragEnabled(): `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [onMove](on-move.md) | `fun onMove(recyclerView: RecyclerView, viewHolder: ViewHolder, target: ViewHolder): `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [onSelectedChanged](on-selected-changed.md) | `fun onSelectedChanged(viewHolder: ViewHolder?, actionState: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [onSwiped](on-swiped.md) | `fun onSwiped(viewHolder: ViewHolder, direction: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
