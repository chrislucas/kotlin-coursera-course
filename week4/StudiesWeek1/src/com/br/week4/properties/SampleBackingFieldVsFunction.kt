package com.br.week4.properties

/**
 * aqui rodamos a funcao run que executa um print e retorna o valor da ultima linha executada
 * no caso 42
 * */
val lambda1 = run {
    println("a")
    42
}
// abaixo temos uma funcao getter personalizada
val variable1: Int
    get() {
        println("b")
        return 42
    }

/**
 * a funcao abaixo imprime 2x a variavel lambda1. Ao usar essa variavel
 * para impressao ou se definissemos o valor de uma outra variavel X com
 * o valor dela e usassemos-a novamente o comportamento seria o seguinte:
 *  - a primeira vez a funcao lambda eh executada, assim tudo que estiver
 *  dentro do bloco dela será feito (no caso sera impresso "a"). Ela retornará
 *  o valor 42
 *  - nas proximas vezes que lambda1 fosse usada, somente o valor 42 seria retornado,
 *  a expressao lambda nao seria mais executada
 * */
private fun sample1() {
    println("$lambda1, $lambda1")
}

/**
 * Diferente da funcao lambda, uma funcao getter personalizada eh executada sempre que
 * sua propriedade eh utilizada, seja para definir uma outra variavel X ou para uma
 * impressao na saida padrao (como no caso abaixo)
 * */
private fun sample2() {
    println("$lambda1, $lambda1, $variable1, $variable1")
}

fun main() {
    //sample1()
    sample2()
}