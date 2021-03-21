package com.bytecoders.librecyclerviewbindinggenerator

import com.bytecoders.librecyclerviewbindinggenerator.extensions.android
import com.bytecoders.librecyclerviewbindinggenerator.extensions.variants
import org.gradle.api.Plugin
import org.gradle.api.Project
import java.io.File

internal class RecyclerviewBindingGenerator : Plugin<Project> {

    override fun apply(project: Project) {
        project.android().variants().all { variant ->

            // Make a task for each combination of build type and product flavor
            val bindingTaskName = "generateRecyclerviewBinding${variant.name.capitalize()}"

            val packageFolder = BINDING_PACKAGE.split(".").joinToString("/")
            val outputPath = "${project.rootDir}/${project.name}/src/main/java/$packageFolder"

            project.tasks.register(bindingTaskName, BindingTask::class.java) { bindingTask ->
                bindingTask.group = "recyclerview"

                // We write our output in the build folder. Also note that we want to have a
                // reference to this so we can later mark it as a generated resource folder
                val outputDirectory =
                    File(outputPath).apply { mkdir() }
                bindingTask.outputFile = File(outputDirectory, "RecyclerViewBinding.kt")

                // Marks the output directory as an app resource folder
                variant.registerGeneratedResFolders(
                    project.files(outputDirectory).builtBy(
                        bindingTask
                    )
                )
            }
        }
    }
}