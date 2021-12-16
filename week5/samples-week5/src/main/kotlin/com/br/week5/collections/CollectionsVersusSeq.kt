package com.br.week5.collections

import com.br.week5.inlinefun.exercise.filter.filter

/*
    https://www.coursera.org/learn/kotlin-for-java-developers/lecture/wD8Vc/collections-vs-sequences
 */


private fun check() {
    val max = listOf(1, 2 - 3)
        .map { it * it }
        .filter { it and 1 == 0 }
        .maxOrNull()
}