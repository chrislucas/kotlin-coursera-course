package com.br.playground3.functions.group

/**
 * Esse eh um arquivo com extensoes para explorar as ext function groupBy e groupingBy.
 *
 * Nada do que tem aqui necessariamente eh útil, tudo é fruto de experimentos com objetivo
 * de explorar como essas funcoes funcionam e o que eh possivel fazer. O limite está na minha
 * imaginacao.
 *
 *
 * quando aplicamos um groupBy num Iterator<T> obtemos um mapa
 * Map<K, List<V>> onde K eh obtido atraves da funcao lambda keySelector
 * */

fun <V> Iterable<V>.histogram(fn: (List<V>) -> Int): Map<V, Int> =
    this.groupBy(keySelector = { it }).transformValue(fn)

fun <T, K> Iterable<T>.classify(keySelector: (T) -> K) =
    this.groupingBy(keySelector)

fun <V, K, Acumulator> Iterable<V>.frequecy(
    classifying: (V) -> K,
    builder: (key: K, acc: Acumulator?, e: V, first: Boolean) -> Acumulator
): Map<K, Acumulator> = groupingBy(classifying).aggregate(builder)


fun <T, K> Array<T>.grouping(keySelector: (T) -> K) =
    this.groupingBy(keySelector)


//

/**
 * IterableElement = CollectionType
 * Key = a chave para agrupar eh os elementos de uma colecao eh
 * dada pela funcao-lambda keySelector passada como argumento para
 * extension function groupingBy que por sua vez retorna um objeto
 * interface Grouping<T, K>.
 *
 * Group: Parametrizando Grouping<IterableElement, Key> ou do original Grouping<T, K>
 *
 * a extension abaixo tem o objetivo de construir um mapa cuja a chave sera
 * o conjunto de elementos retornados pela funcao grouping. Nao ha uma funcao
 * que a partir de um Grouping<T, K> conseguimos contruir um mapa, entao
 * estou explorando essa possibilidade
 * */

fun <IterableElement, Key, Group : Grouping<IterableElement, Key>, Value>
        Group.mapping(fn: Group.() -> Map<Key, Value>) = fn()

fun <V> Array<Array<V>>.histogram(fn: (List<Array<V>>) -> Int) =
    this.groupBy { array -> array[0] }
        .transformValue(fn)

fun <V> Array<V>.histogram(fn: (List<V>) -> Int) = this.groupBy { it }.transformValue(fn)