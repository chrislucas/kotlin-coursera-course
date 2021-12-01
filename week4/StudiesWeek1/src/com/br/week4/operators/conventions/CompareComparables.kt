package com.br.week4.operators.conventions


/**
 * a implmentacao <= funciona para qualquer tipo que implemetna
 * a interface Comparable
 *
 * a > b -> a.comp(b) > 0
 * a < b -> a.comp(b) < 0
 * a == b -> a.comp(b) == 0
 * a >= b -> a.comp(b) >= 0
 * a <= b -> a.comp(b) <= 0
 * */

fun lessThanOrEquals(str1: String, str2: String) = str1 <= str2

fun equals(str1: String, str2: String) = str1 == str2


class WrapperComparable<T:Comparable<T>>(val value: T): Comparable<WrapperComparable<T>> {
    override fun compareTo(other: WrapperComparable<T>): Int = this.value.compareTo(other.value)
}

private fun checkCompareComparableClass() {
    val p = WrapperComparable(1)
    val q = WrapperComparable(2)
    println(p < q)

    println(WrapperComparable(20.4) <= WrapperComparable(20.4))

    println(WrapperComparable("chris") < WrapperComparable("chrys"))
}

private fun check() {
    println(lessThanOrEquals("chriS", "chris"))
    println(lessThanOrEquals("S", "s"))
    println(lessThanOrEquals("S", "ss"))
}

fun main() {
    checkCompareComparableClass()
}