package com.br.playground3


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

//

fun main() {
    println("christoffer lucas".table())
}