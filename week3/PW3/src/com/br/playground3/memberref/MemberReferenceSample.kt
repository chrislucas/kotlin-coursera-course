package com.br.playground3.memberref

import com.br.playground3.samples.combinatory.Combinatory

data class Person(val name: String, val age: Short)


fun fnFunctionReference() {
    // Function reference
    val ncr = Combinatory::ncr
    println(ncr)

    println(ncr.run { this(12, 3) })
    //println(ncr.let { it(12, 3) })

    println(::fnMemberClassReference)
}


fun fnMemberClassReference() {
    val persons = arrayOf(Person("chris", 15), Person("Lucas", 25))
    // sintaxe para acessar um membro da classe (member reference )
    println(persons.maxByOrNull(Person::age))
}

fun main() {
    fnFunctionReference()
}