package com.br.week5.libfun

import com.br.week5.libfun.sample.model.People
import java.util.function.BiFunction


/*
    https://www.coursera.org/learn/kotlin-for-java-developers/lecture/RfwqX/library-functions
 */




private fun simplifyGrouping(peoples: List<People>) {
    val map: MutableMap<Int, MutableList<People>> = mutableMapOf()

    fun grouping1(map: MutableMap<Int, MutableList<People>>, peoples: List<People>) {
        for (person in peoples) {
            // 1
            if (person.age !in map) {
                map[person.age] = mutableListOf()
            }
            // 2
            val group = map.getValue(person.age) // map[person.age]
            group += person
        }
    }

    fun grouping2(map: MutableMap<Int, MutableList<People>>, peoples: List<People>) {
        for (person in peoples) {
            /*
                Simplificar 1 e 2
             */

            val group = map.computeIfAbsent(person.age) {
                mutableListOf()
            }

            group += person
        }
    }

    // resposta da instrutora do curso
    fun grouping3(map: MutableMap<Int, MutableList<People>>, peoples: List<People>) {
        /*
            Simplificar 1 e 2
        */

        for (person in peoples) {
            val group = map.getOrPut(person.age) { mutableListOf() }
            group += person
        }
    }

    grouping2(map, peoples)

    println(map)
}

private fun simplifyGrouping1Fun(peoples: List<People>) {
    println(peoples.groupBy { it.age })
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
    //simplifyGrouping(peoples)

    simplifyGrouping1Fun(peoples)
}


