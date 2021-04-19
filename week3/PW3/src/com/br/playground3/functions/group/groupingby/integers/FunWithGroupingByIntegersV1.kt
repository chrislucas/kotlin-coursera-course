package com.br.playground3.functions.group.groupingby.integers

import com.br.playground3.exts.log
import com.br.playground3.functions.group.grouping
import com.br.playground3.functions.group.mapping
import com.br.playground3.exts.toTypedArray

/**
 *
 * funcao groupingBy
 * htps://kotlinlang.org/docs/collection-grouping.html
 * "If we want to group elements and the apply an operation to all groups at one time
 * , use the function groupingBy"
 *
 * Essa funcao retorna um objeto do tipo: interface Grouping<K, out T>
 *
 *     Grouping permite aplicar uma operacoa em todos os grupos dum "modo tardio (melhor
 *     expressao para definir lazy nesse contexto pois os grupos sao construidos num momento preciso
 *     logo antes de aplicar a operacao de transformacao)"
 *
 * Tipos que possuem uma ext function groupingBy:
 * - [Iterable]
 * - [Sequence]
 * - [Array]
 * - [CharSequence]
 *
 *
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

private fun String.frequency(): Map<String, Int> =
    this.groupingBy { char ->
        char.toString().let {
            if (it == " ") {
                "ESPACO"
            } else {
                it
            }
        }
    }.eachCount()


private fun groupingCollectionNumberByModularFunction() {
    (1..100).toTypedArray()
        .grouping { it % 25 }   // a chave para agrupar sera baseada na operacao modulo % 25
        .mapping grouping@{
            val map = mutableMapOf<Int, MutableList<Int>>()
            sourceIterator().run {
                while (this.hasNext()) {
                    val value = this.next()
                    val key = this@grouping.keyOf(value)
                    if (map[key] != null) {
                        map[key]?.add(value)
                    } else {
                        map[key] = mutableListOf(value)
                    }
                }
            }
            map
        }
        .log()
}

fun main() {
    groupingCollectionNumberByModularFunction()
}