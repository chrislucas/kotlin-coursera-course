package com.br.playground3.lambda

import com.br.playground3.log


// https://www.coursera.org/learn/kotlin-for-java-developers/lecture/EELeL/return-from-lambda

fun duplicateNonZero(list: List<Int>): List<Int> {
    return list.flatMap {
        /**
         * ao usar a keyword return em kotlin, o comportamento
         * sempre sera de fazer com que a funcao seja interrompida e retornada
         * para a parte que chamou a funcao. No caso abaixo o uso do return
         * vai interromper a execucao de flatmap e fara com que a funcao
         * puzzeDuplicateNonZero retorne aquele que a chamou
         * */
        if (it == 0) return listOf()
        listOf(it, it)
    }
}
// usando label return syntax para indicar que
// que queremos que o return seja aplicado no contexto da funcao flatMap
// e nao da funcao mais externa

fun duplicateNonZero2(list: List<Int>): List<Int> {
    return list.flatMap {
        if (it == 0) return@flatMap listOf()
        listOf(it, it)
    }
}
// prefiro a sintaxe abaixo
fun duplicateNonZero1(list: List<Int>): List<Int> {
    return list.flatMap {
        if(it == 0)
            listOf()
        else
            listOf(it, it)
    }
}

fun duplicateNonZero3(list: List<Int>): List<Int>{
    // eh possivel definir a propria label
    return list.flatMap me@{
        if (it == 0) return@me listOf()
        listOf(it, it)
    }
}

/**
 * Por que a keyword return em kotlin faz com que a funcao mais externa
 * retorne aquele que a chamou ?
 * */
fun explainReturnKeyworkBehavior() {
    val list = (1 .. 100).toList()
    // espera-se que um loop for sobre a lista
    for (i in list) {
        if (i == 0)
            return
    }
    // um loop utilizando o for-each tenham o mesmo comportamento
    list.forEach {
        if (it == 0)
            return
    }
    // ambas devem interromper a funcao e retornar aquele que a chamou
}


fun main() {
    duplicateNonZero(listOf(2,3,0,4)).log()
    duplicateNonZero1(listOf(2,3,0,4)).log()
    duplicateNonZero2(listOf(2,3,0,4)).log()
}