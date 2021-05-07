package com.br.playground3.exts

fun <T> Iterable<T>.log() { println(this) }

fun IntRange.toTypedArray() = toList().toTypedArray()