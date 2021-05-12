package com.br.week4.properties.extension

/**
 * Toda vez que a extension for chamada o seu getter sera chamado. O compilador nao
 * faz uma otimizacao para que o valor retornardo/calculado seha armazenado
 * */
val String.median: Char?
    /**
     * Um metodo estatico chamado getMedian sera criado em java
     * */
    get()  {
        println("calculating")
        return getOrNull((length - 1) / 2)
    }


fun main() {
    val s = "abc"
    println(s.median)
    println(s.median)
}