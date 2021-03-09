package com.br.sample.data

import kotlin.math.abs

data class Point2D(val x: Double, val y: Double)

operator fun Point2D.plus(q: Point2D) = Point2D(this.x + q.x,  this.y + q.y)

operator fun Point2D.minus(q: Point2D) = Point2D(this.x - q.x,  this.y - q.y)

operator fun Point2D.inc() = Point2D(this.x + 1.0,  this.y + 1.0)

operator fun Point2D.dec() = Point2D(this.x - 1.0,  this.y - 1.0)

operator fun Point2D.unaryMinus() = Point2D(-this.x,  -this.y)

operator fun Point2D.unaryPlus() = Point2D(-this.x,  -this.y)

operator fun Point2D.times(scale: Int) = Point2D(x * scale, y * scale)

operator fun Point2D.times(scale: Double) = Point2D(x * scale, y * scale)

operator fun Point2D.plus(scale: Double) = Point2D(x + scale, y + scale)

operator fun Int.plus(p: Point2D) = Point2D(this + p.x, this + p.y)

operator fun Int.times(p: Point2D) = Point2D(this * p.x, this * p.y)

// ainda nao sei se essa operacao faz sentido do ponto de vista da geometria, mas a titulo de curiosidade
operator fun Point2D.times(q: Point2D) = Point2D(x * q.x, y * q.x)


 fun Double.almostEquals(other: Double, eps: Double = 1E-6) = abs(this - other) < eps

val comparatorByX = Comparator<Point2D> {
        p, q ->
        when {
            p.x.almostEquals(q.x) -> {
                0
            }
            abs(p.x - q.x) < 0 -> {
                -1
            }
            else -> {
                1
            }
        }
}


// trocando (flipping) a coordenada
operator fun Point2D.not() = Point2D(y, x)