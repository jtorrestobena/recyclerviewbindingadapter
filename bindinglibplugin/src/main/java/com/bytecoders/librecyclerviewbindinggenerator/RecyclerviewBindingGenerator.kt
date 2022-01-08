package com.bytecoders.librecyclerviewbindinggenerator

import com.android.build.gradle.internal.tasks.factory.dependsOn
import com.bytecoders.librecyclerviewbindinggenerator.extensions.android
import com.bytecoders.librecyclerviewbindinggenerator.extensions.packageToPath
import com.bytecoders.librecyclerviewbindinggenerator.extensions.taskName
import com.bytecoders.librecyclerviewbindinggenerator.extensions.variants
import org.gradle.api.Plugin
import org.gradle.api.Project
import java.io.File

private const val TASK_NAME = "generateRecyclerviewBinding"
private const val OUTPUT_FILENAME = "RecyclerViewGeneratedBinding.kt"
@Suppress("unused") // See bindinglibplugin/build.gradle
internal class RecyclerviewBindingGenerator : Plugin<Project> {

    override fun apply(project: Project) {
        // Add dependency on compile
        project.afterEvaluate { p ->
            p.android().variants().all { variant ->
                variant.javaCompileProvider.dependsOn(variant.taskName(TASK_NAME))
            }
        }

        val compilePath = "${project.buildDir}/generated/source/recyclerview"

        // Add Source sets
        project.android().sourceSets { sourceSet ->
            sourceSet.find { it.name == "main" }?.java?.srcDir(compilePath)
        }

        project.android().variants().all { variant ->

            // Make a task for each combination of build type and product flavor
            val bindingTaskName = variant.taskName(TASK_NAME)
            val outputPath = "$compilePath/${BINDING_PACKAGE.packageToPath()}"

            project.tasks.register(bindingTaskName, BindingTask::class.java) { bindingTask ->
                bindingTask.group = "recyclerview"

                // We write our output in the build folder
                val outputDirectory =
                    File(outputPath).apply { mkdir() }
                bindingTask.outputFile = File(outputDirectory, OUTPUT_FILENAME)
            }
        }
    }
}
