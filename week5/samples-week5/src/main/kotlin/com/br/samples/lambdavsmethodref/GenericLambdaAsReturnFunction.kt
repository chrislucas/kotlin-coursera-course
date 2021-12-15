package com.br.samples.lambdavsmethodref

/*
    https://stackoverflow.com/questions/44880442/how-to-write-lambdas-with-generics-in-kotlin
 */


// tentando entender esse exemplo hehehe
fun <T> gen(): (T) -> T = { it }

fun check(f: (Int) -> Int) = f(1) + 2

fun main() {

    println(check(gen()))
}