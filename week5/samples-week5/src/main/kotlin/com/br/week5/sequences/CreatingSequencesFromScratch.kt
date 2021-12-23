package com.br.week5.sequences

import java.lang.StringBuilder
import kotlin.random.Random

/**
 *
 * https://www.coursera.org/learn/kotlin-for-java-developers/lecture/qHm1D/creating-sequences
 * @see Sequence
 *
 * interface que representa uma estrutura sequencia de dados. Essa interface define um unico
 * atributo que eh uma sobrecarga do operador iterator, mas a interface Iterator que o operador
 * implementa nao eh a mesma interface que conhecemos da biblioteca Collections no Java. Isso
 * foi definido para nao haver confusao entre as estruturas de colecao e sequencia, cuja primaira
 * ao implementar operacoes em seus elementos tem um comportamento Eager aplicando a todos os elementos
 * diferente da estrutura Sequence que armazena as operacoes e so as aplica quando o resultado da operacao
 * eh solicitado e ainda para de executar a operacao quando uma FUNCAO TERMINAL (toList(), find() etc) eh achamada.

 */


val sequence = (1..1000).asSequence()


/*
    intermediate operations / extension functon

    Operacoes intermediarias de Sequence nao sao inline porque elas precisam ser armazenadas para
    serem aplicadas quando funcoes terminais solicitares o resultado.

    O fato dessas operacoes nao serem inline eh fundamental para obter-se o comportamento Lazy, pois as funcoes
    lambdas passadas para as funcoes que representam uma operacao precisam ser armazenadas em algum lugar.
 */

private fun checkFilterOp() {
    val result = sequence.filter { it and 1 == 1 }.joinTo(StringBuilder(), ", ")
    println(result)
}

private fun checkMapOp() {
    val result = sequence.map { Pair(it, if (it % 2 == 0) "Par" else "Impar") }.joinTo(StringBuilder(), ", ")
    println(result)
}

/*
    Terminal operation / extension function
    Ja as funcoes terminais que retornam um tipo primitivo, uma colecao ou um tipo defiinido pelo programador
    podem e são declaradas como inline
 */

private fun checkAnyOp() {
    println(sequence.any { it % 2 == 1 })
}


private fun checkFindOp() {
    println(sequence.find { it % 2 == 1 })
}

/*
    Generating a sequence
 */

private fun checkGenerateSequenceFun() {
    val s = generateSequence {
        Random.nextInt(20).takeIf { it > 0 }
    }
    println(s.toList().joinTo(StringBuilder(), ", "))
}


private fun checkGenerateInfiniteSequence() {
    val s = generateSequence(0) { it + 1 }
        .take(50)
        .toList()
    println(s)
}


private fun howManyTimesIsExecuted() {
    val f = generateSequence(3) {
        n ->
        println("Generating element...")
        (n + 1).takeIf { it < 7 }
    }
    println(f.first())
    println(f.toList())
}

fun main() {
    //checkFibonacciSeq()
    howManyTimesIsExecuted()
}