package com.br.week4.oop.designchoices

import com.br.week4.oop.designchoices.delegateInitInt
import kotlin.reflect.KProperty

/**
 * Por: Svetelana Isakova
 *
 * Anotacoes sobre o video
 *
 * Why final and public are used by default ?
 *
 * - tentar alcancar o equilibro entre as necessidades de
 * desenvolvedores de aplicacoes e de bibliotecas
 *
 * - tentar criar um ambiente que seja facil para desenvolvedores de aplicacoes, onde
 * os recursos da linguagem sejam mais permissivos (public, tudo eh acessivel por padrao),
 * porem com algunas restricoes (final, nada eh sobrescrito por padrao)
 * para que nao cause problemas para desenvolvedores de libraries/frameworks.
 *
 * - Para desenvolvedores de libraries existe o cuidado sobre a visibilidade da sua API.
 * Se algo for criado como publico fica dificil de diminuir essa visibilidade no futuro, podendo
 * causar problemas em aplicacoes que dependem de tal API
 *
 * - Aplicacoes que nao sao dependencias de nenhuma outra nao tem problemas imprevisiveis em alterar visibilidade
 * de metodps e classes
 *
 * - A existencia de recursos como a keyword final/open cria a possibilidade de recursos em linguagem como
 * smart-casts.
 *
 * https://stackoverflow.com/questions/63213696/how-is-smart-cast-different-from-explicit-cast-in-kotlin
 *
 * A keyword 'final' garante a seguranca do uso de smartcast porque a variavel criada como val (final)
 * nao muda de valor
 *
 * */

/**
 *
 * Exemplo baseado nesse link:
 * // https://stackoverflow.com/questions/63213696/how-is-smart-cast-different-from-explicit-cast-in-kotlin
 * e no video OOP design choices
 * do curso kotlin for java developers
 * */

class SafeUnSafeCasting(var numberValue1: Int?, private val initInt: (() -> Int?)? = null) {
    /**
     * 1) val T? com valor inicial so faz sentido
     * se o valor vier de outra variavel
     *
     * 2) val T? com default getter pode ser usado num smart cast
     * if(numberValue2 is Int) {
     *      println(numberValue2 * 2)
     * }
     *
     * */
    val numberValue2: Int? = numberValue1

    /**
     * variaveis que do tipo val que tem getters personalizados nao
     * sofrem smart cast
     * if (valueWithCustomGetter is Int) {
     *      // aqui valueWithCustomGetter nao passa a ser do tipo Int
     *      // ainda eh necessario usar o operador nullSafe ? pois
     *      // nao se sabe o que pode ocorrer dentro do getter personalizado
     * }
     * */
    val valueWithCustomGetter: Int? = 2
        /**
         * Esse getter poderia ter algum procedimento que
         * retorna-se null e seria aceito em tempo de execucao
         * pq o tipo de variavel assim permite
         * */
        get() = field?.times(2)


    var numberValue3: Int? = 10

    var numberValue4: Int? = 10
        get() = field?.times(2)

    val numberValue5: Int? by initInt

    fun test(value: Int?) {
        if (value is Int) {
            // depois de checar se a variavel eh nula ocorre o smart cast
            // e podemos usar a variavel como do tipo Int
            println(value * 2)
        }

        println(value?.times(2))
    }

    fun testLocalVariable(value: Int?) {
        var mValue: Int? = value
        if (mValue is Int) {
            mValue *= 2
            println(mValue * 2)
        }
    }

    fun testLocalVariable(value: Int?, transform: (Int?) -> Int?) {
        var mValue: Int? = value
        // mesmo apos o checar se mValue nao eh nulo
        // se ocorrer alguma mudanca dentro da instrucao condicional
        // que torne ou possa tornar a variavel mutavel em nula
        // o smart cast passa a nao valer mais
        if (mValue is Int) {
            // mValue sendo alterado por uma lambda que pode transforma-lo
            // em nullo
            //mValue = transform(mValue)

            // ou definir mValue como nulo diretamente
            //mValue = null

            println(mValue * 2)
        }
    }
}

/**
 * operator delegate getter
 * */
private operator fun (() -> Int?)?.getValue(c: SafeUnSafeCasting, property: KProperty<*>): Int? =
    this?.let { it() }

private fun testMemberClass1(instance: SafeUnSafeCasting) {

    instance.test(10)

    if (instance.numberValue1 is Int) {
        println("Variavel numberValue1 ${instance.numberValue1}")
    }

    if (instance.numberValue2 is Int) {
        println("Variavel numberValue2 ${instance.numberValue1}")
    }

    if (instance.valueWithCustomGetter is Int) {
        println("Variavel com getter personalizado ${instance.valueWithCustomGetter?.times(2)}")
    }
}

private fun testMemberClass2(instance: SafeUnSafeCasting) {
    instance.numberValue5?.let {
        println("Variavel numberValue5 = $it")
    }
}

fun main() {
    /*
    testMemberClass1(SafeUnSafeCasting(10))
    testMemberClass1(SafeUnSafeCasting(null))
    testMemberClass2(SafeUnSafeCasting(10) { 15 })
*/
}
