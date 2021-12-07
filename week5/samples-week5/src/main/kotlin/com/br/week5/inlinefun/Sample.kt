package com.br.week5.inlinefun

/*
    https://www.coursera.org/learn/kotlin-for-java-developers/lecture/UqKXj/library-functions-looking-like-built-in-constructs
    runs the block of code (lambda) and returns the last expression as the result
 */

val foo = run { 42 }


fun main() {
    println(foo)
}