[recyclerviewbindinglib](../../index.md) / [com.bytecoders.recyclerviewbindinglib.diff](../index.md) / [ContentChecker](./index.md)

# ContentChecker

`interface ContentChecker`

### Properties

| Name | Summary |
|---|---|
| [comparableFields](comparable-fields.md) | `abstract val comparableFields: `[`Map`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-map/index.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, `[`Serializable`](https://developer.android.com/reference/java/io/Serializable.html)`>` |

### Functions

| Name | Summary |
|---|---|
| [changePayload](change-payload.md) | `open fun changePayload(newItem: `[`ContentChecker`](./index.md)`): `[`Any`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)`?` |
| [sameContent](same-content.md) | `open fun sameContent(newItem: `[`ContentChecker`](./index.md)`): `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
