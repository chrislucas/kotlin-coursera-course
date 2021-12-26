package com.br.week5.lambdawithreceiver.usefulfun

/*
    https://www.coursera.org/learn/kotlin-for-java-developers/lecture/pCrW2/more-useful-library-functions

    lambda Regular -
        fun ( fn:  (T) -> R ),
        fun ( fn:  () -> R ),
        fun ( fn:  () -> Unit ),

    lambda with Receiver -
        fun ( fn: T.() -> R) : R,
        fun ( fn: T.(A) -> R) : R,
        fun ( fn: T.(A, ..., Z) -> R) : R,
        fun ( fn: T.() -> Unit) : T,
        fun ( fn: T.() -> Unit) : Unit

    Note que na lambda regular ela eh uma funcao que recebe ou nao argumentos
    podendo receber como argumento o proprio objeto que a chama,
     ele eh chamado de receiver. Veja a funcao "also"como exemplo de funcao
     lambda regular que recebe seu receiver como argumento

    Ja a lambda with Receiver é uma extension function do objeto receiver

    Function Lambda with Receiver
    with (T, T.() -> R): R (this)   - a unica funcao que nao eh uma extensao
    T.run (T.() -> R): R    (this)
    T.apply ( T.() -> Unit): T (this)

    Regular Lambda
    T.also ( (T) -> Unit): T  (it)
    T.let ( (T) -> R): R (it)

    retornam o receiver: [also, apply]
    retornam o resultado function lambda: [with, run, let]

    this: [with, run, apply]
    it: [let, also]
 */
class Window private constructor(
    var width: Long,
    var height: Long,
    var isVisible: Boolean
) {
    class Builder {
        private var width: Long = 0L
        private var height: Long = 0L
        private var isVisible: Boolean = false

        fun withWidth(width: Long): Builder = this.also { it.width = width }
        fun withHeight(height: Long): Builder = this.also { it.height = height }
        fun isVisible(flag: Boolean): Builder = this.also { it.isVisible = flag }
        fun build(): Window = Window(width, height, isVisible)

        fun copy(window: Window) = window.run { Window(width, height, isVisible) }
    }

    override fun toString(): String =
        "[w: $width, h:$height, isVisible: $isVisible]"
}


private fun checkWithFun() {
    /*
        Retorna o resultado da funcao lambda, ou seja, o que a ultima
        linha da funcao lambda executar
     */
    val window = with(Window.Builder()) {
        withWidth(100)
        withHeight(100)
        isVisible(true)
        build()
    }
    println(window)
}

private fun checkRunFun(window: Window?) {
    /*
        Muito similar a funcao with porem ela eh uma ext fun e pode
        ser usada com nullables atraves do operador nullsafety "?"
        Retorna o resultado da funcao lambda, ou seja, o que a ultima
        linha da funcao lambda executar
     */
    val copyWindow = window?.run { Window.Builder().copy(this) }
    println(copyWindow)
}

private fun checkApplyFun() {
    // Apply retorna o objeto que o chamou (Receiver) como resultado
    val builder = Window.Builder().apply {
        withHeight(100)
        withWidth(100)
        isVisible(false)
    }

    println(builder.build())
}

private fun checkAlsoFun(window: Window?) {
    /*
        Similar a funcao apply, retornando o objeto/receiver que o chamou
        com a diferenca de ser uma funcao lambda regular nao uma funcao lambda
        with receiver
     */

    window?.apply {
        width = 50
        height = 50
    }?.also {
        println(it)
    }
}

private fun checkLetFun() {

}


fun main() {
    checkWithFun()
}