package com.br.week4.properties.exec

import kotlin.random.Random

// como tornar uma variavel imutavel em uma variavel com valores mutaveis
//
var mutableInt = 0
val unstable1: Int
    get() = mutableInt++

val unstable2: Int
    get() = Random.nextInt()

var readOnlyValue: Int = 0xff
    private set

fun main() {
    println(readOnlyValue)
    println(readOnlyValue)
}