package com.br.scopefun.predicate

import com.br.scopefun.models.Point2Df


/**
 * https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/take-if.html
 *
 * inline fun <T> T.takeIf(predicate: (T) -> Boolean): T?
 *
 * Retorna a propria referencia caso a funcao predicate retornne true do contrario retornara nulo
 *
 * https://kotlinlang.org/docs/scope-functions.html#takeif-and-takeunless
 * inline fun <T> T.takeUnless(predicate: (T) -> Boolean): T?
 *
 * retorna o this se a funcao predicate retornar false
 * */


fun main() {
    val p: Point2Df? =  Point2Df(2.0, .1).takeIf { it.x > 2.0 }
    val origin = Point2Df(0.0, 0.0)
    val q = p.takeUnless{ it == origin }
    println(q)
}