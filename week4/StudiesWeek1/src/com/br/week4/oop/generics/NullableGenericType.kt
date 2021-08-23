package com.br.week4.oop.generics



fun <T> List<T>.anyNull() = this.any { it == null }

fun <T> List<T>.checkAnyNull() : List<T?>? = this.run {
    if ( this.any { it == null } ) null else this
}


fun main() {
    // List<Int?>
    val list = listOf(1, null)
    println(list.anyNull())
    println(list.checkAnyNull()) // retorno  List<Int?>?
}