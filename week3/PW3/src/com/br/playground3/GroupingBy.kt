package com.br.playground3

/**
 * Exemplo interessante de como contar elementos repitidos dentro num array e agrupa-los
 * https://stackoverflow.com/questions/47200440/kotlin-how-to-find-number-of-repeated-values-in-a-list
 * */


fun String.frequency(): Map<String, Int> = this.groupingBy {
    val s = it.toString()
    if (s == " ") {
        "ESPACO"
    } else {
        s
    }
}.eachCount()

fun main() {
    println("chris luccas".frequency())
}