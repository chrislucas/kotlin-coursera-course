package com.br.week4.properties.model

import kotlin.math.abs


const val EPS = 10e-6

infix fun Double.almostEquals(that: Double):Boolean = abs(this - that) < EPS

/**
 * Exemplo de classe com propriedades que nao utilizando 'field'
 * */
class Rectangle(private val height: Double, private val width: Double) {
    val isSquare: Boolean
        get() {
            return height almostEquals width
        }
}