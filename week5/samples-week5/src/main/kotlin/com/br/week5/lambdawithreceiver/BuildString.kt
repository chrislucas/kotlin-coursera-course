package com.br.week5.lambdawithreceiver

private fun checkBuildString() {
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
    checkBuildString()
}