package com.br.playground3.memberref

/**
 * Bound and Non-bound Reference
 *
 * Uma referencia eh considerada non-bound quando ela nao
 * esta atrelada a nenhuma instancia especifica. Podemos
 * executar a funcao passando qualquer instancia do tipo da classe
 * cuja funcao eh atribuida como referencia a uma variavel.
 *
 * as duas funcoes abaixo exemplificam a definicao de non-bound reference
 * */

fun fnNonBoundedRef() {
    // a  regular non-bound reference
    // val agePredicate = Person::flexibleIsOlder
    val agePredicate: (Person, Byte, (Byte, Byte) -> Boolean) -> Boolean = Person::flexibleIsOlder
    // type of this member reference
    // (Person, Byte, (Byte, Byte) -> Boolean) -> Boolean
    // Note que para chamar essa funcao precisamos passar uma instancia da classe Person
    val chris = Person("chris", 23)
    val isOlder = agePredicate(chris, 32) { p, q -> p > q }

    /**
     *
     * agePredicate = { person, someage, fn(Int, Int) ->
     *      person.flexibleIsOlder(age, someage)
     *          // que vai executar a funcao fn(age, someage)
     *          // que eh a expressao lambda passada no fim da funcao agePredicate
     * }
     * */
    println(isOlder)
    // lambda equivalente a funcao membro da class Person
    val eqLambda = { person: Person, someage: Byte, fn: (Byte, Byte) -> Boolean ->
        person.flexibleIsOlder(someage, fn)
    }
    println(eqLambda(chris, 32) { p, q -> p > q })

    println(agePredicate == eqLambda)
}

fun fnNonBoundedRef2() {
    // val agePredicate: (Person, (Byte, Byte) -> Boolean) -> Boolean = Person::simplestIsOlder
    val agePredicate = Person::fixedValueIsOlder
    val isOlder = agePredicate(Person("chris", 23)) { p, q -> p > q }
    println(isOlder)
}

fun main() {
    fnNonBoundedRef()
}