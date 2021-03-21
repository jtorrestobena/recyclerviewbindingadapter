package com.bytecoders.librecyclerviewbindinggenerator.extensions

import com.android.build.gradle.AppExtension
import com.android.build.gradle.BaseExtension
import com.android.build.gradle.LibraryExtension
import com.android.build.gradle.api.BaseVariant
import org.gradle.api.DomainObjectSet
import org.gradle.api.GradleException
import org.gradle.api.Project

fun Project.android(): BaseExtension {
    val android = project.extensions.findByType(BaseExtension::class.java)
    if (android != null) {
        return android
    } else {
        throw GradleException("Project $name is not an Android project")
    }
}

fun BaseExtension.variants(): DomainObjectSet<out BaseVariant> {
    return when (this) {
        is AppExtension -> {
            applicationVariants
        }

        is LibraryExtension -> {
            libraryVariants
        }

        else -> throw GradleException("Unsupported BaseExtension type!")
    }
}

fun String.packageToPath() = split(".").joinToString("/")

@Suppress("DefaultLocale")
fun BaseVariant.taskName(taskName: String): String = "$taskName${name.capitalize()}"
