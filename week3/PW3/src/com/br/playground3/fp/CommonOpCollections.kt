package com.br.playground3.fp

import com.br.playground3.exts.log

val INT_INTERVAL = (0..100).toList()

fun fnFilter() {
    println(INT_INTERVAL.filter { it and 1 == 0 })
}

fun fnMap() {
    println(INT_INTERVAL.filter { it and 1 == 0 }.map { it + 1 })
}

fun fnReduce() {
    println(INT_INTERVAL.filter { it < 11 }.reduce { acc, i -> acc + i } == 55)
}

fun fnCount(pred: (Int) -> Boolean) {
    INT_INTERVAL.count { pred(it) }
}



/**
 * Agrupa uma colecao num mapa. A chave do mapa eh obtida atraves
 * de uma funcao passada por argumento que ira recuperar um valor que
 * sera usado como 'chave' de cada elemento da colecao
 *
 * */
fun <T, K> fnGroupBy(collection: Collection<T>, getKey: (T) -> K) {
    collection.groupBy(getKey).log()
}

/**
 * para o caso de eu ter uma colecao de objetos que possui algum atributo cujo
 * valor eh unico
 * */
fun <T, K> fnAssociateBy(collection: Collection<T>, getKey: (T) -> K) {
    collection.associateBy(getKey).log()
}

/**
 * find retorna nulo se nao encontrar 1 elemento que satisfaca a funcao predicado
 *
 * existem outras funcoes similares: first e firstOrNull
 *
 * */
fun <T> fnFind(collection: Collection<T>, pred: (T) -> Boolean) {
    val values = collection.find(pred)
    println(values)
}

fun <T> fnFirst(collection: Collection<T>, pred: (T) -> Boolean) {
    val values = collection.first(pred)
    println(values)
}

fun <T> fnFirstOrNull(collection: Collection<T>, pred: (T) -> Boolean) {
    val values = collection.firstOrNull(pred)
    println(values)
}

/**
 * any(all, none)
 * */
fun <T> fnAny(collection: Collection<T>, pred: (T) -> Boolean) {
    val value = collection.any { pred(it) }
    println(value)
}

fun <T> fnAll(collection: Collection<T>, pred: (T) -> Boolean) {
    val value = collection.all { pred(it) }
}

fun <T> fnNone(collection: Collection<T>, pred: (T) -> Boolean) {
    val value = collection.none { pred(it) }
}

fun <T> fnPartition(collection: Collection<T>, pred: (T) -> Boolean) {
    val pair = collection.partition(pred)
    println(pair)
}

fun <T, K, V> fnAssociate(collection: Collection<T>, transform: (T) -> Pair<K, V>) {
    collection.associate(transform).log()
}

/**
 *
 * Junta 2 listas numa colecao de pares Pair<c1(i), c2(i)>
 *     Se as lictas tiverem tamanhos diferentes, a lista resultante tera o tamanho da menor lista
 * */
fun <P, Q> fnZip(c1: Collection<P>, c2: Collection<Q>) {
    println(c1.zip(c2))
}

/**
 * a ext function sem parametros cria uma lista de Pares com o i-esimo elemento e o seguinte
 * ja a ext function que aceita uma funcao como argumento permite criar uma lista de qualquer
 * objeto. Esse objeto em questão será definido pelo resultado que a funcao transform retornar
 * */
fun <T, R> fnZipWithNext(collection: Collection<T>, transform: ((a: T, b: T) -> R)?) {
    val rs = transform?.run { collection.zipWithNext(this) } ?: collection.zipWithNext()
    println(rs)
}

/**
 *
 * [[a,b], [b], [c] ...].flatten() -> [a, b, b, c]
 *
 * */
fun <T> fnFlatten(collection: Collection<Collection<T>>) {
    println(collection.flatten())
}


/**
 * flatMap combina duas operacoes: map e flat
 *
 * a funcao transform aplica uma operacao no item da colecao e deve retornar um Iterable (map)
 * e a funcao flatMap aplica a linearizacao em cada Iterable pegando item a item e adicionando
 * numa lista unica, linear
 * */
fun <T, R> fnFlatMap(collection: Collection<T>, transform: (T) -> Iterable<R>) {
    println(collection.flatMap(transform))
}


fun <T, R> fnFlatMapTo(collection: Collection<T>, transform: (T) -> Iterable<R>) {
    val result = collection.flatMapTo(mutableListOf(), transform)
    println(result)
}

fun main() {
    fnGroupBy(INT_INTERVAL) { if (it % 2 == 0) "Even" else "Odd" }

    val persons = listOf(Person("a", 1), Person("b", 1), Person("c", 2))
    fnGroupBy(persons) { it.age }
    /*ˆ
    * se o valor usado como chave nao for unico as duplicidades nao serao armazenadas onde o ultimo
    * elemento sera escolhido
    * */
    fnAssociateBy(persons) { it.age }
    // como na colecao o atributo nome de cada objeto eh nulo, todos os objetos
    // sao armazenados no mapa
    fnAssociateBy(persons) { it.name }

    /**
     * Construindo um mapa atraves de uma lista
     * */
    fnAssociate(INT_INTERVAL.filter { it < 21 }) { Pair(it % 4 == 0, it) }

    fnZipWithNext<Int, Pair<Int, Int>>(INT_INTERVAL, null)

    fnZipWithNext(INT_INTERVAL) { p: Int, q: Int -> p xor q }

    fnFlatMap(listOf("christoffer", "lucas")) { str ->
        str.split("")
    }

    fnFlatMapTo(listOf("chris", "lks")) {
        str -> listOf(str.split(""))
    }
}