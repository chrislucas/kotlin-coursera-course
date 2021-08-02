package com.br.samples.constructors

import com.br.samples.constructors.visibility.User

class Sample {
    fun log() = println("nothing")
}

// Sem definir um construtor
class AnotherSample

private fun testSampleClass() {
    val sample = Sample()
    sample.log()

    val anotherSample = AnotherSample()
    println(anotherSample)
}

// primary constructor
class Person(val name: String, val age: Int)  {

    init {
        println("Init constructor body")
    }
}

private fun testPersonConstructorInit() {
    val person = Person("chris", 123)
    println(person)
}

private fun testUser() {
    val user = User("#123", "chris")
}

fun main() {
    testPersonConstructorInit()
}