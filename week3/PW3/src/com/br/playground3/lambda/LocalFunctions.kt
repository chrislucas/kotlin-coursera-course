package com.br.playground3.lambda

import com.br.playground3.log


fun mapWithLocalFunction(list: List<Int>, fn: (Int) -> Boolean): List<Int> {
    fun transform(e: Int): List<Int> {
        return if (fn(e)) {
            listOf()
        } else {
            listOf(e, e)
        }
    }

    // so para mostrar que eh possivel usar os returns dentro das clausulas condicionais
    fun anotherTransformationFun(e: Int): List<Int> {
        if (fn(e)) { return listOf() } else { return listOf(e, e) }
    }

    return list.flatMap(::transform)
}

fun mapWithAnonymousFunction(list: List<Int>, fn: (Int) -> Boolean): List<Int> {
    // usando uma funcao anonima
    // na essencia, anon function e lambdas sao a mesma coisa, a primeira
    // e simplesmente uma sintaxe alternativa para a segunda e
    // os bytecodes gerados pelo compilador saosmo
    val transform = fun(i: Int) :List<Int> {
        return if(fn(i)) {
            listOf()
        } else {
            listOf(i, i)
        }
    }
    println("Anon fun: $transform")

    // curiosamente ao passar a anon fun para flatMap
    // (anonymous function in place) eu nao
    // preciso definir o tipo do argumento dela, ocorre uma inferencia
    // do compilador, pelo q me parece
    return list.flatMap(fun(i) :List<Int> {
        return if(fn(i)) {
            listOf()
        } else {
            listOf(i, i)
        }
    })
}

fun main() {
    mapWithLocalFunction(listOf(3, 0, 1)) { it == 0 }.log()
    mapWithAnonymousFunction(listOf(3, 0, 1)) { it == 0 }.log()
}