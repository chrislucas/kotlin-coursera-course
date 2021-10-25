package com.br.week4.oop.generics.reifiedtype

import java.lang.StringBuilder

/*
    https://kotlinlang.org/docs/inline-functions.html#inline-properties
    #
    https://kotlinlang.org/docs/inline-functions.html#restrictions-for-public-api-inline-functions
    Quando uma funcao inline é  public ou protected ela e considerada uma API de módulo público,
    podendo ser chamado em outros módulos e  seu  codigo sera inserido no local da chmada

    Isso pode causar certos riscos de imcompatibilidade na versão binária do código caso
    o módulo que declara a funcao inline for modificado e nao for recompilado

    Para evitar esse risco, public APIS nao sao permitidas chamarem non-public APIS (private and internal)

    Uma funcao INTERNAL INLINE pode utilizar a anotacao PublishedApi, permitindo ser usada em public API inline function.
    Quando uma internal inline e marcada com essa anotacao, seu coropo e checado como se fosse publico.

 */

val Int.binary: String
    inline get() {
        var x = this
        return StringBuilder().run {
            while (x > 0) {
                append(if (x and 1 == 1) "1" else "0")
                x = x shr 1
            }
            toString().reversed()
        }
    }


fun main() {
    /*
        o metodo de acesso que foi marcado como inline sera injetado
        no ponto de chamada.
     */
    println(10.binary)
}