package com.br.week4.oop.generics.reifiedtype

import kotlin.reflect.KClass

/*
    https://www.baeldung.com/kotlin/reified-functions

    Recapitulando o mecanismo de Type Erasure - Java

    - Kotlin e Java apagam em tempo de compilação a informação dos tipos genéricos
    - O parametro de tipo Genérico <T> é apagado. Ele aparece somente no codigo fonte

    - Assim, em tempo de execução todos as possiveis formas de tipos genericos sao representados
    no formado RAW/Bruto
        - List<Int>, List<String> ou qualquer outro tipo tornam-se uma simples List
        - Esse é o comportameno denominado Erasue


    "Nao podemos usar algo como T.class in Java porque T nao é "reified(reificado/materializado/objetificado/instanciado)"
    em tempo de execução.
    Técnicamente um tipo T é objectificadose estiver presente em tempo de execucao
    "

    INLINE functions
        - O código de uma inline function será injetado no corpo do método que faz a chamada a ela, exatamente
        a partir do ponto da chamada.

    https://kotlinlang.org/docs/inline-functions.html#reified-type-parameters

 */

// usando a keyword reified posso manipular o parametro generico como um tip
inline fun <reified T> membersOf() = T::class.members

// sem a keyword reified preciso dar um limitador superior para o parametro generico para
// o compilador tenha em tempo de compilacao o tipo que esse parametro pode se tornar
fun <T:Any> KClass<T>.membersOf() = this.members

fun <T:Any> Class<T>.membersOf() = this.kotlin.members

private fun checkIntMembers() {
    println(membersOf<Int>().joinToString("\n"))
}

private fun checkStringBufferMembers() {
    println(membersOf<StringBuffer>().joinToString("\n"))
}

private fun <T:Any> checkMemberOf(type: KClass<T>) {
    println(type)

    /**
     * @see kotlin.reflect.jvm.internal.KClassImpl
     */
    println(type::class) // class kotlin.reflect.jvm.internal.KClassImpl
    println((type as Any).javaClass) // class kotlin.reflect.jvm.internal.KClassImpl

    println(type.membersOf().joinToString("\n"))
    println("****************************************")
}

private fun <T:Any> checkMemberOf(type: Class<T>) {
    println(type.membersOf().joinToString("\n"))
    println("****************************************")
}

private inline fun <reified T> log() = println(T::class)

private fun <T:Any> KClass<T>.log() = println(this)

private fun <T:Any> Class<T>.log() = println(this)

private fun checkLog() {
    log<Int>()
    log<String>()
    log<StringBuilder>()
}

private fun <T:Any> checkLogKClass(type: KClass<T>) {
    println((type as Any).javaClass) // class kotlin.reflect.jvm.internal.KClassImpl
    println(type)
    type.log()
    println("****************************************")
}

private fun <T:Any> checkLogClass(type: Class<T>) {
    println(type)
    type.log()
    println("*****************************************")
}

fun main() {
    //checkIntMembers()
    //checkMemberOf(Int::class)
    //checkLog()
    //checkLogKClass(Int::class)
    //checkLogKClass(Double::class)
    //checkLogClass(Int::class.java)
    //checkLogClass(Double::class.java)
    checkMemberOf(Int::class.java)
}