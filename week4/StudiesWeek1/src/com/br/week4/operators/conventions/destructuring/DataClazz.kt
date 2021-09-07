package com.br.week4.operators.conventions.destructuring

private data class Data(val username: String, val pwd: Byte)
/**
 * o compilador gera automaticamente os
 * component1() = String
 * component2() = Byte
 * componentN() = ? se existir
 *
 * da data class acima
 *
 *
 * a classe Pair e a IndexedValue sao definidas como data class por exemplo. Por isso conseguimos
 * aplicar a tecnica de destructuring com instancias delas
 * */

private fun test(data: Data) {
    val (user, pwd) = data
    println("$user, $pwd")
    // da para igonorar um componente tbm usando o underline
    val (another, _) = data
}

fun main() {
    test(Data("chrisluccas", 0x1c))
}