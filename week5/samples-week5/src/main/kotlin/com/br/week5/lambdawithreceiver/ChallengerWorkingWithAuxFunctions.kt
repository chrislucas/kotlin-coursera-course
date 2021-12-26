package com.br.week5.lambdawithreceiver

/*
    https://www.coursera.org/learn/kotlin-for-java-developers/ungradedWidget/SVZzI/kotlin-playground-working-with-auxiliary-functions
 */

interface X {
    var first: Int
    var second: Int
    var third: Int
}

interface Y {
    fun start()
    fun finish()
}

interface Z {
    fun init()
}

class DefaultImplX(override var first: Int = 0, override var second: Int = 0, override var third: Int = 0) : X

class DefaultImplY: Y {
    override fun start() = println("$this - start()")
    override fun finish() = println("$this - finish()")
}

class DefaultImplZ: Z {
    override fun init() = println("$this - init()")
}

fun foo(x: X, y: Y?, z: Z) {
    x.let {
        it.first = 1
        it.second = 2
        it.third = 3
    }

    y?.let {
        with(it) {
            start()
            finish()
        }
    }
    val result = with(z) {
        init()
        this
    }
}

private fun simplifyFoo(x: X, y: Y?, z: Z) {
    with(x) {
        first = 1
        second = 2
        third = 3
    }

    y?.run {
        start()
        finish()
    }

    val result = z.apply { init() }
}

fun main() {
    simplifyFoo(DefaultImplX(), DefaultImplY(), DefaultImplZ())
}