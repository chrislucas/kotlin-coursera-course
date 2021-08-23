package com.br.week4.oop.objects

class CheckStaticMethodCompanionObject {

    companion object {
        fun a() = println("fun A")

        @JvmStatic
        fun b() = println("fun B")
    }
}

object CheckStaticMethodObject {
    fun a() = println("fun A")

    @JvmStatic
    fun b() = println("fun B")

    // Nested object sao permitidos dentro de objects
    object NestedObject {
        fun haveFun(fn: () -> Unit) = fn()
    }
}


class OuterClass {
    /**
     *  Modifier 'inner' is not applicable to 'object'
     *  o modificador inner implica que a inner class guardara a referencia da outer class
     *  um object uma classe singleton no final e poderia existir inumeras instancias de OuterClass
     *  de vazamento de memoria se quisemos eliminar  o INNER OBJECT pq ele teria associado a ele uma serie
     *  de referencias de outer class e nao seria claro qual deveria ser destruida
     * */
    //inner object InnerClass

    // nested object sao possiveis

    object NestedObject {
        fun invokeSomething(fn: () -> Unit) = fn()
    }
}

