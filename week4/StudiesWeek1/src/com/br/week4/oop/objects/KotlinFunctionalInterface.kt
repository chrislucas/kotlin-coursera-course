package com.br.week4.oop.objects

/**
 * https://www.baeldung.com/kotlin/sam-conversions#functional-interfaces
 * */

fun interface Transformer<P, R> {
    fun transform(value: P): R
}

private fun testTransformer() {
    println(Transformer<Int, Boolean> { it and 1 == 0 }.transform(15))
}

private fun <P, R> testLambdaTransformer(value: P, fn: (P) -> R) {
    println(fn(value))
}

private fun <P, R> testFunctionalInterfaceTransformer(value: P, fn: Transformer<P, R>) {
    println(fn.transform(value))
}

fun main() {
    testLambdaTransformer(10) { it % 2 == 0 }
    // podemoe usar uma lambda ao inves de uma implementacao da function interface
    testFunctionalInterfaceTransformer(10) { it % 2 == 0 }
    // passando uma implrementacao anonima
    testFunctionalInterfaceTransformer(15, Transformer<Int, Boolean> { it % 2 == 0 })
}