package com.br.scopefun.predicate

import kotlin.random.Random

// https://medium.com/mobile-app-development-publication/using-kotlin-takeif-or-takeunless-c9eeb7099c22

data class State(val isValid: Boolean)

private fun getWeirdState(i: Int): State? = if (i and 1 == 1) null else State(i % 14 == 0)

private fun sample1()  {
    do {
        val weirdValue = Random.nextInt(100000) % (1 shl 10)

        val state: State? = getWeirdState(weirdValue)
        /**
         * Usando takeIf em cadeia
         * */
        val mState = state
            ?.takeIf { it.isValid }
            ?.apply { println("$weirdValue, $this") }

    } while (mState == null)
}

class Log(val value: String) {
    fun show() = println("Log: $value")
}

private fun sample2(log: Log?) {
    /**
     * ao inves de usar um condicional
     *
     * */

   // if (log != null && log.value.isNotEmpty())
       // log.show()

    log?.takeIf { it.value.isNotEmpty() }?.show()
}

private fun sample3(log: Log) {
    log.takeIf { it.value.isNotEmpty() }?.apply { show() }
}

fun main() {
    sample2(Log(""))
    sample2(Log("chrisluccas"))
    sample3(Log(""))
}