package com.br.sample.extprops

import java.io.BufferedReader
import java.io.FileInputStream
import java.io.FileReader
import java.io.InputStreamReader

val buffer = BufferedReader(InputStreamReader(System.`in`))

private fun sample1() {
    do {
        val line = buffer.line() ?: break
        println(line)
    } while (true)
}


private fun sample2() {
    buffer.process({it != "null"}) {
        println(it)
    }
}

private fun sample3() {
    val buffer = BufferedReader(FileReader("raw/ref"))
    for (line in buffer.lines) {
        println(line)
    }
}

fun main() {
    sample3()
}