package com.br.week4.oop.objects

/**
 * Kotlin nao possui metodos estaticos com a mesma sintaxe e keyword static
 * como em Java, mas possui companion objects que podem substituir esse recurso
 *
 * Motivacoes para existir companion objects e nao static methods
 *
 * 1) companion objects podem implementar interfaces
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
        override fun create(): DataIII = DataIII()
    }

    fun invokeLambda(fn: () -> Unit) = fn()

}


fun main() {
    Data.invoke { println(0xff) }

    DataII.RunnerObject.run { println("Nao tenho um bom exemplo para testar esse recurso ${0xaa}") }
    // posso ignorar o nome do companio object
    DataII.run { println("Nao tenho um bom exemplo para testar esse recurso ${0xaa}") }

    DataIII.create().invokeLambda { println("outro exemplo tosco") }
}