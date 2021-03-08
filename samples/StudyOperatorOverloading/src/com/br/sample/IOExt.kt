package com.br.sample

import java.io.BufferedReader
import java.io.FileReader

val BufferedReader.lines: Iterator<String>
    get() {
        var line = this.readLine()
        return object : Iterator<String> {

            override fun hasNext(): Boolean = line != null

            override fun next(): String {
                if (line == null)
                    return ""

                val result = line ?: ""
                line = readLine()
                return result
            }
        }
    }


fun main() {
    val reader = BufferedReader(FileReader("refs/kotlin_expressions"))
    for (line in reader.lines) {
        println(line)
    }
}