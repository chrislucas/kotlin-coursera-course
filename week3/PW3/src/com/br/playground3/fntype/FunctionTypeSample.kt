package com.br.playground3.fntype

import com.br.playground3.fp.wrapperFunction

// lambda expression
val wrappingLambda = { x: Int, y: Int, fn: (Int, Int) -> Int
    ->
    fn(x, y)
}

// function type
val wrappingFunction: (Int, Int, (Int, Int) -> Int) -> Int =
    { x, y, function -> function(x, y) }

val isEven = { i: Int -> i % 2 == 0 }

fun callDirectlyLambdas(x: Int, y: Int) {
    // val mod = { x: Int, y: Int -> x % y } (x, y)

    //val mod = run { x % y }
}
val aNullableFunction: (() -> Int)? = null
val aFunctionThatReturnsNullSometime: () -> Int? = { null }

fun main() {
    println("$wrappingLambda\n$wrapperFunction\n${wrappingLambda == wrappingFunction}")
    println(isEven)
    // como chamar com seguranca uma funcao que pode ser nullable
    // atraves do operator ?
    aNullableFunction?.invoke()
}