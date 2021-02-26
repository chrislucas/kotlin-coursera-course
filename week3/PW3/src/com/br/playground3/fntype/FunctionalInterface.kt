package com.br.playground3.fntype

/**
 * Refs
 * https://kotlinlang.org/docs/fun-interfaces.html#sam-conversions
 *
 * Functional interfaces vs Type Aliases
 * https://kotlinlang.org/docs/fun-interfaces.html#functional-interfaces-vs-type-aliases
 * https://blog.jetbrains.com/kotlin/2020/03/kotlin-1-4-m1-released/
 * https://lankydan.dev/calling-java-functional-interfaces-from-kotlin
 *
 * Discussoes sobre functional interface
 * https://stackoverflow.com/questions/33590646/kotlin-use-a-lambda-in-place-of-a-functional-interface
 *
 *
 */


fun interface Caller {
    fun call()
}

fun interface GenericPredicate<T> {
    fun check(value: T): Boolean
}

val even = GenericPredicate<Int> { it and 1 == 0 }


fun main() {
    Caller { println(even.check(10)) }.call()
}
