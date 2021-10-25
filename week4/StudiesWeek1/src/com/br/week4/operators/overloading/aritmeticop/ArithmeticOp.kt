package com.br.week4.operators.overloading.aritmeticop

import com.br.week4.operators.overloading.GeoPoint2D

/**
 * + plus
 * += plusAssign
 * - minus
 * * times
 * / div
 * % mod - rem
 * */

operator fun GeoPoint2D.plus(other: GeoPoint2D): GeoPoint2D = GeoPoint2D(x + other.x, y + other.y)

// Nao faz sentido para uma classe com atributos imutaveis
operator fun GeoPoint2D.plusAssign(value: Int) {
    GeoPoint2D(x + value, y + value)
}

operator fun GeoPoint2D.minus(other: GeoPoint2D): GeoPoint2D = GeoPoint2D(x - other.x, y - other.y)

operator fun GeoPoint2D.times(scale: Double): GeoPoint2D = GeoPoint2D(x * scale, y * scale)

operator fun GeoPoint2D.times(scale: Int): GeoPoint2D = GeoPoint2D(x * scale, y * scale)

operator fun GeoPoint2D.div(scale: Double): GeoPoint2D = GeoPoint2D(x / scale, y / scale)

operator fun GeoPoint2D.rem(scale: Int): GeoPoint2D = GeoPoint2D(x % scale, y % scale)



private fun checkPlusAssignOperator() {
    val p = GeoPoint2D(2.0, 3.0)
    p += 1
    println(p)
}

private fun check() {
    println(GeoPoint2D(1.0, 2.0) + GeoPoint2D(-1.0, -1.5))
    println(GeoPoint2D(1.0, 2.0) - GeoPoint2D(-1.0, -1.5))
    println(GeoPoint2D(1.0, 2.0) * 10.5)
    println(GeoPoint2D(1.0, 2.0) * 10)
}


fun main() {
    checkPlusAssignOperator()
}