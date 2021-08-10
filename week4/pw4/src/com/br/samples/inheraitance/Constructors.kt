package com.br.samples.inheraitance

open class Base(val value: String) {
    init {
        println("Base ${javaClass.name}")
    }
}

class SubBase: Base {
    constructor(value: String) : super(value)
    init {
        println("Subclass ${javaClass.name}")
    }
}

class AnotherSubBase(value: String): Base(value)


fun main() {
    SubBase("test")
}