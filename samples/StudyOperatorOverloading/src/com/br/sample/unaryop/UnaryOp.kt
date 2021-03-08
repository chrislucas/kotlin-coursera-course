package com.br.sample.unaryop

import com.br.sample.data.*

fun inc() {
    var p = Point2D(2.0, 3.0)
    // aqui a operacao inc() eh feita antes de definir a variavel q
    val q = ++p
    println("$p, $q")
    // aqui a operacao r = p e feita primeiro do que a inc()
    val r = p++
    println("$r, $p")
}

fun dec() {
    var p = Point2D(2.0, 3.0)
    // aqui a operacao dec() eh feita antes de definir a variavel q
    val q = --p
    println("$q, $p")
    // aqui a operacao r = p e feita primeiro do que a dec()
    val r = p--
    println("R: $r, P: $p")
}


fun unaryPlus() {
    val p = Point2D(-2.0, -3.0)
    val q = +p
    println("$p, $q")
}

fun unaryMinus() {
    val p = Point2D(2.0, 3.0)
    val q = -p
    println("$p, $q")
}

fun sampleUnaryOpInteger() {
    val p = 10
    val q = +p
    println("$p, $q")
    val r = -p
    println("$r, $p")
    val s = +r
    println("$s, $r")
}

fun main() {
    sampleUnaryOpInteger()
}