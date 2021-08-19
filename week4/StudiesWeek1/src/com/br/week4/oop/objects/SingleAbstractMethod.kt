package com.br.week4.oop.objects

/**
 * https://kotlinlang.org/docs/fun-interfaces.html#sam-conversions
 *
 * Functional Interface VS Type Alias
 *
 * https://www.baeldung.com/kotlin/sam-conversions
 * */

fun interface Listener {
    fun publish()
}

private fun testListener () {
    Listener { println(0xff) }.publish()
}

private fun executeListener(listener: Listener) {
    listener.publish()
}

private fun testExecuteListener() {
    executeListener {
        println("Ola ${0xff}, sou um teste de sintaxe de functional interface")
    }
}

interface Runner {
    fun invoke()
}

private fun executeRunner(runner: Runner) {
    runner.invoke()
}

private fun testExecuteRunner() {
    executeRunner(object : Runner {
        override fun invoke() {
            println("Ola ${0xff}, sou um teste de sintaxe de object expression com uma non - functional interface")
        }
    })
}

fun main() {
    testExecuteListener()
    testExecuteRunner()
}