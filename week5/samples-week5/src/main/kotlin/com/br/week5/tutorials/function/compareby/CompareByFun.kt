package com.br.week5.tutorials.function.compareby

val compInt = compareBy<Int> { it }

val compDouble = compareBy<Double> { it }

val comparePersonByWeight = compareBy<Person> { it.weight }

val comparePersonByName = compareBy<Person> { it.name }


data class Person(val name: String, val weight: Double)


private fun checkCompInt() {
    println(
        compInt.compare(1, 2)
    )

    println(
        compInt.compare(2, 1)
    )
}

private fun checkCompDouble() {
    println(compDouble.compare(2.5, 4.5))
}

/*
    https://www.codevscolor.com/kotlin-maxof-find-maximum-value
 */
private fun checkComparaPersonByName() {
    val p = Person("Chris", 90.5)
    val q = Person("Lucas", 87.5)
    println(maxOf(p, q, comparePersonByName))

    val r = Person("Tulio", 34.2)
    println(maxOf(p, q, r, comparePersonByName))
}


fun main() {
    checkComparaPersonByName()
}