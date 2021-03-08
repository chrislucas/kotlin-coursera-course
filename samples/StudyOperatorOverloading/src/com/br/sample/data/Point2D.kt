package com.br.sample.data

data class Point2D(val x: Double, val y: Double)

operator fun Point2D.plus(q: Point2D) = Point2D(this.x + q.x,  this.y + q.y)

operator fun Point2D.minus(q: Point2D) = Point2D(this.x - q.x,  this.y - q.y)

operator fun Point2D.inc() = Point2D(this.x + 1.0,  this.y + 1.0)

operator fun Point2D.dec() = Point2D(this.x - 1.0,  this.y - 1.0)

operator fun Point2D.unaryMinus() = Point2D(-this.x,  -this.y)

operator fun Point2D.unaryPlus() = Point2D(-this.x,  -this.y)