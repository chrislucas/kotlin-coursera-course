package com.br.playground3.exts

import java.io.BufferedReader
import java.io.File
import java.io.FileReader
import java.lang.StringBuilder
import java.nio.file.Files
import java.nio.file.Path
import kotlin.streams.toList

fun Path.getAllFiles(predicate: (Path) -> Boolean): List<File> {
    return Files.walk(this)
        .filter(predicate)
        .map(Path::toFile)
        .toList()
}

fun Path.getAllFiles(): List<File> = getAllFiles { true }

fun getAllFiles(directory: File, files: MutableList<File>) {
    directory.listFiles()?.let { filesOrDirectories ->
        for (file in filesOrDirectories) {
            if (file.isDirectory)
                getAllFiles(file, files)
            else if (file.isFile) {
                files.add(file)
            }
        }
    }
}

fun List<File>.joinContent() : String {
    val content = StringBuilder()
    for (file in this) {
       BufferedReader(FileReader(file))
           .readLines()
           .joinTo(content)
    }
    return content.toString()
}