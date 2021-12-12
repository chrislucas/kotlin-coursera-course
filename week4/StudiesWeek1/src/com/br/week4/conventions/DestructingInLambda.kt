package com.br.week4.conventions

// definindo o tipo de argumento que essa funcao lambda recebe
// por inferencia o compilador vai saber qual o tipo do argumento no corpo da funcao lambda
val fa: (Int) -> Unit = { a -> println(a) }

// aqui o tipo teve que ser definido explicitamente
val fOneParam = { a: Int -> println(a) }

val fb: (Int, Int) -> Int = { a, b -> a + b }

val fc: (Pair<Int, Int>) -> Unit = { (a, b) -> println("$a, $b") }

val fd: (Pair<Int, Int>, Int) -> Unit = { (a, b), c -> println("$a, $b, $c") }

val fe: (Pair<Int, Int>, Int) -> Unit = { (a: Int, b: Int), c -> println("$a, $b, $c") }

data class MyPair<K, V>(val key: K, val value: V)

val fg: (MyPair<Int, Int>) -> Unit= { (a, b) -> println("$a, $b") }

// TESTE maluco
//val fh: (MyPair<Int, Int>, MyPair<Int, Int>.() -> Unit) -> Unit=  { p, function ->  p.function()}


private fun checkIndexedValueClass(list: List<Int>) {
    for ((idx, value) in list.withIndex()) {
        println("$idx, $value")
    }

    /**
     * @see IndexedValue data class
     * compilador gera automaticamente os metodos component1 .. componentn
     * para acessar os atributos de uma data class
     */
    for (indexedValue in list.withIndex()) {
        val i = indexedValue.component1()
        val v = indexedValue.component2()
        println("$i, $v")

    }
}

fun main() {
}