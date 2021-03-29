package com.br.playground3.functions.group.filters

private fun <V> filteringAfterGroupBy(collection: List<V>, function: (Map.Entry<V, List<V>>) -> Boolean) =
    collection.groupBy { it }.filter(function)


private fun <V> filteringByKeyAfterGroupBy(collection: List<V>, function: (V) -> Boolean) =
    collection.groupBy { it }.filterKeys(function)


private fun <V> filteringByValueAfterGroupBy(collection: List<V>, function: (List<V>) -> Boolean) =
    collection.groupBy { it }.filterValues(function)

fun main() {

}