package com.br.week4.properties.model

import kotlin.math.abs


const val EPS = 10e-9

fun Double.almostEquals(that: Double, eps: Double): Boolean = abs(this - that) < eps

/**
 * Exemplo de classe com propriedades que nao utilizam 'field' s
 * */
class Rectangle(private val height: Double, private val width: Double, private val eps: Double = EPS) {
    val isSquare: Boolean
        get() {
            return height.almostEquals(width, eps)
        }
}

private fun test(pair: Pair<Double, Double>) {
    val rect = Rectangle(pair.first, pair.second)
    println(rect.isSquare)
}

fun main() {
    println(String.format("%.10f", EPS))
    arrayOf(
        10.0 to 10.00004,
        10.0 to 10.00001,
        10.0 to 10.0001,
        10.0 to 10.000004,
        10.0 to 10.00000002,
        10.0 to 10.000000002,
        10.0 to 10.5,
    ).forEach {
        test(it)
    }

}