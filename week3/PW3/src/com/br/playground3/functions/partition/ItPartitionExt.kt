package com.br.playground3.functions.partition

import com.br.playground3.exts.toTypedArray

/**
 * Exemplos baseados nas seguintes referencias
 * https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/partition.html
 * A funcao partition quebra a colecao de elementos original em um par de lista.
 *
 * Ela recebe uma colecao e uma funcao de primeira classe que dita o criterio
 * para executar a particao da colecao. Essa funcao recebe um elemento da lista e deve retornar TRUE/FALSE
 *
 *
 * O primeiro  item do par eh a lista contendo os elementos que retornaram TRUE segundo a funcao e o segundo
 * item eh uma lista com os elementos restantes
 *
 * https://kotlination.com/kotlin-partition-list-map-example/
 *
 * */

fun <A, B> Pair<A, B>.log() = println("$first, $second")

fun <T> fn(array: Array<T>, predicate: (T) -> Boolean) = array.partition(predicate)


fun testWithListOfIntegers() {
    fn((0..10).toTypedArray()) { it and 1 == 0 }.log()
}

fun testWithMap() {
    val map = mapOf<Int, Person>(
        1 to Person("a", 15), 2 to Person("b", 32),
        3 to Person("c", 29), 4 to Person("d", 30)
    )

    map.map { it }.partition { (_, person) -> person.age < 30 }.log()
}


data class Person(val name: String, val age: Short)

fun testWithLisOfCustomClass() {
    fn(
        arrayOf(
            Person("a", 10), Person("b", 11),
            Person("c", 20)
        )
    ) { person ->
        person.age < 20
    }.log()
}

fun main() {
    testWithMap()
}