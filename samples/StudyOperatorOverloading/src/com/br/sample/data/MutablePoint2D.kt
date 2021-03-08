package com.br.sample.data

import kotlin.math.abs

data class MutablePoint2D(var x: Double, var y: Double) {
    companion object {
        const val EPS = 1E-06
    }
    private fun almostEquals(q: MutablePoint2D) =
        abs(x - q.x) < EPS &&  abs(y - q.y) < EPS

    override fun equals(other: Any?): Boolean = almostEquals(other as MutablePoint2D)
}

operator fun MutablePoint2D.plus(q: MutablePoint2D) = MutablePoint2D(this.x + q.x,  this.y + q.y)

operator fun MutablePoint2D.minus(q: MutablePoint2D) = MutablePoint2D(this.x - q.x,  this.y - q.y)

operator fun MutablePoint2D.inc() = MutablePoint2D(this.x + 1.0,  this.y + 1.0)

operator fun MutablePoint2D.dec() = MutablePoint2D(this.x - 1.0,  this.y - 1.0)

operator fun MutablePoint2D.unaryMinus() = MutablePoint2D(-this.x,  -this.y)

operator fun MutablePoint2D.unaryPlus() = MutablePoint2D(+this.x,  +this.y)

// https://kotlinlang.org/docs/operator-overloading.html#augmented-assignments

/**
 *  # comportamento do compilador na operacao plusAssign
 *  - Compilador procura por uma extension fun ou funcao membro definida na classe do tipo T onde
 *  ocorre a operacao.
 *      - Se ela existe
 *          - Verifica para garantir que essa funcao retorne Unit, do contrario reporta um erro
 *          - Gera o codigo para a operacao a.plusAssign(b)
 *      - Se nao, tenta gerar o codogo para a = a + b, incluindo uma checagem para validar que
 *      a + b resulta num subtipo de a
 * */

operator fun MutablePoint2D.plusAssign(q: MutablePoint2D) {
    val p = Point2D(this.x + q.x,  this.y + q.y)
    with(this ) {
        x = p.x
        y = p.y
    }
}

operator fun MutablePoint2D.minusAssign(q: MutablePoint2D) {
    val p = Point2D(this.x - q.x,  this.y - q.y)
    with(this ) {
        x = p.x
        y = p.y
    }
}