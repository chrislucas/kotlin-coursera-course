package com.br.playground3.functions.group.filters

import com.br.playground3.exts.log


fun simpleFilterOdds() {
    (1 .. 100).toList().filter { it and 1 == 0 }.log()
}

fun main() {
    simpleFilterOdds()
}