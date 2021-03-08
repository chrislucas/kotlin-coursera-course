package com.br.sample.unaryop

import com.br.sample.data.Point2D
import com.br.sample.data.unaryMinus

// https://www.baeldung.com/kotlin/operator-overloading#overloading-for-unary-operations


class Shape {
    val points: MutableList<Point2D> = mutableListOf()
    operator fun Point2D.unaryPlus() {
        points.add(this)
    }
}

fun shape(init: Shape.() -> Unit): Shape {
    return Shape().run {
        this.init()
        this
    }
}

fun add() {
    val shape = shape {
        +Point2D(1.0, 2.0)
        +Point2D(3.0, 2.0)
        +Point2D(3.0, 4.0)
        +Point2D(3.0, 2.5)
        +Point2D(1.0, 2.0)
        +(-Point2D(1.0, 2.0))
    }

    println(shape.points)
}

fun main() {
    add()
}