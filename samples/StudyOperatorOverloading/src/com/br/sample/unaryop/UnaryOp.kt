package com.br.sample.unaryop

import com.br.sample.data.*

private fun inc() {
    var p = Point2D(2.0, 3.0)
    // aqui a operacao inc() eh feita antes de definir a variavel q
    val q = ++p
    println("Q: $q, P: $p")
    // aqui a operacao r = p e feita primeiro do que a inc()
    val r = p++
    println("R: $r, P: $p")
}

private fun dec() {
    var p = Point2D(2.0, 3.0)
    // aqui a operacao dec() eh feita antes de definir a variavel q
    val q = --p
    println("Q: $q, P: $p")
    // aqui a operacao r = p e feita primeiro do que a dec()
    val r = p--
    println("R: $r, P: $p")
}


private fun unaryPlus() {
    val p = Point2D(-2.0, -3.0)
    val q = +p
    println("$p, $q")
}

private fun unaryMinus() {
    val p = Point2D(2.0, 3.0)
    val q = -p
    println("$p, $q")
}

private fun sampleUnaryOpInteger() {
    val p = 10
    val q = +p
    println("$p, $q")
    val r = -p
    println("$r, $p")
    val s = +r
    println("$s, $r")
}

fun flippingCoordinate() {
    val p = Point2D(2.0, 3.0)
    println("P: $p")
    val q = !p  // essa operacao nao altera o objeto
    println("Q: $q, P: $p")
}

fun main() {
    flippingCoordinate()
}