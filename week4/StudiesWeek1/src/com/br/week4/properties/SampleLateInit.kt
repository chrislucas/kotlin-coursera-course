package com.br.week4.properties

import com.br.week4.properties.model.Point2D
import kotlin.reflect.full.memberProperties
import kotlin.reflect.jvm.javaField

/**
 * uma propriedade nao pode ser lateinit val. Isso esta relacionado
 * como isso eh implementado a nivel do bytecode. A propriedade na pode
 * ser mutavel em um determinado momento (lateinit)e imutavel ao mesmo tempo, pois impediria que
 * o codigo alterasse o seu valor
 *
 *
 * Uma variavel val em kotlin transforma-se numa variavel final em Java (a nivel de bytecode)
 * , por definicao sendo impossivel de ser modificada
 * por um codigo Java. Entao foi definido que variaveis que recebem o atributo lateinit devem ser
 * var, pq se fosse possivel definir lateinit val teriamos uma variavel que pode ser modificada por codigo kotlin mas nao
 * por codigo Java.
 *
 * A segunda restricao: para se definir uma variavel lateinit sua tipagem nao pode ser nula, por motivos
 * obvios. Nao faz sentido criar uma variavel que sera inicializada num momento definido pelo programador mas que
 * pode ter o valor nulo.
 *
 * A terceira restricao: O tipo da variavel lateinit nao pode ser primitivo. Isso esta relacionado
 * com a forma que esse recurso eh implementado. Somente tipos que derivam de classes;referencias
 * podem ser inicializados como null, tipos primitivos sao inicializados pelo compilador com valores
 * CONSTANTES {0, 0.0, false} relacionados aos seus respectivos tipos ()
 * Em resumo, somente uma propriedade Non-Nullable Reference Type pode ser lateinit
 *
 *
 * */

class SampleLateInit {
    // Mon Nullable Reference of Point2D
    lateinit var  p: Point2D

    fun init() {
        p = Point2D(2.0, 1.0)
    }

    /**
     * Checando via reflection todos as propriedades de uma classe
     * https://stackoverflow.com/questions/58547245/how-to-find-is-a-lateinit-var-has-been-initialized-via-reflection
     * */

    fun check() : Boolean {
        return this::class.memberProperties.filter {
            member -> member.isLateinit
        }.none {
            it.javaField?.get(this) == null
        }
    }

    fun checkInitialization() =
        this::p.isInitialized
}


fun main() {
    val sample = SampleLateInit()
    println(sample.checkInitialization())
    println(sample.check())
    sample.init()
    println(sample.checkInitialization())
    println(sample.check())
}