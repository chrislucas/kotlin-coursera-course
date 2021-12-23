package com.br.week5.lambdawithreceiver

/*
    Podemos adicionar tanto ext fun e lambdas a variaveis
    https://www.coursera.org/learn/kotlin-for-java-developers/lecture/FNQGO/lambda-with-receiver
 */

val lambdaWithReceiver: (Int, (Int) -> Boolean) -> Boolean = { x, fn -> fn(x) }

val extensionFunction: Int.((Int) -> Boolean) -> Boolean = { fn -> fn(this) }


private fun checkLambdaWithReceiver() {
    println(lambdaWithReceiver(10) { it and 1 == 0 })
}

private fun checkExtensionFunction() {
    println(10.extensionFunction { it and 1 == 0 })
}


fun main() {
    checkLambdaWithReceiver()
}