package com.br.week5.lambdawithreceiver

import com.br.week5.eq

/*
    https://www.coursera.org/learn/kotlin-for-java-developers/ungradedWidget/VazrU/kotlin-playground-member-extensions
 */


class Words {
    private val list = mutableListOf<String>()

    // TODO

    fun String.record(): Words =
        this@Words.apply {
            this.list += this@record
        }


    operator fun plus(data: String) {
        list += data
    }

    operator fun String.unaryPlus() {
        list += this
    }

    override fun toString() = list.toString()
}

fun main(args: Array<String>) {
    val words = Words()
    with(words) {
        // The following two lines should compile:
        "one".record()
        + "two"
    }
    println(words.toString())
    println(words.toString() eq "[one, two]")
}