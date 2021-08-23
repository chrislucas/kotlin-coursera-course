package com.br.week4.oop.objects

/**
 * Kotlin nao possui metodos estaticos com a mesma sintaxe e keyword static
 * como em Java, mas possui companion objects que podem substituir esse recurso
 *
 * Motivacoes para existir companion objects e nao static methods
 *
 * 1) companion objects podem implementar interfaces
 * 2) Em java, static methods nao podem sobreescreer um membro de uma interface. Em kotlin, com companio objects
 * podendo implementar interface, sobreescrever um metodo de uma interface passa a ser possivel
 *
 * 3) podemos criar uma extension function para companion object. Seria como adicioanr um metodo estatico que nao
 * existe a uma classe
 *
 * Como declarar membros estaticas em kotlin
 *
 *  - variaveis em top-level (em arquivos fora de classes), dentro de objects ou dentro de companion objects
 *
 * */


class Data {
    companion object {
        fun invoke(fn: () -> Unit) = fn()
    }
}

class DataII {

    interface Runner {
        fun run(fn: () -> Unit)
    }

    companion object RunnerObject: Runner {
        override fun run(fn: () -> Unit) = fn()
    }
}

class DataIII private constructor() {
    interface Factory<T> {
        fun create() : T
    }

    companion object: Factory<DataIII> {
        /**
         * Ponto importante:
         *
         * Metodos privados so sao possiveis de serem chamados dentro da propria classe
         * ou dentro de um companion object.
         *
         * Uma funcao top-level (fora de qualquer classe, jogada num arquivo), nao pode acessar
         * funcoes-membro privadas de uma classe
         * */
        override fun create(): DataIII = DataIII()
    }

    fun invokeLambda(fn: () -> Unit) = fn()

}

private fun test1() {
    Data.invoke { println(0xff) }

    DataII.RunnerObject.run { println("Nao tenho um bom exemplo para testar esse recurso ${0xaa}") }
    // posso ignorar o nome do companio object
    DataII.run { println("Nao tenho um bom exemplo para testar esse recurso ${0xaa}") }

    DataIII.create().invokeLambda { println("outro exemplo tosco") }
}


/**
 * Imagine que essa classe esta num modulo relacionado a regra de negocios
 * */
data class User(val id: Int, val name: String) {
    companion object
}

// e essa extension function num modulo client
fun User.Companion.createUserFromJson(json: String): User = User(1, "mockuser")

private fun test2() {
    val user = User.createUserFromJson("")
    println(user)
}


fun main() {
    test2()
}