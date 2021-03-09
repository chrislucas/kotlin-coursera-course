package com.br.sample.unaryop

import com.br.sample.data.Point2D
import com.br.sample.data.unaryMinus
import com.br.sample.data.unaryPlus

// https://www.baeldung.com/kotlin/operator-overloading#overloading-for-unary-operations


class Shape {
    val points: MutableList<Point2D> = mutableListOf()

    operator fun Point2D.unaryPlus() {
        points.add(this)
    }

    override fun toString(): String {
        return "Shape: $points"
    }
}

// uma outra forma de socrecarregar um operador de uma classe
// Compound Assigments: https://www.baeldung.com/kotlin/operator-overloading#4-compoundassignments
operator fun Shape.plusAssign(point2D: Point2D) {
    points.add(point2D)
}


fun shape(init: Shape.() -> Unit): Shape {
    return Shape().run {
        this.init()
        this
    }
}

fun buildShape() {
    val shape = shape {
        +Point2D(1.0, 2.0)
        +Point2D(3.0, 2.0)
        +Point2D(3.0, 4.0)
        +Point2D(3.0, 2.5)
        +Point2D(1.0, 2.0)
        +(-Point2D(1.0, 2.0))
    }

    println(shape)

    shape += Point2D(321.23, 2.123)

    println(shape)
}

private fun sampleUnaryPlus() {
    val p = +Point2D(2.0, 3.5)
    println(p)
}

fun main() {
    buildShape()
}