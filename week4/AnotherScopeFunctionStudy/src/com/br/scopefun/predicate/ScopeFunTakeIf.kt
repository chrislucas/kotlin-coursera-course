package com.br.scopefun.predicate

import com.br.scopefun.models.Point2Df

/**
 * https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/take-if.html
 *
 * inline fun <T> T.takeIf(predicate: (T) -> Boolean): T?
 *
 * Retorna a propria referencia caso a funcao predicate retornne true do contrario retornara nulo
 *
 * */


private fun Point2Df.check() = x == 0.0 && y == 0.0

private fun isZeroPoint(p: Point2Df): Boolean = p.run { x == 0.0 && y == 0.0 }

private fun test1() {
    val p = Point2Df(1.0, 1.0).takeIf(::isZeroPoint)
    println(p)
}

fun main() {
    test1()
}