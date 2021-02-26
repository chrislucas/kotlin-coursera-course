package com.br.playground3.fntype

// SAM

fun execute(execution: Execution) {
    execution.execute()
}

fun runner(runnable: Runnable) {
    runnable.run()
}

fun call(calleable: Calleable) {
    calleable.call()
}

fun main() {
    execute {
       println(0xff)
    }

    runner {
        println(0xff)
    }
    // funciona a partir do kotlin 1.4
    // marcando a interface Calleable como functional interface
    call { println(0xff) }
}