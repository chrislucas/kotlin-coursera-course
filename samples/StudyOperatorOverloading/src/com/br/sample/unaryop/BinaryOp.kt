package com.br.sample.unaryop

import com.br.sample.data.MutablePoint2D
import com.br.sample.data.plus
import com.br.sample.data.plusAssign
import com.br.sample.data.unaryMinus


fun samplePlusAssignment() {
    val p = MutablePoint2D(2.0, 3.75)
    val q = -p
    p += q
    println("P: $p")
}

fun samplePlus() {
    val p = MutablePoint2D(2.0, 3.75)
    val q = -p
    println("${p + q}")
}

fun main() {
    samplePlus()
}