package com.br.week5.ktypes.hierarchy

/*
    Pq temos o tipo Nothing em kotlin ?

    1) ele nos permite utilizar expressoes que retornam Nothing type
        - Como Nothing é um subtipo de todos os tipos o compilador faz a inferencia do resultado da expressao
        e aloca o tipo certo a variavel que recebe esse resultado

    2) Permite criar funcoes que encapsulam o lancamento de exceptions, uma vez que tais funcoes nao precisam
    retornar valor nenhum e podem terminar de forma anormal

    A nivel de bytecode Nothing é trocado por void pelo compilador

    Ainda sobre hierarquia entre classes.

    - os tipos nao nulos sao subtipos dos tipos nulos
        - Any? <- Any, Int? <- Int, User? <- User ... [Int?, User?] <- Nothing? e Nothing? <- Nothing
        - Isso pq podemos definir um tipo nulo usando um tipo na nulo mas o contrário não é verdadeiro
            - val user: User? = User()
            - val value: Int? = 1 // Int
            - so on
 */


private val nullConstant: Nothing? = null

private fun checkNullableNothing() {
    var value = null    // Nothing?
    // abaixo teremos um problema de inferencia
    // value e um Nothing?
    // value = 1 // The integer literal does not conform to the expected type Nothing?
    val values = mutableListOf(null)
    //values.add(1) // o mesmo problema de inferencia ocorre aqui
    val values1: MutableList<Int?> = mutableListOf(null)
    values1.add(1)
}

private fun checkInference(predicate: () -> Boolean) {
    /*
        Como o compilador infere o tipo de retorno dessa expressao if ?

        1) o compilador analisa o retorno do if - Int
        2) analisa o retorno a funcao 'failIllegalState' - Unit
        3) O compilador define que o retorno sera o supertipo de Int e Unit, no caso Any
     */
    val a1 = if (predicate()) {
        42
    } else {
        failIllegalState("Fail")
    }

    println(a1 is Int)
}


private fun checkTheCorrectInference(predicate: () -> Boolean) {
    /*
        E agora, como o compilador inferirá o tipo de retorno da expressao if ?
        1) o tipo da primeira expressao eh int e o da segunda nothing
        2) Nothing e um subtipo de Int assim como subtipo de todos os tipos. Isso permite
        que o compilador infira que o retorno da expressao é do tipo Int
     */
    val a1 = if (predicate()) {
        42
    } else {
        anotherFailIllegalState("Fail")
    }

    println(a1 is Int) // Check for instance is always 'true'
}

/*
    Usando a sintaxe abaixo a funcao deveria retornar Unit.
    Será que o compilador consegue inferir que essa funcao nao termina
    de forma normal pois lançará apenas uma exception
 */
private fun failIllegalState(message: String) {
    throw IllegalStateException(message)
}

private fun anotherFailIllegalState(message: String): Nothing = throw IllegalStateException(message)


fun main() {
    //checkInference { true }
    checkTheCorrectInference { false }
}