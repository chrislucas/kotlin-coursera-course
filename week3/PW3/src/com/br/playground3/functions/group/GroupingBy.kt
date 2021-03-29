package com.br.playground3.functions.group

/**
 * https://kotlinlang.org/docs/collection-grouping.html
 *
 * https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.text/grouping-by.html
 * https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/grouping-by.html
 *
 * https://www.geeksforgeeks.org/kotlin-grouping/
 * */


/**
 * Exemplo interessante de como contar elementos repitidos dentro num array e agrupa-los
 * https://stackoverflow.com/questions/47200440/kotlin-how-to-find-number-of-repeated-values-in-a-list
 * */


fun String.frequency(): Map<String, Int> = this.groupingBy { char ->
    char.toString().let {
        if (it == " ") {
            "ESPACO"
        } else {
            it
        }
    }
}.eachCount()

fun main() {
    println("chris luccas".frequency())
}