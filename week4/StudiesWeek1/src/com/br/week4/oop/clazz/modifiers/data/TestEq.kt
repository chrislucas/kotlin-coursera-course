package com.br.week4.oop.clazz.modifiers.data

class User(private val name: String)  {
    constructor(user: User): this(user.name)
}

/**
 *  membros da classe que nao sao postos no construtor primario nao
 *  sao utilizados nos metodos toString, hashcode e equals
 * */
data class Data(val value: Int) {
    var point: Point2f? = null
}


private fun compareClasses() {
    val user1 = User("chris")
    val user2 = User(user1)

    println("${user1 == user2}")
    println("${user1 === user2}")
}

private fun compareDataClasses() {
    val data1 = Data(1)
    data1.point = Point2f(2.0, 1.0)
    val data2 = Data(1)
    data2.point = Point2f( -1.0, -2.0)

    // como o membro point: Point2f? nao foi definido como propriedade
    // no construtor primario, ele nao sera utilizado na comparacao no metodo
    // equal entao data1 == data 2 = true
    println(data1 == data2)
}

fun main() {
    compareDataClasses()
}