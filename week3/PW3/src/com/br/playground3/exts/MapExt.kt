package com.br.playground3.exts

fun <K, V> Map<K, V>.log() {
    println(this)
}

fun <K, V, R> Map<K, V>.log(show: Map<K, V>.() -> R) = show()