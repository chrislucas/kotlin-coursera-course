package com.br.playground3.collectionfun.map

import com.br.playground3.exts.log


fun associateBySimpleSample() {
    val range = (0 .. 1000)
    //range.associateBy { it }.log()
    range.associateBy({it % 2}, {it}).log()
}


fun main() {
    associateBySimpleSample()
}