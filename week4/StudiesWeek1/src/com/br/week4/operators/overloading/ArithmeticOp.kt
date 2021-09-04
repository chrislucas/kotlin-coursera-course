package com.br.week4.operators.overloading

/**
 * + plus
 * - minus
 * * times
 * / div
 * % mod
 * */

operator fun GeoPoint2D.plus(other: GeoPoint2D):  GeoPoint2D = GeoPoint2D(x + other.x, y + other.y)

operator fun GeoPoint2D.minus(other: GeoPoint2D):  GeoPoint2D = GeoPoint2D(x - other.x, y - other.y)

operator fun GeoPoint2D.times(scale: Double):  GeoPoint2D = GeoPoint2D(x * scale, y * scale)

operator fun GeoPoint2D.times(scale: Int):  GeoPoint2D = GeoPoint2D(x * scale, y * scale)


fun main() {
    println(GeoPoint2D(1.0, 2.0) + GeoPoint2D(-1.0, -1.5))
    println(GeoPoint2D(1.0, 2.0) - GeoPoint2D(-1.0, -1.5))
    println(GeoPoint2D(1.0, 2.0) * 10.5)
    println(GeoPoint2D(1.0, 2.0) * 10)
}