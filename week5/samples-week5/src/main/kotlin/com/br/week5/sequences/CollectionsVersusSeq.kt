package com.br.week5.sequences

import com.br.week5.inlinefun.exercise.filter.filter

/*
    https://www.coursera.org/learn/kotlin-for-java-developers/lecture/wD8Vc/collections-vs-sequences

    Operacoes em colecoes

    - usando as funcoes lambdas ja existentes na linguagem não temos problemas com performance
    porque elas sao definidas como inline, assim objetos anonimous nao sao criados e o corpo
    da funcao lambda e movido para o local que ela eh chamada, tudo isso diminui o overhead
    de chamar uma funcao lambda

    - As funcoes que operacao em colecoes como, filter, map, group geram uma nova colecao
    com o resultado da operacao, e isso pode causar um problema de performance por criar
    objetos intermediarios quando chamamos diversas funcoes de forma encadeada, como no exemplos
    da funca checkMaxOrNull

    - A opcao para evitar a criacao de objetos intermediarios ao chamar tais funcoes de forma
    encadeada eh utilizano seuqnce

        - Sequences sao caparaveis a Streams do Java, ambas as estruturas computam operacoes
        de forma lazy, somente quando sao requisitadas

        - Sequences so sao chamadas dessa forma pq Streams ja é usado pelo Java, entao evitou-se
        conflitos de nomes ja que as linguagens podem operar juntas
 */


private fun checkMaxOrNull() {
    val max = listOf(1, 2, - 3)
        .map { it * it }
        .filter { it and 1 == 0 }
        .maxOrNull()

    println(max)
}


private fun checkMaxOrNullOnSequence() {
    // afim de fazer um exemplo diferente, usei um IntRange
    val max = (1 .. 1000).asSequence()
        .map { it * it }
        .filter { it and 1 == 0 }
        .maxOrNull()

    println(max)
}

/*
    Operacoes em cadeia sob sequences nao geram objetos
    intermediarios, o mecanismo dessa estrutura permite
    que as operacoes sejam lançadas para as colecoes posteriores
    até que o resultado dessa operacao seja solicitado pelo programa.

    Esse eh um mecanismo que adia (lazy) as operacoes que precisam ser
    feitas até que seja solicitado o resultado.
 */
private fun checkMaxOrNullOnSequenceSeparatedCalls() {
    val seq = (1..1000).asSequence()
    println("Before sequence op: $seq")
    val seqMap : Sequence<Int> = seq.map { it  * it }   // Sequence
    println("Sequence Mapped: $seqMap")
    val seqFilter = seqMap.filter { it and 1 == 0 }     // Sequence
    println("Sequence Mapped Filtered: $seqFilter")
    println("After sequence op: $seq")


    println(seqFilter.maxOrNull())  // int
}

/*
    https://www.coursera.org/learn/kotlin-for-java-developers/lecture/HwXl7/more-about-sequences
 */

fun main() {
    checkMaxOrNullOnSequenceSeparatedCalls()
}