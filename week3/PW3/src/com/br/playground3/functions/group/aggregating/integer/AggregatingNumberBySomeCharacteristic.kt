package com.br.playground3.functions.group.aggregating.integer

import com.br.playground3.exts.log
import com.br.playground3.functions.group.frequecy

private fun classify(iterable: Iterable<Int>, transform: (Int) -> Int) {

    val classifying = { k: Int -> transform(k) }

    /**
     * A funcao frequency chama a extension function aggregate. Ela por sua
     * vez chama a funcao aggregateTo. Se olharmos a implementacao
     * dessa segunda funcao vamos notar algumas coisas interessantes
     *     1) ela recebe como argumento uma MutableMap<K, R> onde R eh o tipo do objeto acumulador
     *     2) internamente a funcao itera pelos elementos do objeto grouping e a cada elemento
     *     recupera a chave desse elemento atraves da funcao keyOf e verifica se o mapa possui essa chave
     *          2.1) O valor de cada chave do mapa eh um objeto acumulador
     *          se a chave nao existe o objeto acumulador nao existe e essa eh uma das condicoes
     *          para que a a variavel first seja TRUE a outra condicao eh se o mapa nao possuir uma chave
     *          cujo valor eh igual a chamada da funcao keyOf do objeto aggregator
     *
     *  Saber esses pontos eh importante para saber como a funcao aggregator funciona e como ela define
     *  o valor do argumento "first" que recebemos na nossa lambda
     * */
    val builder = { _: Int, acc: MutableList<Int>?, element: Int, _: Boolean ->
        acc?.apply { add(element) } ?: mutableListOf<Int>().apply {
            add(element)
        }
    }
    val map = iterable.frequecy(classifying, builder)
    map.log()
}

fun main() {
    classify( 0 .. 10000) { it % 20 }
}