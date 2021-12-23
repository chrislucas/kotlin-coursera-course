package com.br.week5.sequences

import java.lang.StringBuilder

private fun checkFibonacciSeq() {
    fun fibonacci(i: Int): Int {
        var a = 0
        var b = 1
        var c = 1
        while (c < i) {
            b += a
            a = b - a
            c += 1
        }
        return a
    }

    val f = generateSequence(10, ::fibonacci)

    println(f.take(10).toList())
}

// my solution
// https://www.coursera.org/learn/kotlin-for-java-developers/ungradedWidget/DhBaL/kotlin-playground-fibonacci-sequence
private fun fibonacci(n: Long) = sequence {
    var a = 0L
    var b = 1L
    var counter = 1L
    while (counter <= n) {
        b += a
        a = b - a
        yield(Pair(counter, a))
        counter += 1
    }
}

private fun fibonacci(): Sequence<Int> = sequence {
    var a = 0
    var b = 1
    while (true) {
        yield(a)
        b += a
        a = b - a
    }
}

private fun checkMyFibonacci() {
    val s = fibonacci(10).toList()
    println(s)
    val t = fibonacci(100).take(50).toList()
    println(t)
}

private fun checkInfiniteSequenceOfFibonacci() {
    val seq = fibonacci()
    val s = seq.take(4).toList().joinTo(StringBuilder(), ",")
    println(s)
    println("*******************")
    val t = seq.take(40).toList().joinTo(StringBuilder(), ",")
    println(t)

}


fun main() {
    checkInfiniteSequenceOfFibonacci()
}