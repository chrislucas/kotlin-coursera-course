package com.br.week4.oop

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

class SafeUnSafeCasting(var numberValue1: Int?, initInt: (() -> Int?)? = null) {
    val numberValue2: Int? = numberValue1 // so faz sentido

    var numberValue3: Int? = 10

    var numberValue4: Int? = 10
        get() = field?.times(2)

    val numberValue5: Int? by initInt
}

private operator fun (() -> Int?)?.getValue(casting: SafeUnSafeCasting, property: KProperty<*>): Int? =
    this?.let { it() }


private fun test1() {
    val instance = SafeUnSafeCasting(10)

    if (instance.numberValue1 is Int) {
        println("Variavel numberValue1 ${instance.numberValue1}")
    }

    if (instance.numberValue2 is Int) {
        println("Variavel numberValue1 ${instance.numberValue1}")
    }
}

fun test2() {
    val instance = SafeUnSafeCasting(10) { 15 }
    instance.numberValue5?.let {
        println("Variavel numberValue5 = $it")
    }
}

fun main() {
    test2()
}
