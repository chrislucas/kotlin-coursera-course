package com.br.week4.operators.overloading


private fun checkInappropriateListAssignment1() {
    var list1 = listOf(1, 2, 3)
    var list2 = list1
    list2 += 4
    println(list1)
    println(list2)
    list1 = listOf(1)
    println(list1)
    println(list2)
}

private fun checkInappropriateListAssignment2() {
    val list1 = listOf(1, 2, 3)
    var list2 = list1   // list2 eh uma lista imutavel
    // aqui uma lista nova sera criada e a referencia de list2 sera modificada
    // list2 = list2 + 4
    list2 += 4
    println(list1)
    println(list2)
}

private fun checkInappropriateListAssignment3() {
    var list1 = listOf(1, 2, 3)
    println(list1)
    // list = list + 4 // create a new list
    list1 += 4
    println(list1)
}

private fun checkAppropriateListAssignment() {
    val list1 = mutableListOf(1, 2, 3)
    val list2 = list1   // ambos compartilharam a mesma referencia
    // list2 plusAssignment 4 ou list2.add(4) como está implementado no codigo
    list2 += 4
    println(list1)
    println(list1)
}

fun main() {
    checkAppropriateListAssignment()
}