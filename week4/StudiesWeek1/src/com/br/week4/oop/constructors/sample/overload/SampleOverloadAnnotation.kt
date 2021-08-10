package com.br.week4.oop.constructors.sample.overload

/**
 * https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.jvm/-jvm-overloads/
 *
 * https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.jvm/-jvm-overloads/
 *
 * passa instrucoes para o compilador Kotlin criar sobrecargas de um determinado
 * metodo que possui parametros default
 *
 * Se o metodo tem M argumentos e N deles tem valores default o compilador
 * ira gerar N metodos de sobrecarga. O primeiro tem N - 1 parametros com o N-esimo
 * sendo o parametro default
 *
 * https://medium.com/android-news/demystifying-the-jvmoverloads-in-kotlin-10dd098e6f72
 *
 * */

data class Model @JvmOverloads constructor(val context: Context, val index: Int = 0) :
    BaseClass(context, index)

class MyOverLoad : Sample() {

    override fun log(value: Int) {
        println(value)
    }
}

data class User(val model: Model)


fun main() {

    val context = BaseClass.Context("User::class.java", 0xff)
    val user = User(Model(context))

    println(user)

}