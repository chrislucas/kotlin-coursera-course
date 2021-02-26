package com.br.playground3.memberref

data class Person(val name: String, val age: Byte) {
    companion object {
        private const val LIMIT: Byte = 127
    }

    // duas funcoes bestas so para testar umas ideias
    fun flexibleIsOlder(someAge: Byte, classify: (age: Byte, anotherAge: Byte) -> Boolean) = classify(age, someAge)
    fun fixedValueIsOlder(classify: (age: Byte, anotherAge: Byte) -> Boolean) = classify(age, LIMIT)

    // fn getFlexibleIsOlderBoundRef() : (Byte, (Byte, Byte) -> Boolean) -> Boolean { return ::flexibleIsOlder }
    // resumindo. this::flexibleIsOlder
    fun getFlexibleIsOlderBoundRef() = ::flexibleIsOlder
}
