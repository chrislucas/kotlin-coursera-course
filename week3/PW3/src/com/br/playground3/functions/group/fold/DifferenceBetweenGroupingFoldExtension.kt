package com.br.playground3.functions.group.fold

import com.br.playground3.exts.log

/**
 * Um sample para brincar com a diferenca das funcoes

    Grouping<T, K>.fold(
        initialValueSelector: (key: K, element: T) -> R,
        operation: (key: K, accumulator: R, element: T) -> R
    )

    Grouping<T, K>.fold(
        initialValue: R,
        operation: (accumulator: R, element: T) -> R
    )

    O que vai diferenciar o resultado final das duas funcoes eh como o valor inicial do acumulador eh definido
    A primeira funcao que recebe uma lambda initialValueSelector: (key: K, element: T) -> R gera um
    objeto de valor inicial para cada grupo, a segunda define um unico acumulador para todos os grupos.

    Citando a doc
    Da primeira funcao
    initialValueSelector:(key: K, element: T) -> R ->
    "A function that provides an initial value of accumulator for each group


    Da segunda funcao
    initialValue: R ->
    An initial value of accumulator is the same initialValue for each group."

    Nota: Ainda nao sei bem como usar essas funcoes em exemplos mais concretos
 * */


val PHRASE = ("a function that provides an initial value of accumulator for each group." +
        " It's invoked with parameter a function that is invoked on each element with the following parameters").split(" ")

private fun sampleWhereSameInstanceAccIsReturned() {
    val operation = { acc: MutableSet<String>, e: String ->
        acc.apply { add(e) }
    }
    val info = PHRASE.fold(mutableSetOf(), operation)
    info.log()
    val mapInfo = PHRASE.groupingBy { it.first() }.fold(mutableSetOf(), operation)
    mapInfo.log()
}

private fun sampleWhereDifferentInstanceOfAccIsReturned() {
    val createAccumulator = { _: Char, s: String -> mutableSetOf<String>() }
    val operation = { _: Char, acc: MutableSet<String>, element: String ->
        acc.apply { add(element) }
    }
    val info = PHRASE.groupingBy { it.first() }.fold(createAccumulator, operation)
    info.log()
}


fun main() {
    sampleWhereSameInstanceAccIsReturned()
    sampleWhereDifferentInstanceOfAccIsReturned()
}