package com.br.playground3.fp

// https://www.coursera.org/learn/kotlin-for-java-developers/lecture/5WnAh/lambdas

// lambda syntax -> { paramaters -> lambda body } sempre envolto de das chaves {}
val binOperation = { x: Int, y: Int, op: (Int, Int) -> Int -> op(x, y) }

val wrapperFunction = { op: () -> Unit -> op() }


fun String.frequency() : Map<Char, Int> =
    this.run {
        val freq = mutableMapOf<Char, Int>()
        this.forEach { freq[it] = freq[it]?.plus(1) ?: 1 }
        freq
    }

fun main() {
    println(binOperation(10, 5) { x: Int, y: Int -> x xor y } == 15)

    // wrapperFunction () {} : os parenteses sao desnecessarios
    wrapperFunction {
        println(0xff)
    }

    // usando destructuring quando iterar por colecoes de pares
    "Christoffer".frequency().mapValues { (c, q)  -> println("$c: $q") }

}