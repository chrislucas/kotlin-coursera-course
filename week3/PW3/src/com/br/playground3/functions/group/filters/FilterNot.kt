package com.br.playground3.functions.group.filters

import com.br.playground3.exts.log


fun simpleFilterNotOdds() {
    /**
     * Filter Not: Retorna uma lista com os elementos
     * que nao correspondem com a funcao "predicate"
     *
     * Todos os elementos que passam pela funcao predicate e retonam true nao
     * sao incluidos na lista final
     * */
    (1 .. 100).toList().filterNot { it and 1 == 0 }.log()
}


fun main() {
    simpleFilterNotOdds()
}