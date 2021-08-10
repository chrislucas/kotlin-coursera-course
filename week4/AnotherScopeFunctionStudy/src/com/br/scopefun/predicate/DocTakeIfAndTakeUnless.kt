package com.br.scopefun.predicate

import kotlin.random.Random

/**
 * https://kotlinlang.org/docs/scope-functions.html#takeif-and-takeunless
 * T.takeIf( predicate: T.() -> Boolean : T ?
 * retorna this se predicate retornar true
 *
 *   T.takeUnless( predicate: T.() -> Boolean : T ?
 *
 *   retorna this se predicate retornar false
 *
 * */

private fun sample1() {
    val str = "chrisluccas"
    val takeIt = str.takeIf { it.isNotEmpty() }?.uppercase()
    println(takeIt)
}


private fun Long.isOdd() = this and 1L == 1L

private fun sample2(value: Long) {
    val mValue = value.takeUnless(Long::isOdd)
    println("$value, $mValue")
}

fun main() {
    sample1()
    sample2(Random.nextLong(System.currentTimeMillis()) % 50L)
}