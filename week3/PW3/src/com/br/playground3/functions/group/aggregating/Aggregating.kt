package com.br.playground3.functions.group.aggregating

import com.br.playground3.exts.log
import com.br.playground3.exts.toTypedArray
import com.br.playground3.functions.group.grouping


/**
 * https://kotlinlang.org/docs/collection-grouping.html
 * */

private fun groupingNumberByModuloX(modX: Int) {

    val group: Grouping<Int, Int> = (0..100).toTypedArray()
        .grouping int@{ int -> int % modX }

    // aplica uma operacao (aggregate) em cada elemento de cada grupo
    // e agrupa num mapa <K, R> onde K eh a chave definida pelo Grouping
    // e R e o tipo retornado pela funcao lambda passada para aggregate
    //.aggregate { key: K, accumulator: R?, element: T, first ->  }
/*
    fun ag(_none: Int, mutableList: MutableList<Int>?, e: Int, __none: Boolean): MutableList<Int>? {
        return mutableList?.apply { this.add(e) }
    }
 */
    //group.aggregate(::ag).log()
    val map = group.aggregate { _, list: MutableList<Int>?, element, _ ->
        list?.apply { add(element) } ?: mutableListOf(element)
    }
    map.log()
}

private fun groupingByModuloX(modX: Int) {
    val interval = (0..100)
    val map: Map<Int, StringBuilder?> = interval.toTypedArray()
        // a funcao lambda passada para grouping sera chamada toda a vez que a funcao
        // lambda passara para funcao aggregate tbm for
        .grouping int@{ int -> int % modX }
        .aggregate { _, buffer: StringBuilder?, e, first ->
            if (first) {
                StringBuilder("$e")
            } else {
                buffer?.apply {
                    this.append(", $e")
                }
            }
        }
    println("Interval: $interval, ModularFunction($modX)\n$map")
}

fun main() {
    for (i in 24..25) {
        groupingByModuloX(i)
    }
}