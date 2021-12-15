package com.br.week5.inlinefun

/*
    https://www.coursera.org/learn/kotlin-for-java-developers/lecture/9Qmtm/the-power-of-inline
 */


//fun exec(fn: () -> Unit) = fn()

fun <R> exec(fn: () -> R): R = fn()

inline fun <R> execute(fn: () -> R): R = fn()

private fun checkExec() {
    val name = "chirs"

    /*
        Uma funcao lambda eh transformada numa ckasse anonima (class PowerOfInline$main$1), se essa funcao
        captura alguma variavel externa, novos objetos sao criados para armazenar os valores dessas variaveis.

        Isso causa um overhead muito grande mesmo para simples implementacao como essa
     */
    exec {
        println("Capturando $name")
    }

    println("Ola, $name")
}

private fun checkExecute() {
    val variable = 23
    /*
        o atributo inline faz com que o codigo da funcao
        seja escrito no local onde ela está sendo chamada
        removendo o overhead ja que nao precisamos chamar a funcao,
        o codigo já está aqui, no local da chamada.

        Em outras palavras: O compilador irá trazer o corpo da funcao para o local da chamada
        ao inves de chamar a funcao. Se a inline function recebe uma lambda como argumento
        e somente faz a chamada dela, então o corpo dessa funcao lambda também será copiado
        para o local da chamada
     */
    execute {
        println("Inlined function $variable")
    }

    /*
        o exemplo acima em bytecode só teria a chamada a println
     */

    println(variable)
}


fun main() {
    checkExec()
}