package com.br.playground3.tpool

import java.util.concurrent.LinkedBlockingDeque
import java.util.concurrent.ThreadPoolExecutor
import java.util.concurrent.TimeUnit


fun runningThreadPoolExecutor() {
    val queue = LinkedBlockingDeque<Runnable>()
    val executor = ThreadPoolExecutor(1
        , 4
        , 3000
        , TimeUnit.MILLISECONDS
        , queue
    )

    // SAM Conversion
    executor.execute {
        println("Executando uma Thread Pool")
    }
}

fun main() {

}