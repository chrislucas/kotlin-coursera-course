package com.br.week4.conventions.playground

// https://www.coursera.org/learn/kotlin-for-java-developers/lecture/fZtQF/conventions


data class WrapperComparableTypes<T:Comparable<T>>(val value: T)

/*
    a > b - a.compareTo(b) > 0
    a < b - a.compareTo(b) < 0
    a >= b - a.compareTo(b) >= 0
    a <= b - a.compareTo(b) <= 0
 */

operator fun <T:Comparable<T>>  WrapperComparableTypes<T>.compareTo(wrapper: WrapperComparableTypes<T>)
    = this.value.compareTo(wrapper.value)

infix fun <T:Comparable<T>>  WrapperComparableTypes<T>.lessThan(wrapper: WrapperComparableTypes<T>)
    = this < wrapper

infix fun <T:Comparable<T>>  WrapperComparableTypes<T>.greaterThan(wrapper: WrapperComparableTypes<T>)
        = this > wrapper


private fun checkComparableInt() {
    val p = WrapperComparableTypes(1)
    val q = WrapperComparableTypes(2)

    println(p < q)
    println(p lessThan  q)
}


private fun checkComparableString() {
    val p = WrapperComparableTypes("cccb")
    val q = WrapperComparableTypes("ccca")

    println(p > q)
    println(p greaterThan q)
}


fun main() {
    checkComparableInt()
    println("***********************")
    checkComparableString()
}