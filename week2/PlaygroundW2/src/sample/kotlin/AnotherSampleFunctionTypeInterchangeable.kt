package sample.kotlin

/**
 * https://kotlinlang.org/docs/reference/lambdas.html#instantiating-a-function-type
 *
 * Se o compilador consegue inferir que
 *
 * A.(B) -> C eh (A, B) -> C
 *
 * Sera que a reciproca eh verdadeira ?
 *
 * */

fun <P, R> transform(p: P, fn: P.() -> R): R = fn(p)

fun histogram(word: String): Map<Char, Int> {
    val histogram = mutableMapOf<Char, Int>()
    for (c in word) {
        histogram[c] = histogram[c]?.plus(1)  ?: 1
    }
    return histogram
}

fun main() {
    // um exemplo que ainda permite usar callable reference :)
    val histogram = transform("christoffer", ::histogram) //{ histogram(this) }
    println(histogram)
}