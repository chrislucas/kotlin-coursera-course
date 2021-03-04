package com.br.playground3

// take
// https://kotlinlang.org/docs/scope-functions.html#takeif-and-takeunless

fun funTake() {
   println( (1 .. 100).toTypedArray().take(10).partition { it % 2 == 0 })
}

fun main() {
    funTake()
}