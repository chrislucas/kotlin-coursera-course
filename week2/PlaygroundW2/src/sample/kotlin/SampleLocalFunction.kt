package sample.kotlin

/**
 * https://kotlinlang.org/docs/reference/functions.html#local-functions
 * */


class Graph {
    val struct = mutableListOf<MutableList<Int>>()

    fun add(p: Int, q: Int) {
        struct[p].add(q)
    }
}

fun main() {

}