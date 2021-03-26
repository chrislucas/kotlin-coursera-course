package com.br.playground3

// Estudo sobre os metodos do arquivo Comparisons.kt


fun main() {
    val fn = compareBy<Int> { it }

    println(fn.compare(10, 20))
}