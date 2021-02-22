package com.br.playground3.nullable

/**
 * O operador !! eh chamado de NOT-NULL ASSERTION
 * */

fun sampleTestSmartCompiler(s: String?) {
    // smart compiler
    if(s == null)
        return
    // se existir uma verificao com return o compilador me permite usar a variavel apos a verificacao
    // sem a necessidade do operador de safe-access
    println(s)
}


/**
 * https://www.coursera.org/learn/kotlin-for-java-developers/lecture/wM6YD/nullable-types
 *
 * A justificativa para existir esse operador segundo engenheira que trabalha na linguagem
 * eh:
 * Existe casos onde o compilador kotlin nao consegue inferir um tipo correto, nesses casos
 * o uso do operador eh necessario/
 * */
fun sampleNotNullAssertion(s: String?) {
    s!!
    // apos o uso do operador NOT-NULL assertion, nao eh necessario usar o operador SAFE-ACCESS
    // o uso do operador nao eh recomendado, podendo gerar um NPE
    println(s.length)


}



fun main() {
    sampleTestSmartCompiler("123")
    sampleTestSmartCompiler(null)

    sampleNotNullAssertion(null)
}