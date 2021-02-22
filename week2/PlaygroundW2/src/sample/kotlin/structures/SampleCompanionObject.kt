package sample.kotlin

/**
 * https://kotlinlang.org/docs/reference/object-declarations.html#companion-objects
 *
 * Object Expressions and Declarations
 *
 * 1) Object sao usados nos casos que precisamos de uma classe ligeiramente
 * diferente de uma ja existente, mas nao queremos usar o recurso de heranca,
 * declarando uma subclasse
 *
 * Sintaxe para criar um objeto anonimo a partir de uma classe
 *
 * val instance = object: Class {
 *      override fun foo() {}
 * }
 *
 * se a superclasse possui um construtor, esse construtor deve ser explicitamente
 * utilizado ao criar uma instancia anonima
 *
 * val instace = object: SuperClassA(B), InterfaceA {}
 *
 * */


interface Factory<T> {
    fun create(description: String): T
}


data class Item(val description: String) {
    companion object : Factory<Item> {
        override fun create(description: String): Item = Item(description)
    }
}

fun main() {
    val f: Factory<Item> = Item
    val item = f.create("ola mundo")
    println(item)
}