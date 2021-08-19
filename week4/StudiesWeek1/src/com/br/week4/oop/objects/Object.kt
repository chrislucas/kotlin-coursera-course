package com.br.week4.oop.objects

/**
 * object == single
 * object expressions substitui a implementacao de anonymous class em Java
 *
 * object expression (OE) e objects declaration singletons sao conceitos diferentes
 * object expression nao sao singletons, a cada chamada a uma OE uma nova instancia
 * eh criada
 * */

object SingletonObject {
    fun log() = println("Singleton object")
}


private fun checkInstanceOfSingletonObject() {
    val p = SingletonObject
    val q = SingletonObject
    println(p)
    println(q)
    println(p == q)
}

interface InvokeBehavior {
    fun invoke()
}

val invokeBehaviorExpression = object : InvokeBehavior {
    override fun invoke() {
        println(this)
    }
}

private fun checkInstanceOfObjectExpression() {
    val p = invokeBehaviorExpression
    val q = invokeBehaviorExpression
    println(p)
    println(q)
    println(p == q)
}

private fun test() {
    fun checkInvokeBehavior(invokeBehavior: InvokeBehavior) {
        println(invokeBehavior)
        //invokeBehavior.invoke()
    }

    checkInvokeBehavior(object : InvokeBehavior {
        override fun invoke() {
            println(0xfa)
        }
    })

    checkInvokeBehavior(object : InvokeBehavior {
        override fun invoke() {
            println(0xfb)
        }
    })

    fun checkSingletonObject(singleton: SingletonObject) {
        println(SingletonObject)
    }

    checkSingletonObject(SingletonObject)
    checkSingletonObject(SingletonObject)

   // checkInvokeBehavior(invokeBehaviorExpression)
    // checkInvokeBehavior(invokeBehaviorExpression)
}

fun main() {
    test()
}







