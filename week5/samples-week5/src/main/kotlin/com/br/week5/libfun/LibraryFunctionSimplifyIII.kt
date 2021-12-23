package com.br.week5.libfun

import com.br.week5.libfun.sample.model.People

/*
    https://www.coursera.org/learn/kotlin-for-java-developers/lecture/RfwqX/library-functions
 */

private fun twoWaysGroupingCollection(peoples: List<People>) {
    val p = peoples.asSequence()
    val group = p.groupBy { it.age }     // existe uma funcao groupBy para sequencias
        .mapValues { (_, group) -> group.size }

    println(group)

    /*
        Funcao similar a groupBy porem com um comportamento lazy
     */
    val grouping = p
        .groupingBy { it.age }  //
        //.eachCount()
    println(grouping)
    println(grouping.eachCount())
}


fun main() {
    val peoples = listOf(
        People("chris", 12),
        People("chris", 25),
        People("silvio", 12),
        People("silvia", 25),
        People("silvana", 32),
        People("marcos", 12),
    )

    twoWaysGroupingCollection(peoples)
}