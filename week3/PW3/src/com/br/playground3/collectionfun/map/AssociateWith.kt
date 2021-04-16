package com.br.playground3.collectionfun.map

import com.br.playground3.exts.log


fun associateWithFastSample() {
    (0 .. 1000).associateWith { (it and 1 == 0) to it }.log()
}


fun main() {
    associateWithFastSample()
}