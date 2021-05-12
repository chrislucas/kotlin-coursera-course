package com.br.week4.properties

val function1 = run {
    println("run: function1")
    42
}

val function2 =  function1.let {
    println("run: function2")
    42
}

val function3 = with(function2) {
    println("run: function3")
    42
}

val variable1: Int
    get() {
        println("get value")
        return 42
    }

private fun runTest1() {
    println("$function1, $function1")
    println("$function1, $function1")
}

private fun runTest2() {
    println("$variable1, $variable1, $function1, $function1")
}

private fun runTest3() {
    println("$variable1, $variable1, $variable1, $variable1")
}

private fun runTest4() {
    val (a, b, c, d) = arrayOf(variable1, variable1, variable1, variable1)
    println("$a, $b, $c, $d")
}

private fun runTest5() {
    val (a, b, c, d) = arrayOf(function1, function1, function1, function1)
    println("$a, $b, $c, $d")
}


private fun runTest6() {
   val (a, b, c, d) = arrayOf(function1, variable1, function1, variable1)
    val template = "$a, $b, $c, $d"
    println("ok")
}

/**
 * Funcionamento curioso:
 * Ao  atribuir as variaveis function1 e variavel1 a outras variaveis dentro do
 * metodo runTest7 ou como nos metodos acima onde adicionamos elas a templates de string
 * a ScopeFun e o metodo de acesso explicito get sao chamados. Entretando um comportamento
 * diferente ocorre entre a ScopeFun e o metodo de acesso quando atribuimos os valores
 * delas a mais de um variavel.
 *  ScopeFun: se atribuimos mais de uma  vez a funcao a variaveis o que estiver
 *  no corpo da funcao lambda atribuida a ela sera executado UMA UNICA VEZ e todas as variaveis que receberam
 *  a atribuicao terao o valor de retorno.
 *
 *  Metodo de acesso get: todas as vezes que o metodo de acesso for invoaado
 *  o bloco de codigo dentro de seu escopo sera executado
 * */
private fun runTest7() {
    val e = function1
    val f = function1
    val g = variable1
    val h = variable1
    println("ok")
}

private fun runTest8() {
    var e = function1
    e = function1
    var f = variable1
    f = variable1
    println("$e, $f")
}

private fun runTest9() {
    var e = function1
    e = function1
    var f = function2
    f = function2
    f = function3
    println("$e, $f")
}

fun main() {
    runTest9()
}