package com.br.week4.operators.overloading

import com.br.week4.oop.clazz.modifiers.data.Point2f

/**
 *
 * - unaryMinys
 * - unaryPlus
 * ! not
 * ++a, a++ inc
 * --a, a-- dec
 *
 * */

operator fun GeoPoint2D.unaryMinus() = GeoPoint2D(-x, -y)

operator fun GeoPoint2D.unaryPlus() = GeoPoint2D(if (x < 0) -x else x, if (y < 0) -y else y)

operator fun GeoPoint2D.inc() = GeoPoint2D(x + 1, y + 1)

operator fun GeoPoint2D.dec() = GeoPoint2D(x - 1, y - 1)

private fun testIncPrefix() {
    var p = GeoPoint2D(0.0,0.0)
    println("++p = ${++p}")
    var q = p
    println("q = $q")
    var r = ++q
    println("after ++q = $q")
    println("++r after r = ++q ${++r}")
}

private fun testIncSuffix() {
    var p = GeoPoint2D(0.0,0.0)
    println("p++ = ${p++}") // usa o valor de p no print e depois aplica a operacao inc()
    var q = p
    println("after p++ e q = p -> q = $q e p = $p")
    var r = q++ // define o valor de q a r e depois aplica a operacao inc()
    println("after r = q++ -> r = $r e q = $q")
    println("r++ = ${r++}") // idem ao que ocorre no print de $p++
    println("after r++ $r")
}

private fun testDecPrefix() {
    var p = GeoPoint2D(0.0,0.0)
    println("--p = ${--p}") // aplica a operacao dec() na propria variavel e a funcao print utiliza esse valor
    var q = p
    println("q = $q")
    var r = --q // aplica a funcao dec antes de fazer o assignemnt
    println("after --q = $q")
    println("r = $r")
    println("--r ${--r}")   // idem ao que ocorre em println $--p
}

private fun testDecSuffix() {
    var p = GeoPoint2D(0.0,0.0)
    println("p-- = ${p--}") // usa o valor de p no print e depois aplica a operacao dec()
    var q = p
    println("after p-- e q = p -> q = $q e p = $p")
    var r = q-- // primeiro defini r com o valor de q e  depois aplica a operacao dec()
    println("after r = q-- -> r = $r e q = $q") // aqui r eh diferente de q obviamente
    println("r-- = ${r--}") // idem ao que ocorre no print de $p--
    println("after r-- $r")

}

private fun testUnary() {
    println(-GeoPoint2D(1.0, -2.0))
    println(+GeoPoint2D(-1.0, -2.0))
}


fun main() {
    //testIncPrefix()
    //testIncSuffix()
    //testDecPrefix()
    testDecSuffix()
}