package com.br.playground3.collectionfun.map


private fun groupByToWith1Lambda() {
    val range = 1..1000
    val map = mutableMapOf<Boolean, MutableList<Int>>()
    fun keySelector(value: Int) = value and 1 == 0 //value % 2
    range.groupByTo(map, ::keySelector)
    println(map)
}

/**
 *
 * A funcao groupBy utiliza a funcao groupByTo, entao para entender o comportamento
 * da primeiro precisamos estudar o comportamento da segunda
 * */

private fun groupByToWith2Lambdas() {
    val range = 1..1000
    val map = mutableMapOf<Boolean, MutableList<Int>>()
    // a funcao keySelector deve retornar o mesmo tipo da chave do mapa que iremos armazenar os dados
    val generateKeyByIsEvenFunction: (Int) -> Boolean = { n -> n and 1 == 0 }
    val valueTransform: (Int) -> Int = { it }  // nao aplica nenhuma transformacao nos valores da colecao range
    range.groupByTo(map, generateKeyByIsEvenFunction, valueTransform)
    println(map)
}

fun main() {
    groupByToWith2Lambdas()
    groupByToWith1Lambda()
}