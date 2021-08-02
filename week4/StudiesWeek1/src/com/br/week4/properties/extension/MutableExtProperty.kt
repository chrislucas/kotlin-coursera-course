package com.br.week4.properties.extension

var StringBuilder.last: Char
    get() = this[length - 1]
    set(value: Char) {
        setCharAt(length - 1, value)
    }

fun main() {
    val buffer = StringBuilder("chris")
    buffer.last = 'S'
    println(buffer.toString())
}