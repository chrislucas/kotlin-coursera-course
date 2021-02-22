package sample.kotlin

import java.lang.StringBuilder

/**
 * https://kotlinlang.org/docs/reference/multi-declarations.html#destructuring-in-lambdas-since-11
 * */

fun sampleDestructuringLambdas(word: String) {
    val map = mutableMapOf<Char, Int>()
    word.associateWith {
        c ->
        map[c] = map[c]?.plus(1) ?: 1
        map[c]
    }

    /**
     * Destructiring in lambda
     *
     * O Argumento da funcao mapValues eh um Map.Entry<K, V>, assim podemos
     * usar o recurso de desctructuring (k, v) para recuperar os valores
     * do objeto Map.Entry
     * */
    map.mapValues {
        // Destructuring um Par,
        // pode definir o tipo do par (k, v): Map.Entry<Char, Int> ou omitir
        (k, v) -> println("$k, $v")
    }
    val valuesOfMap = StringBuilder()
    map.mapValues {
        // podemos usar underline quando uma variavel nao for usada
        (k: Char, _) ->
        valuesOfMap.append(k)
    }

    println(valuesOfMap.toString())
}

/**
 * LINQ-Style
 *
 * aproveitando que se o ultimo argumento de uma funcao for uma outra funcao
 * podemos escrever essa ultima funcao usando chaves {}
 * (https://kotlinlang.org/docs/reference/lambdas.html#passing-a-lambda-to-the-last-parameter)
 * fun foo(a: Int, fn: () -> Unit)
 * fn(1) { "um algoritmo qualquer aqui" }
 *
 * Atraves desse recurso podemos encadear diversas chamadas, escrevendo com LINQ-style
 * strings.filter { it.length == 5 }.sortedBy { it }.map { it.toUpperCase() }
 * */

fun sampleLINQStyleWithLambdaExpression() {}


fun main() {
    sampleDestructuringLambdas("chrisluccas")
}