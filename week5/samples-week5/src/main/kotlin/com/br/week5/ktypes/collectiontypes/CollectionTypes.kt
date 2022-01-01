package com.br.week5.ktypes.collectiontypes

/*
    https://www.coursera.org/learn/kotlin-for-java-developers/lecture/R3aKw/collection-types
 */


/*
    Quando criamos uma ReadOnly List a partir de uma MutableList, a primeira
    observa as mudancas que ocorrem na segunda

    kotlin.List <- kotlin.MutableList <- java.util.ArrayList


 */

private fun listReadOnlyObservesMutableListChanges() {
    val mutableList = mutableListOf(1, 2, 3)
    val list: List<Int> = mutableList
    mutableList += 4
    println(list)

    val newList = mutableList.map { it * it }.toMutableList()
    mutableList.removeAll { true }
    newList.forEach { mutableList += it }

    println(list)
}

/*
    Pq existe um tipo de colecao imutavel que nào é bem imutável uma vez que se definimos
    o seu valor a partir de uma colecao imutavel o primeiro passa a observar as mudancas do segundo
    e deixa de ser imutável tbm. ?

    Porque é interessante ter uma lista que nao tenha que nao pode ser modificada após se criada
    O exemplo abaixo nos da um bom exemplo de como criar uma lista
 */

data class Customer(val name: String)
object Shop {
    private val customers = mutableListOf<Customer>()

    fun getCustomers(): List<Customer> = customers
}

class AnotherShop {
    private val customers = mutableListOf<Customer>()

    fun getCustomers(): List<Customer> = customers
}

private fun testBehaviorOfList() {
    var customers = Shop.getCustomers()
    // customers += Customer("Chris")
    customers = mutableListOf(Customer("Chris"))
    println(customers)
    println(Shop.getCustomers())
}

private fun check() {
    val shop = AnotherShop()
    var list = shop.getCustomers()
    println(list)
    list = mutableListOf(Customer("Chris"))
    println(list)
    println(shop.getCustomers())
}

private fun checkMutableListReferences() {
    val mutableList = mutableListOf(1, 2, 3)
    val mutableList2 = mutableList
    mutableList2 += 4
    println(mutableList)

    //fun Int.op(function: (Int) -> Int) = function(this)

    fun changeData(list: MutableList<Int>) {
        //val newList = list.map { it::op.invoke { value -> value xor (value - 1) } }
        val newList = list.map(Int::dec)
        list.removeAll { true }
        list.addAll(newList)
    }
    changeData(mutableList2)

    println(mutableList2)
    println(mutableList)
}


fun main() {
    checkMutableListReferences()
}