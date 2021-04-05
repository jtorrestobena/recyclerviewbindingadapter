[recyclerviewbindinglib](../../index.md) / [com.bytecoders.recyclerviewbindinglib.viewholder](../index.md) / [MainViewHolderConfig](./index.md)

# MainViewHolderConfig

`interface MainViewHolderConfig`

Takes care of abstracting all View configuration
parameters and binding the model for a given item

### Properties

| Name | Summary |
|---|---|
| [itemAnimation](item-animation.md) | `abstract val itemAnimation: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`?` |
| [variableId](variable-id.md) | `abstract val variableId: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |

### Inheritors

| Name | Summary |
|---|---|
| [DragHandleViewHolderConfiguration](../-drag-handle-view-holder-configuration/index.md) | `class DragHandleViewHolderConfiguration : `[`ViewHolderConfiguration`](../-view-holder-configuration.md)`, `[`MainViewHolderConfig`](./index.md) |
| [ExpandableViewHolderConfiguration](../-expandable-view-holder-configuration/index.md) | `class ExpandableViewHolderConfiguration : `[`ViewHolderConfiguration`](../-view-holder-configuration.md)`, `[`MainViewHolderConfig`](./index.md) |
| [StandardViewHolderConfiguration](../-standard-view-holder-configuration/index.md) | `class StandardViewHolderConfiguration : `[`ViewHolderConfiguration`](../-view-holder-configuration.md)`, `[`MainViewHolderConfig`](./index.md) |
