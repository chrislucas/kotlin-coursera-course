package sample.kotlin.ranges


// usando a keyword in para checar se um elemento
// esta dentro de um intervalo

fun checkIn(value: Char, range: CharRange = 'a' .. 'z')
    = value in range

fun checkIn(value: Int, range: IntRange) = value in range

/**
 * https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.ranges/-closed-range/
 *
 * */

fun sampleClosedRange() {
    val range = "ab" .. "az"
    println("ac" in range)
}

fun main() {
    sampleClosedRange()
}