package com.br.samples.methodref

private fun <T, R> apply(arg: T, fn: (T) -> R): R = fn(arg)

private fun isOdd(int: Int) = int and 1 == 1

val checkIsOdd: (Int) -> Boolean = { x -> x and 1 == 1 }

// anonymous fun
val powerOf2 = fun(x: Int): Int = 1 shl x

private fun checkApply() {
    println(apply(11, ::isOdd))
    println(apply(11, checkIsOdd))
    println(apply(11, powerOf2))
    println(apply(12, ::isOdd))
}


fun main() {
    checkApply()
}