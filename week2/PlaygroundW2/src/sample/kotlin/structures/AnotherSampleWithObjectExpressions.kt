package sample.kotlin.structures

interface Action {
    fun call()
}
// https://kotlinlang.org/docs/reference/object-declarations.html#object-expressions

fun main() {
    val action = object : Action {
        override fun call() {
            println("execute action")
        }
    }

    action.call()
}