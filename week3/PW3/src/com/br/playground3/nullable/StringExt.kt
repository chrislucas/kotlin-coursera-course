package com.br.playground3.nullable

fun String?.isEmptyOrNull() = this == null || this.matches(Regex("\\s+")) || this.isEmpty()

fun main() {
    val s: String? = null
    println("            ".isEmptyOrNull())
    println("".isEmptyOrNull())
    println(" ".isEmptyOrNull())
    println(s.isEmptyOrNull())
}