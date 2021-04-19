package com.br.playground3.functions.group

import com.br.playground3.exts.log
import com.br.playground3.exts.toTypedArray


/**
 * https://kotlinlang.org/docs/collection-grouping.html
 * groupBy(keySelector: (T) -> K)
 *
 * Funcao que agrupa uma colecao (Iterator) Iterator<V> num mapa do tipo Map<K, List<V>>
 *     onde o mapa eh definido numa funcao chamada groupByTo
 *     K vem atraves da funcao lambda keySelector
 *     List<V> eh definido dentro da funcao groupByTo e pode ocorrer de 2 formas
 *      - numa das implementacoes de groupByTo cada elemento V do iterator nao sofre nenhuma transformaca
 *          - Dessa forma obtemos um mapa cujos valores sao os mesmos da colecao original
 *       - existe uma implemnentacao de groupByTo que aceita uma funcao lambda como parametro chamada valueTransform
 *          - essa funcao recebe cada elemento do iterator<V> e pode transformar num objeto R qualquer
 *          - Fazendo com que a funcao groupBy nos retorne um mapa <K, List<R>>
 *
 * https://medium.com/default-to-open/kotlin-tips-singleton-utility-functions-group-object-initialization-and-more-27cdd6f63a41
 * */

/**
 * Agrupando strings num mapa cuja chave eh a primeira letra da string
 * */
fun groupByFirstLetter() {
    val map: Map<Char, List<String>> =
        listOf("james", "stweart", "philip", "arnold", "armando")
            .groupBy { name -> name.first().toUpperCase() }

    println(map)
}

fun groupByFirstLetterAndTransformElements() {
    fun keySelector(value: String): Char = value.first()

    fun transformMatchResult(match: MatchResult): CharSequence = match.value.toUpperCase()

    fun transformValueOfIterator(value: String): Pair<String, Int> =
        run {
            val matchFirstCharOfWord = Regex("\\b\\w")
            val word = value.replace(matchFirstCharOfWord, ::transformMatchResult)
            val sum = value.sumBy { c -> c.toInt() }
            word to sum
        }

    val map: Map<Char, List<Pair<String, Int>>> =
        listOf("james", "stweart", "philip", "arnold", "armando", "a").groupBy(
            ::keySelector,
            ::transformValueOfIterator
        )

    println(map)
}


fun <K, V, R> Map<K, V>.transformValue(fn: (V) -> R): Map<K, R> {
    return this.run {
        val mutableMap = mutableMapOf<K, R>()
        this.forEach { (k, v) -> mutableMap[k] = fn(v) }
        mutableMap.toMap()
    }
}

fun <K, V, R> Map<K, V>.transformValue(fn: (Map<K, V>, V) -> R): Map<K, R> {
    return this.run {
        val mutableMap = mutableMapOf<K, R>()
        this.forEach { (k, v) -> mutableMap[k] = fn(this, v) }
        mutableMap.toMap()
    }
}

fun String.table(): Map<Char, Int> = this.groupBy { it }.transformValue { chars -> chars.size }

fun groupingNumbersUsingModularFunction() {
    (1 .. 100).toTypedArray()
        .groupBy { it % 25 }
        .log()
}

//

fun main() {
    groupingNumbersUsingModularFunction()
}