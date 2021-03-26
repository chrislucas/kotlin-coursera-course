package com.br.playground3


// https://www.techiedelight.com/sort-map-by-keys-kotlin/


fun <K, V> fn(map: Map<K, V>, comparator: Comparator<K>) {
    println(map.toSortedMap(comparator))
}

fun main() {
    val comparatorDescendant = Comparator<Int> { p, q -> q - p }

    fn(mapOf(1 to 10, 2 to 20, 8 to 80), comparatorDescendant)
}