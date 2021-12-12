package com.br.week4.conventions.playground

// https://www.coursera.org/learn/kotlin-for-java-developers/lecture/fZtQF/conventions


val fixedInterval = 1 .. 100
val anotherFixedInterval = 1.rangeTo(100)

private fun checkEquality() = fixedInterval == anotherFixedInterval


private fun check() = 1 in 0 .. 100


fun main() {
    println(checkEquality())
}