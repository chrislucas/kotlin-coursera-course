package com.br.week5.inlinefun.exercise.filter

/*
    https://www.coursera.org/learn/kotlin-for-java-developers/ungradedWidget/lmAxP/kotlin-playground-inlining-of-the-filter-function
 */
inline fun <T> Iterable<T>.filter(fn: (T) -> Boolean) : List<T> {
    val resource = arrayListOf<T>()
    this.forEach {
        if (fn(it)) {
            resource.add(it)
        }
    }
    return resource
}