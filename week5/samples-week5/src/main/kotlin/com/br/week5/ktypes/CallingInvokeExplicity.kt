package com.br.week5.ktypes

val fn: (() -> Int) -> Int = { lambda -> lambda() }

var gn: (() -> Int)? = null

val hn: (() -> Int) = {
    println(42)
    42
}


val kn: ((() -> Unit)?) -> Unit? = { lambda -> lambda?.invoke() }

/*
    O interessante eh que mesmo que eu defina um nome para o argumento
    no tipo da variavel, ao construir a funcao lambda eu preciso definir
    o nome novamente e pode ser diferente do que foi definido anteriormente
    veja: function -> fn
 */
val mn: (function: (() -> Unit)?) -> Unit? = { fn -> fn?.invoke() }


val binaryIntOperation = { p: Int, q: Int, op: (Int, Int) -> Int
    ->
    op(p, q)
}

fun main() {
    fn.invoke { 42 }
    hn.invoke()
    hn()

    gn?.invoke()
    gn = hn
    gn?.invoke()


    //val s = binaryIntOperation(10, 4) { p, q -> p xor q }
    //println(s)
}