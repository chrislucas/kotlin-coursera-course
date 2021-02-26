package com.br.playground3.memberref

import com.br.playground3.samples.combinatory.Combinatory

fun fnFunctionReference() {
    // Function reference
    val ncr = Combinatory::ncr
    println(ncr)
    println(ncr.run { this(12, 3) })
    println(ncr.also { it(12, 3) })
}

fun compareFunctionReferenceWithLambda() {
    fun isEven(i: Int) = i and 1 == 0
    val isEvenRef = ::isEven
    val isEvenLambda = { i: Int -> isEven(i) }
    println(isEvenRef == isEvenLambda)
}

fun showFunctionReference() {
    println(::compareFunctionReferenceWithLambda)
}