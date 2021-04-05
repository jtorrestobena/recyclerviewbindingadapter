[recyclerviewbindinglib](../index.md) / [com.bytecoders.recyclerviewbindinglib.viewholder](./index.md)

## Package com.bytecoders.recyclerviewbindinglib.viewholder

### Types

| Name | Summary |
|---|---|
| [BindingViewHolder](-binding-view-holder/index.md) | `open class BindingViewHolder : ViewHolder` |
| [DragHandleViewHolder](-drag-handle-view-holder/index.md) | `class DragHandleViewHolder : `[`BindingViewHolder`](-binding-view-holder/index.md) |
| [DragHandleViewHolderConfiguration](-drag-handle-view-holder-configuration/index.md) | `class DragHandleViewHolderConfiguration : `[`ViewHolderConfiguration`](-view-holder-configuration.md)`, `[`MainViewHolderConfig`](-main-view-holder-config/index.md) |
| [ExpandableViewHolder](-expandable-view-holder/index.md) | `class ExpandableViewHolder : `[`BindingViewHolder`](-binding-view-holder/index.md)<br>Allows expanding a TextView when clicking on it, also takes care of closing it when users scroll away and it is no longer visible |
| [ExpandableViewHolderConfiguration](-expandable-view-holder-configuration/index.md) | `class ExpandableViewHolderConfiguration : `[`ViewHolderConfiguration`](-view-holder-configuration.md)`, `[`MainViewHolderConfig`](-main-view-holder-config/index.md) |
| [MainViewHolderConfig](-main-view-holder-config/index.md) | `interface MainViewHolderConfig`<br>Takes care of abstracting all View configuration parameters and binding the model for a given item |
| [StandardViewHolderConfiguration](-standard-view-holder-configuration/index.md) | `class StandardViewHolderConfiguration : `[`ViewHolderConfiguration`](-view-holder-configuration.md)`, `[`MainViewHolderConfig`](-main-view-holder-config/index.md) |
| [ViewHolderConfiguration](-view-holder-configuration.md) | `sealed class ViewHolderConfiguration` |

### Type Aliases

| Name | Summary |
|---|---|
| [TouchHelperProvider](-touch-helper-provider.md) | `typealias TouchHelperProvider = () -> ItemTouchHelper?`<br>Define a drag handler that will be used. This only works when dragging is enabled |
