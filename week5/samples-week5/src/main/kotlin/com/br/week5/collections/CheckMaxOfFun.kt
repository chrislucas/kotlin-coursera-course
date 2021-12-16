package com.br.week5.collections

/*
    Samples
    https://www.codevscolor.com/kotlin-maxof-find-maximum-value
    https://ozenero.com/kotlin-list-methods-max-maxby-maxwith

    https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.comparisons/max-of.html

    Collection
    https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.sequences/max-of.html
 */

private fun checkMaxOf() {
    val p = (1..100).toList().maxOf { it }

    println(p)
}

//     https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.comparisons/max-of.html
private fun checkMaxOfTwoValues(a: Int, b: Int) {
    println(maxOf(a, b))
}


private fun <T> checkMaxOfWithComparator(a: T, b: T, comparator: Comparator<T>) {
    println(maxOf(a, b, comparator))
}


private fun <T : Comparable<T>> checkMaxOfWithLambdaComparator(a: T, b: T, lambda: (a: T, b: T) -> Int) {
    println(maxOf(a, b, lambda))
}

private fun runCheckMaxOfWithComparator() {
    checkMaxOfWithComparator("chris", "lucas") { p, q -> p.compareTo(q) }
    checkMaxOfWithComparator("cris", "chris") { p, q -> p.compareTo(q) }
}

private fun runCheckMaxOfWithLambdaComparator() {
    checkMaxOfWithLambdaComparator(12, 23) { a, b -> a.compareTo(b) }
    checkMaxOfWithLambdaComparator(12, -23) { a, b -> a.compareTo(b) }
    checkMaxOfWithLambdaComparator(-12, -23) { a, b -> a.compareTo(b) }
}


fun main() {
    //checkMaxOfTwoValues(10, -10)

    runCheckMaxOfWithLambdaComparator()
}