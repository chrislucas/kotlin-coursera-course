package com.br.week5.ktypes.hierarchy

/*
    https://www.coursera.org/learn/kotlin-for-java-developers/lecture/zxwAb/kotlin-type-hierarchy

    Kotlin possui uma estrutura hierarquica com uma super classe que representa todos as subclasses
    chamada de Any e uma uma sub classe que um subtipo de todas as classes, tanto do kotlin quanto
    as criadas em projetos, essa classe eh chamada de Nothing

    Any <- Int, User
    Int, Double, User ... <- Nothing


    Unit ao invés de void
    - Usamos o tipo Unit quando um metodo retorna um valor 'sem valor ou signifiicado', assim como fazemos
    no java com void
    - A nivel do bytecode Unit é trocado pelo tipo void


    Nothing é diferente de Unit/Void
    - Signigica que a funcao nao retorna algo
    - Esse recurso é último para criar funções que lamçam exceptions

    Definicoes de Unit e Nothing vindas da programacao funcional

    - Unit : "a type that allows only one value and thus can hold no information"
    -Nothing: "a type that has no values"

    Expressoes que retornam o tipo Nothing
    - throw Exception() - qualquer familia de exception
    - return
        - expressao boleana ?: return
    - TODO ("") - function
        - public inline fun TODO(): Nothing
 */


/*
    O compilador ira determinar que essa funcao nao pode terminar normalmente
    analisando o tipo de retorno dela/type inference e vai aloca-la na área
    de dead code
 */
private fun fail(message: String): Nothing = throw IllegalStateException(message)

private fun failIllegalArgument(message: String): Nothing = throw IllegalArgumentException(message)

private data class Person(val name: String?)

private fun check(person: Person) {
    val isValid = person.name ?: failIllegalArgument("Argumento ")
    println(isValid)
    TODO()
}

fun main() {
    check(Person(null))
}