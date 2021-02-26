package com.br.playground3.lambda


fun main() {
    val range = (1..100).toList()
    range.forEach {
        if (it % 3 == 0) return@forEach //  break ou continue ? continue.
        // a label indica que o return esta no escopo do forEach e nano da funcao mais acima
        // o interessante aqui eh que break e continue nao sao permitidos dentro do forEach
        // prara simular o comportamento do continue temos o return com a label @forEach
        // mas o break nao da para simular usando o return, pq a funcao de fora acaba retornando
        // a quem a chamou
        println(it)
    }

    // return dentro do forEach != break dentro do for

    range.forEach {
        if (it % 3 == 0) return // break ou continue ? break
        // aqui o return faz com que a funcao de escopo mais acima encerre e retorne para quem
        // a chamou
        println(it)
    }
}