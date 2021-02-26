package com.br.playground3.quizimpl

import kotlin.concurrent.fixedRateTimer


fun <T> fnFirst(collection: Array<T>, pred: (T) -> Boolean) {
    val p = try {
        collection.first(pred)
    } catch (e: NoSuchElementException) {
        "nenhum elemento atende o requisito"
    }
    println(p)
}


fun whatNotDo(collection: Array<Hero>) {
    val maxDiffAge = collection.flatMap {
        collection.map { hero ->
            it to hero
        }
    }
        .maxByOrNull { it.first.age - it.second.age }
    println(maxDiffAge)
}

fun fixingWhatNotDo(collection: Array<Hero>) {
    val combinations = collection.flatMap { first ->
        collection.map { second -> first to second }
            .filter { it.first != it.second }
    }

    println(combinations.size)

    //val maxDiffAge = combinations.maxByOrNull { it.first.age - it.second.age }
    val maxDiffAge = combinations.maxByOrNull { it.second.age }

    println(maxDiffAge)
}

fun anotherWay(collection: Array<Hero>) {
    val possibilities = collection.toList().zipWithNext()

    println(possibilities)
}

fun main() {
    val heroes = arrayOf(
        Hero("a", 1, Gender.MALE),
        Hero("b", 2, null),
        Hero("c", 3, null),
        Hero("d", 4, Gender.FEMALE),
        Hero("e", 5, Gender.FEMALE),
        Hero("f", 5, Gender.FEMALE),
        Hero("g", 1, Gender.FEMALE),
        Hero("g", 10, Gender.FEMALE)
    )

    /**
     * first, last, firstOrNull, lastOrNull
     * first e last lanca uma excecao se a lista nao tiver elementos
     * as demais devolvem null
     * */
    println(heroes.last())
/*
    fnFirst(heroes) { hero: Hero -> hero.age == 30 }

    println(heroes.distinct())

    println(heroes.distinctBy { it.gender })

    println(heroes.partition { it.gender == Gender.MALE })

    println(heroes.maxByOrNull { it.age })

    println(heroes.all { it.gender == Gender.MALE })
    println(heroes.any { it.gender == Gender.MALE })
    println(heroes.none { it.gender == Gender.MALE })

    println(heroes.groupBy { it.age }.maxByOrNull { (_, group) -> group.size })

    println(heroes.associateBy { it.name })

    val rs = heroes.associateBy { it.name }.getOrElse("b") {
        Hero("default", 0, null)
    }.age

    println(rs)

    val rs1 = heroes.associate { it.name to it.gender }
        //.getOrDefault("u", Gender.UNKNOWN)
        .getOrElse("u") { Gender.UNKNOWN }
    println(rs1)

    whatNotDo(heroes)
*/
    fixingWhatNotDo(heroes)
}