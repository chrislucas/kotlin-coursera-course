package com.br.samples.inheraitance

open class A {
    open val value = 1
    open val value2 = 1
    init {
        println("$value, $value2")
    }
}

class B: A() {
    override val value = 2
    override val value2: Int
        get() = 2
}

fun main() {
    B()
}