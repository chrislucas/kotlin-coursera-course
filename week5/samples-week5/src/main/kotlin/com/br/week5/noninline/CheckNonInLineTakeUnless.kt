package com.br.week5.noninline

private fun <T> T.nonInlineTakeUnless(fn: (T) -> Boolean): T? =
    if (!fn(this)) {
        this
    } else {
        null
    }


private inline fun <T> checkNonInlineTakeUnless(q: T, noinline predicate: (T) -> Boolean) {
    val p = q.nonInlineTakeUnless(predicate)
    println(p)
}


fun main() {
    checkNonInlineTakeUnless(10) { it > 10 }
}