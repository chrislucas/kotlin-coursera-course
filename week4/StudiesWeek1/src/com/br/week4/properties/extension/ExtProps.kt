package com.br.week4.properties.extension


private val String.lastIndex: Int
    get() = this.length - 1

val String.lastChar: Char
    get() = this[lastIndex] ;// utilizando 'this'

val String.range: IntRange
    get() = 0 .. lastIndex  // omitindo 'this'

fun main() {
    "chris".run {
        println(lastChar)
        println(range)
    }
}