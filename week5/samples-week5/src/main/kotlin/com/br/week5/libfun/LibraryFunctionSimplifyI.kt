package com.br.week5.libfun

import kotlin.math.sqrt


/*
    https://www.coursera.org/learn/kotlin-for-java-developers/lecture/RfwqX/library-functions
 */

/*
    1 simplify

    listOf().filter { predicate }.size
 */


private fun simplify1() {
    val range = (1..100).toList()
    val a = range.filter { it and 1 == 1 }.size
    println(a)
    println("******************************************************")
    val b = range.count { it and 1 == 1 }
    println(b)
}

private fun simplify2() {
    val range = (1..100).toList()
    val a = range.sortedBy { it }.reversed()
    println(a)
    println("******************************************************")
    val b = range.sortedByDescending { it }
    println(b)
}

/*
    Como era
    people
      .map { person ->
        person.takeIf { it.isPublicProfile }?.name
      }
      .filterNotNull()

     de forma mais simples

    people.mapNotNull { person ->
      person.takeIf { it.isPublicProfile }?.name
    }
 */

private fun simplify3() {
    val range = (1..100).map { Pair(it, if (it and 1 == 0) null else it * it) }.toList()
    val a = range.map { it ->
        it.takeIf { it.second != null }?.second
    }.filterNotNull()
    println(a)

    println("******************************************************")
    val b = range.mapNotNull { it.second }
    println(b)
}

/*
    listOf().filterNotNull().map {
        it?.predicateFunction() or it?.attribute
    }
    or
    listOf().mapNotNull {
        it?.predicateFunction() or it?.attribute
    }
 */
private fun simplify4() {
    val nullablePairs = (1..100).map { if (it and 1 == 1) null else Pair(it, it * it) }

    fun Double.isSquarePerfect(): Boolean {
        val s = sqrt(this)
        return s * s == this
    }

    val c = nullablePairs.filterNotNull()
        .map {
            it.takeIf { (it.second * 1.0).isSquarePerfect() }
        }
    println("$c, ${c.count()}")
    println("******************************************************")

    val d = nullablePairs.mapNotNull {
        it?.takeIf { it.second.toDouble().isSquarePerfect() }
    }
    println("$d, ${d.count()}")
}

fun main() {
    simplify4()
}