package com.br.week4.properties.exec

class UnstableImmutableProperty {
    private var mutableInt = 0
    val immutableIntUnstable: Int
        get() = ++mutableInt
}

fun main() {
    val unstable = UnstableImmutableProperty()
    println(unstable.immutableIntUnstable)
    println(unstable.immutableIntUnstable)
    println(unstable.immutableIntUnstable)
}