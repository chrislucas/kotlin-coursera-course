package com.br.playground3.memberref

fun fnMemberClassReference() {
    val persons = arrayOf(Person("chris", 15), Person("Lucas", 25))
    // sintaxe para acessar um membro da classe (member reference )
    println(persons.maxByOrNull(Person::age))
}

fun main() {
    fnMemberClassReference()
}