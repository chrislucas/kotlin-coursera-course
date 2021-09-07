package com.br.week4.operators.conventions.iteratoris

private fun <T> testItList(list: List<T>) {

    // fun <T> Iterable<T>.withIndex(): Iterable<IndexedValue<T>
    // destructuring
    // for ( (i, v) in list.withIndex()) { println("[$i, $v]") }

    for (indexedValue:IndexedValue<T> in list.withIndex()) {
        val i = indexedValue.component1()
        val v = indexedValue.component2()
        println("[$i, $v]")
    }
}


fun main() {
    testItList("chrisluccas".split(""))
}