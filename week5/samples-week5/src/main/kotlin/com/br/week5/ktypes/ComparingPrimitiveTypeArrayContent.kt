package com.br.week5.ktypes

/*
    Como vimos

    Array<Int>  = Integer []
    e
    IntArray = int []
 */

private fun checkComparingContent() {
    val a = intArrayOf(1, 2, 3)
    val b = intArrayOf(1, 2, 3)
    /*
        O codigo implementado pela funcao contentEquals eh esse em Java
        java.util.Arrays.equals(this, other)
        O jeito certo de comparar conteudo de tipos de referencia em Java/Kotlin
        o metodo equal por padrao vai comparar referencia de memoria
        e o resultado eh dizer q a eh diferente de b por serem instancias
        diferentes
     */
    println(a.contentEquals(b))
    // println(a == b)
}


fun main() {
    checkComparingContent()
}