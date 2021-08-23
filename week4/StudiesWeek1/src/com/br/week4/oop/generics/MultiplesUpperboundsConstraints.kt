package com.br.week4.oop.generics

import java.lang.StringBuilder

fun <T> ensureTrailingPeriod(seq: T) where T : CharSequence, T : Appendable {
    if (!seq.endsWith("."))
        seq.append(".")
}

fun main() {
    val data =StringBuilder("Hello")
    ensureTrailingPeriod(data)
    println(data)
}