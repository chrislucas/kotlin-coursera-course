package com.br.week5.inlinefun

/*
    https://www.coursera.org/learn/kotlin-for-java-developers/lecture/fvkpL/inline-or-not-inline

    Quando utilizar inline

    - Segundo o instrutor do curso, em geral, a VM eh muito boa em realizar inlining em tempo de execucao
    então não são muitos os casos que precisamos fazer isso por nós mesmo

    - O exemplo dado para o uso de inline é
        - Uma high order function que recebe uma lambda
        - lamndas criando uma instancia de classe anonima mais objetos para cada atributo que ela tem acesso
            - isso causa um consumo de memória maior e aplicar o inline elimina esse consumo excessivo

 */

private fun checkNonInlineFunctionBytecode(data: Array<Int>, fn: (Array<Int>) -> Unit) = fn(data)

private inline fun checkInlineFunctionBytecode(data: Array<Int>, fn: (Array<Int>) -> Unit) = fn(data)

/*
    O que ocorre com o bytecode quando uma funcao lambda acessa uma variavel
 */

private fun anotherCheckNonInlineFun(fn: (Array<Int>) -> Unit) {
    val data = (1 .. 100).toList().toTypedArray()
    fn(data)
}


private fun anotherCheckInlineFun(fn: (Array<Int>) -> Unit) {
    val data = (1 .. 100).toList().toTypedArray()
    fn(data)
}

/*
    TODO fazer uma conparacao do bytecode dessas funcoes
 */

fun main() {
    val data = (1 .. 100).toList().toTypedArray()

    checkNonInlineFunctionBytecode(data) {
        println(it.filter { value -> value and 1  == 1 })
    }

    checkInlineFunctionBytecode(data) {
        println(it.filter { value -> value and 1  == 0 })
    }
}

