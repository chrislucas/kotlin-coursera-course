package com.br.week4.properties.lazy

/**
 * Uma propriedade que receba o atributo lazy tera seu valor computado somente na primeira
 * vez que um codigo tentar utiliza=la
 *
 * O atributo eh chamado de "lazy" no sentido que a variavel ou propriedade que tiver esse atributo nao
 * tera seu valor atributo a ela ate o momento que for necessári
 *
 * */

val s : String by lazy {
    println("computado")
    "teste"
}

fun main() {
    println("$s, $s, $s")
}