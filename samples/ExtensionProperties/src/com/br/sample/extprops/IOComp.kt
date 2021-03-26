package com.br.sample.extprops

import java.io.BufferedReader
import java.io.IOException

val BufferedReader.lines: Iterator<String>
    get() {
        var line = this.readLine()
        return object : Iterator<String> {
            override fun hasNext(): Boolean = line != null

            override fun next(): String {
                if (line == null)
                    return ""
                val result = line
                line = readLine()
                return result
            }
        }
    }


fun BufferedReader.line(): String? = try {
    readLine()
} catch (e: IOException) {
    null
}

fun BufferedReader.process(predicate: (String?) -> Boolean, process: (String) -> Unit) {
    do {
        val line = readLine()
        if (predicate(line)) {
            process(line)
        } else {
            break
        }
    } while (true)
}

