package com.br.week4.conventions.playground
/*
     s1 == s2
     o mesmo que
     s1.equals(s2)
 */


private fun check() {
    val s = "chris"
    val r = "chris"
    println(s == r)
    println(null == r)
    println(null == null)
}

fun main() {
    check()
}