package sample.kotlin.exceptions

import java.lang.IllegalArgumentException
import java.lang.NumberFormatException

fun map(value: String) {
    val p = try {
        Integer.parseInt(value)
    } catch (e: NumberFormatException) {
        return
    }
    println(p)
}

fun interval(value: Int) {
    val p = if (value in 1 .. 100) {
        true
    } else {
        throw IllegalArgumentException("$value not between 1 .. 100")
    }
}


fun main() {
    map("a")
    map("1")

    interval(101)
}