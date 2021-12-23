package com.br.week5.lambdawithreceiver


import kotlin.text.StringBuilder

/*
    https://www.coursera.org/learn/kotlin-for-java-developers/lecture/FNQGO/lambda-with-receiver

    Em resumo podemos dizer que:
    Lambdas with receiver = Extension Function + Lambdas

    Está para
    regular function -> regular lambdas
    extension function -> lambda with receiver ou extension lambdas
 */


private fun withFun() {
    fun alphabet() {
        val sb = StringBuilder()
        sb.appendLine("Alphabet: ")
        ('a'..'z').joinTo(sb, ",")
        println(sb.toString())
    }

    fun alphabet1() {
        /*
            Lambda with Receiver é uma funcao lambda
            com a referencia a um objeto (this) implicitamente
            Abaixo temos
            1) funcao regular 'with'
            2) funcao lambda definida pelas chaves {}
            3) essa funcao lamnda recebe como argumento uma reference ao argumento
            passado para funcao regular em 1
         */


        val message = with(StringBuilder()) {
            // lambda com this implicito
            appendLine("Alphabet: ")
            ('a'..'z').joinTo(this, ",")
            this.toString()
        }

        println(message)
    }

    alphabet1()
}

private fun extensionFunAndLambdaWithReceiver() {
    /*
       ext fun e lambda with receiver tem uma estrutura muito similar
       veja
        Ext Fun
        String.lastChar() = this.get(this.len - 1)

        Lambda With Receiver
        buildString { this.append("...") }

        Ambas possuem acesso a referencia de um argumento (this)
     */

    val s = buildString {
        appendLine("Alphabet: ")
        ('a' .. 'z').joinTo(this, ", ")
        this.toString()
    }

    println(s)
}


fun main() {
    extensionFunAndLambdaWithReceiver()
}