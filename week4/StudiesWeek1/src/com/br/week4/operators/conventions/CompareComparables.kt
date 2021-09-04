package com.br.week4.operators.conventions

import com.br.week4.operators.overloading.GeoPoint2D
import com.br.week4.operators.overloading.MutableGeoPoint2D


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





fun main() {
    println(lessThanOrEquals("chriS", "chris"))
    println(lessThanOrEquals("S", "s"))
    println(lessThanOrEquals("S", "ss"))
}