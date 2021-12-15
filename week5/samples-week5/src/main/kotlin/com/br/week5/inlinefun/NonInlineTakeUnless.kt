package com.br.week5.inlinefun

private fun <T> T.nonInlineTakeUnless(fn: (T) -> Boolean): T? =
    if (!fn(this)) {
        this
    } else {
        null
    }


/*
    Para analisar o bytecode disso

    Pesquisar sobre crossinline
 */

private inline fun <T> checkNonInlineTakeUnless(q: T, crossinline predicate: (T) -> Boolean) {
    val p = q.nonInlineTakeUnless { predicate(it) }
    println(p)
}

private inline fun <T> checkInlineTakeUnless(q: T, predicate: (T) -> Boolean) {
    val p = q.takeUnless(predicate)
}

fun main() {
    checkNonInlineTakeUnless(123) { it and 1 == 0 }
}