package com.br.playground3.collectionfun.map

import com.br.playground3.exts.log


/**
 * Diferenca entre groupBy e associateBy
 *  - de preferencia a associateBy se a estrutura que vc quer montar tera chaves unicas
 *
 * */


data class Person(val name: String, val age: Int, val document: SocialDocument)

data class SocialDocument(val doc: String)

fun Array<Person>.groupByAge() : Map<Int, List<Person>> = this.groupBy { person -> person.age }

fun Array<Person>.associateBySocialDocument() : Map<SocialDocument, Person> = this.associateBy { person -> person.document }



fun groupingCollectionOfPerson() {
    val persons = arrayOf(
        Person("a", 32, SocialDocument("12332145678")),
        Person("b", 31, SocialDocument("46546546566")),
        Person("c", 13, SocialDocument("46546544874")),
        Person("d", 13, SocialDocument("12312322233")),
    )

    persons.groupByAge().log()
    println("*********************************************************************")
    persons.associateBySocialDocument().log()
}

fun associateBySimpleSample() {
    val range = (0 .. 1000)
    //range.associateBy { it }.log()
    range.associateBy({it % 2}, {it}).log()
}


fun main() {
    groupingCollectionOfPerson()
}