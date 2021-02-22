package sample.kotlin.ext

open class Parent {
    open fun main() {
        println("Parent")
    }
}


class Child() : Parent() {
    override fun main() {
        println("Child")
    }
}

/**
 * Sempre lembrar que extension functions no final serao transformadas em metodos
 * estaticos de classes auxiliares
 * */

// public static void extFun(Parent parent) sout("bla bla bla")
fun Parent.extFun() = println("extFun in Parent class")
// public static void extFun(Child parent) sout("bla bla bla")
fun Child.extFun() = println("extFun in Child class")

/**
 * use o menu
 * code > decompile kotlin to java
 * para ver o resultado descrito acima. Veremos os 2 metodos estaticos criados
 * numa classe que leva o nome desse arquivo, "ParentExtFunKt.java"
 * */


fun main() {
    val instance: Parent = Child()
    instance.main()
    // O codigo java ficaria
    // Parent instance
    // extFun(instance)
    instance.extFun()

    val anotherInstance = Child()
    anotherInstance.extFun()
}