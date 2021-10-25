package com.br.week4.operators.overloading.assignmentop.immutable


private fun sampleWithMutableList(){
    val list = mutableListOf<Int>(1,2,3,4,5,6)
    val list2 = list
    list += 7
    println(list)
    println(list2)
}


// 'operator' modifier is inapplicable on this function: must return Unit
/*
Assign Operators sao inuteis em classes que so possuem atributos imutaveis
operator fun <T> List<T>.plusAssign(value: T) {
    this + value
}
 */




private fun sampleWithImmutableList(){
    var list = listOf(1,2,3,4,5,6)
    val list2 = list
    list = list + 4 // assign plus nao funciona aqui
    println(list)
    println(list2)

}

fun main() {
    sampleWithImmutableList()
    sampleWithMutableList()
}