package com.br.week4.operators.overloading.assignmentop.mutable

import com.br.week4.operators.overloading.MutableGeoPoint2D

/*
    += plusAssign
    *= timesAssign
    /= divAssign
    %= remAssign
*/

operator fun MutableGeoPoint2D.plusAssign(scale: Double) {
    this.x += scale
    this.y += scale
}

operator fun MutableGeoPoint2D.plusAssign(other: MutableGeoPoint2D) {
    this.x += other.x
    this.y += other.y
}

operator fun MutableGeoPoint2D.plus(scale: Double)=
    MutableGeoPoint2D(x + scale, y + scale)

operator fun MutableGeoPoint2D.plus(other: MutableGeoPoint2D)=
    MutableGeoPoint2D(x + other.x, y + other.y)


operator fun MutableGeoPoint2D.timesAssign(scale: Double) {
    this.x *= scale
    this.y *= scale
}

operator fun MutableGeoPoint2D.divAssign(scale: Double) {
    this.x /= scale
    this.y /= scale
}

operator fun MutableGeoPoint2D.remAssign(scale: Double) {
    this.x %= scale
    this.y %= scale
}


private fun checkOperatorPlusAssignScaleValue() {
    val p = MutableGeoPoint2D(1.0, 6.5)
    p += 3.0
    println(p)
}

private fun checkOperatorPlusAssignMutablePoint() {
    val p = MutableGeoPoint2D(1.0, 6.5)
    p += MutableGeoPoint2D(1.0, 6.5)
    println(p)
}

private fun checkOperatorTimes() {
    val p = MutableGeoPoint2D(1.0, 6.5)
    p *= 3.0
    println(p)
}


fun main() {
    checkOperatorPlusAssignMutablePoint()
}
