package sample.kotlin.ranges

import java.util.*

// https://www.baeldung.com/kotlin/kotlin-ranges


typealias Predicate<T> = (T) -> Boolean

/**
 * ClosedRange: https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.ranges/-closed-range/
 * Represents a range of values
 * https://kotlinlang.org/docs/reference/ranges.html
 * */

class AnyRange<T : Comparable<T>>(
    override val endInclusive: T
    , override val start: T
) : ClosedRange<T> {
    override fun toString(): String =
        "$start .. $endInclusive"
}

fun <T : Comparable<T>> List<T>.toRange() = AnyRange(this[this.size - 1], this[0])

// https://www.baeldung.com/kotlin/kotlin-ranges
fun mapRange(range: IntRange, fn: Predicate<Int>) =
    range.filter { fn(it) }.toRange()

fun rangeOfDate() {
    val before = Calendar.getInstance()
    before.set(1970, Calendar.JANUARY)
    val range = Date(before.timeInMillis)..Date(Calendar.getInstance().timeInMillis)

    // tentar achar todas as sextas desde 1970
}


/**
 *
 *
 * */

fun rangeOfStrings() {

    /**
     * .. eh uma short syntax para a extension function
     * T.rangeTo
     *
     * "b" in "a" .. "z"
     * eh equivalente a
     * "a".compareTo("b") <= 0 && "b".compareTo("z") <= 0
     * */

    val alpha = "a" .. "z"
    println("b" in alpha)

    /**
     * a comparacao entre strings eh feita de forma lixografica
     * letra a letra
     * */
    val words = "Java" .. "Scala"
    println("Kotlin" in words)

    println("ball" in "a" .. "z")

    println("zoo" in "a" .. "k")

}


fun main() {
    //val oddRange = mapRange(1..100) { it and 1 == 1 }
    //println(oddRange)

    rangeOfStrings()
}