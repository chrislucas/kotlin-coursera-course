package com.br.playground3.fntype

import java.util.concurrent.BlockingDeque
import java.util.concurrent.LinkedBlockingDeque
import java.util.concurrent.ThreadPoolExecutor
import java.util.concurrent.TimeUnit

// https://lankydan.dev/calling-java-functional-interfaces-from-kotlin
// https://kotlinlang.org/docs/java-interop.html#using-jni-with-kotlin

fun runningThreadSample() {
    // construtor Thread(Runnable)
    // a lambda escrita eh uma instancia de runnable
    val thread = Thread { println(0xff) }
    thread.start()
}

fun runningThreadPoolExecutor() {
    val queue = LinkedBlockingDeque<Runnable>()
    val executor = ThreadPoolExecutor(1
        , 4
        , 3000
        , TimeUnit.MILLISECONDS
        , queue
    )

    executor.execute {
        println("Executando uma Thread Pool")
    }
}

fun main() {
    runningThreadSample()
    runningThreadPoolExecutor()
}