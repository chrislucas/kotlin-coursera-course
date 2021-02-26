package com.br.playground3.lambda

val anonIntPredicate = fun(i: Int, check: (Int) -> Boolean) = check(i)

val lambdaIntPredicate = { i: Int, check: (Int) -> Boolean -> check(i) }

// https://kotlinlang.org/docs/lambdas.html#lambda-expression-syntax
// definindo o tipo da funcao - lambda expression na sua forma sintatica compleca
val fullSintaxIntPredicate: (Int, (Int) -> Boolean) -> Boolean = { i, check -> check(i) }

fun main() {
    println(anonIntPredicate::class)
    println(lambdaIntPredicate::class)
    println(fullSintaxIntPredicate::class)

    println(anonIntPredicate::class == lambdaIntPredicate::class)
    println(lambdaIntPredicate::class == fullSintaxIntPredicate::class)
}