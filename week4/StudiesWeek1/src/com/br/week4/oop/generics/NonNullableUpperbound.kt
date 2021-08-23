package com.br.week4.oop.generics

fun <T : Any, R : Any> List<T>.transform(map: (T) -> R): List<R> =
    this.run {
        val mutableList = mutableListOf<R>()
        forEach {
            mutableList.add(map(it))
        }
        mutableList.toList()
    }


private fun <T : Any> fn(values: List<T>) {
    // do nothing
    values.forEach { println(it) }
}

fun main() {

    // similar a funcao MAP
    val list1 = listOf(1, 2, 5, 7).transform { it % 2 == 0 }
    println(list1)


    val constant = listOf(1, 2, 3, 4, 5, 6, 7, 8, null)

    // mas MAP funciona para tipos nulos tambem
    // grosseiramente resumindo fun <T, R> T.map ((T) -> R) : R
    val list2 = constant.map { it?.rem(2) == 0 }
    println(list2)

    /**
     * Int.mod(other: Int)
     * Calculates the remainder of flooring division of this value by the other value.
     * */
    println(constant.map { it?.mod(2) == 0 })


    // Unresolved reference. None of the following candidates is applicable because of receiver type mismatch:
    //val list2 = listOf(1, 2, 5 , null).transform {}
    // Type mismatch.
    //Required:
    //Any
    //Found:
    //Int?
    //fn(listOf(1, 2, 5 , null))
}