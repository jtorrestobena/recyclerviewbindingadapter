package com.bytecoders.librecyclerviewbindinggenerator

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.OutputFile
import org.gradle.api.tasks.TaskAction
import java.io.File
import java.io.Serializable
import java.time.LocalDateTime

const val BINDING_PACKAGE = "binding.recyclerview"

private const val LIB_IMPORT_PATH = "com.bytecoders.recyclerviewbindinglib"
data class Param(val name: String, val type: String, val import: Boolean = false)
data class BindingAdapter(val method: String, val params: List<Param>, val viewType: String = "RecyclerView")

internal open class BindingTask : DefaultTask() {
    @get:OutputFile
    lateinit var outputFile: File

    @TaskAction
    fun makeBindingFile() {
        val bindingList = listOf(
            BindingAdapter("bindModel", listOf(
                Param("model", "List<Any>?"),
                Param("config", "RecyclerViewConfiguration", true)
            ))
        )

        val import = mutableListOf(
            "androidx.databinding.BindingAdapter",
            "androidx.recyclerview.widget.RecyclerView"
        )

        val comments = listOf(
            "This file is autogenerated do not edit it by hand",
            "Generated on ${LocalDateTime.now()}"
        )

        var bindingString = ""
        bindingList.forEach { bindingAdapter ->
            import.add("$LIB_IMPORT_PATH.${bindingAdapter.method}")
            val bindParams = bindingAdapter.params.joinToString {
                if (it.import) {
                    import.add("$LIB_IMPORT_PATH.${it.type}")
                }
                "\"${it.name}\""
            }
            val methodParams = bindingAdapter.params.joinToString {
                "${it.name}: ${it.type}"
            }
            bindingString += "@BindingAdapter($bindParams)\nfun ${bindingAdapter.viewType}.${bindingAdapter.method}($methodParams) = "
            val callParams = bindingAdapter.params.joinToString { it.name }
            bindingString += "${bindingAdapter.method}($callParams)"
        }
        outputFile.writeText("package $BINDING_PACKAGE\n" +
                import.joinToString(separator = "") { "import $it\n" } +
                comments.joinToString(separator = "") { "// $it\n" } +
                bindingString)
    }
}