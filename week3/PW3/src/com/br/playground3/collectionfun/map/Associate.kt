package com.br.playground3.collectionfun.map

import com.br.playground3.exts.log


fun associateFastSample() {
    (0 .. 1000).associate { it % 2 to it }.log()
}


fun main() {
    associateFastSample()
}