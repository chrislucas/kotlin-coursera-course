package com.br.playground3.memberref

fun fnBoundedReference(person: Person, someAge: Byte) {
    // bound reference
    //a referencia esta vinculada a instancia
    //val isOlderThanSomeAge = person::flexibleIsOlder
    val isOlderThanSomeAge: (Byte, (Byte, Byte) -> Boolean) -> Boolean = person::flexibleIsOlder
    // lambda equivalente a funcao membro da class Person
    val eqLambda = { age: Byte, fn: (Byte, Byte) -> Boolean -> person.flexibleIsOlder(age, fn) }

    // veja que a chamada nao necessida que seja passada uma instancia
    println(isOlderThanSomeAge(someAge) { p, q -> p > q })
    println(eqLambda(someAge) {p, q -> p > q})
    println("compare fun: ${isOlderThanSomeAge == eqLambda}")
}


fun main() {
    fnBoundedReference(Person("chris", 32), 45)
    println("")
    fnBoundedReference(Person("lucas", 31), 23)
}