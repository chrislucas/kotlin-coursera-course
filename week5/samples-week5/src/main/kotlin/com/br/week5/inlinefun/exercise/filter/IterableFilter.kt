package com.br.week5.inlinefun.exercise.filter

import com.br.week5.eq

/*
    https://www.coursera.org/learn/kotlin-for-java-developers/ungradedWidget/lmAxP/kotlin-playground-inlining-of-the-filter-function
 */
inline fun <T> Iterable<T>.filter(fn: (T) -> Boolean): List<T> {
    val resource = arrayListOf<T>()
    this.forEach {
        if (fn(it)) {
            resource.add(it)
        }
    }
    return resource
}

fun filterNonZero(list: List<Int>) = list.filter { it != 0 }

fun filterNonZeroGenerated(list: List<Int>): List<Int> {
    val newList = mutableListOf<Int>()
    for (i in list) {
        if (i != 0)
            newList.add(i)
    }

    return newList
}

fun main() {
    val list = listOf(1, 2, 3)
    println(
        filterNonZero(list).toString() eq "[1, 2, 3]"
    )

    println(
        filterNonZeroGenerated(list).toString() eq "[1, 2, 3]"
    )
}