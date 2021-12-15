package com.br.week5.funwithreceiver.takeunless

/*
    retorn o proprio objeto que chama essa funcao caso a funcao de teste/predicado
    passada como argumento retornar false
 */


private fun checkTakeUnless() {
    val p = 45
    // p > 45 entao takeUnless deve retornar nulo
    val q = p.takeUnless { it > 10 }
    // print null
    println(q)
}

fun main() {
    checkTakeUnless()
}