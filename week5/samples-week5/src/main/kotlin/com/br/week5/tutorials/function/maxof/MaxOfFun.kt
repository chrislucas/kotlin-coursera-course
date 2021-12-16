package com.br.week5.tutorials.function.maxof

/*
        https://www.codevscolor.com/kotlin-maxof-find-maximum-value
 */


data class Person(val name: String, val age: Int) : Comparable<Person> {
    override fun compareTo(other: Person): Int =
        if (age - other.age == 0) {
            0
        } else if (age - other.age < 0) {
            -1
        } else {
            1
        }
}

fun checkWhoIsOlderPerson() {
    println(maxOf(Person("Peter", 23), Person("Chris", 31)))
}


fun comparePersonByName() {
    val compareByName = compareBy<Person> { it.name }
    println(maxOf(Person("Peter", 23), Person("Chris", 31),compareByName ))
}

fun main() {


    comparePersonByName()
}