package com.br.playground3.functions.group.functions

import com.br.playground3.exts.log
import com.br.playground3.functions.group.grouping
import com.br.playground3.toTypedArray

/**
 * https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/reduce.html
 * https://kotlinlang.org/docs/collection-aggregate.html#fold-and-reduce
 * */

private fun fn(m: Int) {
    val interval = (1 .. 100)
    val map = interval.toTypedArray()
        .grouping { it % m}
        .reduce { _, accumulator: Int, element ->
            accumulator + element
        }

    map.log()
}


fun main() {

}