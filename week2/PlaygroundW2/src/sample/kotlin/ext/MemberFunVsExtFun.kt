package sample.kotlin.ext

/**
 * se uma extension function tiver a mesma assinatura de uma funcao membro
 * de uma classe, o compilador sempre optara por chamar a funcao membro
 * */

// a propria ide avisa
// Extension is shadowed by a member: public open fun get(index: Int): Char
fun String.get(idx: Int) = if (this.isNotEmpty()) this[0] else '*'

fun sampleWithStringClass() {
    // get
    println("abc"[2])
}


class Clazz {
    fun main() {
        println("I'm a class Clazz")
    }
}

fun Clazz.main() = println("I am a useless ext fun")

fun main() {
    sampleWithStringClass()
    Clazz().main()
}