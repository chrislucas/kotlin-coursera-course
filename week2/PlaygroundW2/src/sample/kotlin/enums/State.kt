package sample.kotlin.enums

import kotlin.reflect.KClass

// Anounymous class dentro de enums
enum class State : ObserverSignal {

    // cada constante de um enum pode declarar suas proprias classes anonimas
    // com seus respectivos metodos, podendo ate sobreescreve-los
    WAITING {
        override fun notifyObserver() {
            println(this)
        }

        override fun signal() = WAITING

        override fun toString(): String {
            return "I'm a ${this.name} state"
        }
    },

    RUNNING {
        override fun signal() = RUNNING
        override fun notifyObserver() {
            println(this)
        }
    };

    abstract fun signal(): State
}

fun <T: Enum<State>> KClass<T>.printValues() = this.java.enumConstants.forEach {
    println("Value: $it, Name: ${it.name}")
}

interface ObserverSignal {
    fun notifyObserver()
}