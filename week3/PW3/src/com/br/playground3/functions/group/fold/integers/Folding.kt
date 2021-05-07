package com.br.playground3.functions.group.fold.integers

import com.br.playground3.exts.log
import com.br.playground3.exts.toTypedArray
import com.br.playground3.functions.group.grouping


/**
 * https://kotlinlang.org/docs/collection-grouping.html
 * https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/fold.html
 * https://kotlinlang.org/docs/collection-aggregate.html#fold-and-reduce
 * */

val INTERVAL = (0..100)

private fun groupingNumberByModuloX1(m: Int) {

    /**
     * Agrupar os valores no interval (val interval) [s, e] em grupos
     * cuja a chave e o resto da divisa por m
     * */
    val group: Grouping<Int, Int> = INTERVAL
        .groupingBy int@{ int -> int % m }

    /**
     * Agrupa os elementos A partir de uma instancia de Grouping pela chave, a qual eh fornecida pela funcao
     * keyOf() e aplica uma operacao em cada elemento de cada grupo de Grouping sequencialmente.
     *
     * Essa operacao eh a funcao lambda passada por argumento para funcao fold. Ela recebe
     * 2 argumentos, um acumulador do tipo Generico R que eh o valor acumulado da operacao i-1
     * (no caso da primeira operacao trata-se do valor definido por initialValue) e uma funcao
     * lambda (operation) que eh a operacao que sera aplicada em cada valor de cada grupo
     *
     * Nota:
     * 1) O valor inicial em cada grupo sera o mesmo
     * 2) A cada operacao executada a funcao lambda passada na funcao groupingBy tambem eh chamada
     * transformando passando como argumento o valor da variavel currentValue
     *
     * */
    val mapOfSum = group.fold(initialValue = 0) { acc: Int, currentValue: Int ->
        acc + currentValue
    }

    mapOfSum.log {
        for ((k, v) in this) {
            println("$k, $v")
        }
    }
}

private fun groupingNumberByModuloX2(m: Int) {

    val group: Grouping<Int, Int> = INTERVAL.toTypedArray()
        .grouping int@{ int -> int % m }

    val fromKeyToValue: (Int, Int) -> (Pair<Int, Pair<Int, MutableList<Int>>>) =
        { key: Int, _: Int -> Pair(key, Pair(0, mutableListOf())) }

    group.fold(fromKeyToValue) { key, acc: Pair<Int, Pair<Int, MutableList<Int>>>, currentValue: Int ->
        acc.run {
            val (sum, list) = second
            list.add(currentValue)
            Pair(key, Pair(sum + currentValue, list))
        }
    }.log {
        for ((k, v) in this) {
            println("$k, $v")
        }
    }
}

private fun groupingNumberByModuloX3(m: Int) {
    /**
     * Agrupar os valores no interval (val interval) [s, e] em grupos
     * cuja a chave e o resto da divisa por m
     * */
    val group: Grouping<Int, Int> = INTERVAL.toTypedArray()
        .grouping int@{ int -> int % m }

    val map = group.fold(initialValue = mutableListOf()) { acc: MutableList<Int>, e: Int ->
        acc.apply { add(e) }
    }

    map.log {
        println("F($m) -> Map.size: $size")
        val message = StringBuilder()
        forEach { (k, v) ->
            val groupValues = StringBuilder()
            groupValues.append("[")
            v.forEachIndexed { index, e ->
                val data = if (index > 0) {
                    ", $e"
                } else {
                    e
                }
                groupValues.append(data)
            }
            groupValues.append("]")
            message.append("$k:$groupValues")
        }
        println(message)
    }
}

/**
 * Testando a funcao fold cuja assinatura recebe um objeto como
 * initialValue
 * */
private fun groupingNumberByModuloX4(m: Int) {
    val info = INTERVAL.groupingBy { it % m }.fold(listOf<Int>()) { acc, e ->
        acc.apply { acc + e }
    }
    info.log()
}


fun main() {
    for (i in 10 until 11) {
        groupingNumberByModuloX1(i)
        groupingNumberByModuloX2(i)
        groupingNumberByModuloX3(i)
        groupingNumberByModuloX4(i)
    }

}