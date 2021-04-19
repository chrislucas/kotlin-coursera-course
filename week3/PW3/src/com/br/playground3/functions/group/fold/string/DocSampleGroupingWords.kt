package com.br.playground3.functions.group.fold.string

import com.br.playground3.exts.log

private val words = ("Um livro que marcou época na década de 50, " +
        "escrito por A. E. Van Vogt, tinha o título " +
        "“Voyage of the Space Beagle” e no Brasil recebeu a " +
        "tradução de Missão Inter Planetária. Trata-se de uma " +
        "antologia de histórias de ficção científica, onde o " +
        "autor descreve as viagens de uma nave espacial repleta de " +
        "cientistas que atravessa o universo em busca de planetas distantes " +
        "e está sempre metida em graves problemas. É considerado um clássico" +
        " de aventuras cósmicas e serviu de " +
        "base para muitas criações posteriores do cinema e da literatura").split(" ")

private fun groupingWordsByFirstLetter() {
    /**
     * Exemplo inspirado no sample de lista de frutas nesse link
     * https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/fold.html
     * */
    val map = words.groupingBy { it.first() }.fold(listOf<String>()) { acc, e -> acc + e }
    map.log()
}

fun main() {
    groupingWordsByFirstLetter()
}