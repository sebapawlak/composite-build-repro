package com.atlassian.performance.tools.infrastructure

import java.io.File
import java.util.zip.ZipFile
import kotlin.streams.toList

internal class ObrApp(
    private val file: File
) {
    fun extractJars(
        directory: File
    ): List<File> {
        return ZipFile(file).use { zipFile ->
            zipFile
                .stream()
                .filter { entry -> File(entry.name).extension == "jar" }
                .map { entry ->
                    val jarFile = directory.resolve(File(entry.name).name)
                    jarFile.createNewFile()
                    jarFile.outputStream().buffered().use { zipFile.getInputStream(entry).copyTo(it) }
                    jarFile
                }
                .toList()
        }
    }
}