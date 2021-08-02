package com.br.samples.inheraitance


/**
 * Assim como em Java em Kotlin nao eh possivel sobreescrever um FIELD (atributo), o que
 * eh ocorre em kotlin atraves do compilador e uma sobreescrita do metodo getter
 *
 * Abaixo temos um comportamento interessante relacionado ao metodo init e os campos
 * value1 e value2 da class OpenBase
 *
 * O exemplo abaixo consiste em
 *
 *  a) criar uma class open A com 2 atributos
 *  b) crlar uma sub class B de A e sobrescrever em B os atributos de A
 *  c) definir um bloco init na super classe e printar os 2 atributos
 *  d) num dos atributos sobtreescritos na classe B vou adicionar um getter
 *  e) ver o resultado da execucao do bloco init
 *
 * 0) o metodo main desse arquivo cria uma instancia da subclasse
 *
 * 1) a subclasse chama o construtor da superclasse para que a primeira possa ser criada corretamente
 *
 * 2) As instrucoes no metodo init serao executaadas num construtor de uma classe Java apos a compilacao
 *
 * 3) Ao chamar value1 e value2, o construtor da superclasse ira chamar os metodos getters da subclasse, porem
 * um dos atributos sobreescritos ainda nao fora definido entao tera seu valor default, no caso do tipo INT = 0
 *
 * 4)
 * */


open class OpenBase(open val value: String) {
    open val value1 = 1
    open val value2 = 1

    init {
        println("$value1, $value2")
    }
}

class SubOpenBase(override val value: String) : OpenBase(value) {
    override val value1: Int = 2
    override val value2: Int
        get() = 2
}


fun main() {
    SubOpenBase("Test")
}