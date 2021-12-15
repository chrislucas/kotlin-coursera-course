package com.br.week5.crossinline


private fun <T> T.nonInlineTakeUnless(fn: (T) -> Boolean): T? =
    if (!fn(this)) {
        this
    } else {
        null
    }


private inline fun <T> checkNonInlineTakeUnless(q: T, crossinline predicate: (T) -> Boolean) {
    val p = q.nonInlineTakeUnless { predicate(it) }
    println(p)
}


fun main() {
    checkNonInlineTakeUnless(123) { it and 1 == 0 }
}