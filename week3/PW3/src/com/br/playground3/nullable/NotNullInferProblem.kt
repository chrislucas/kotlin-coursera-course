package com.br.playground3.nullable

/**
 * https://www.coursera.org/learn/kotlin-for-java-developers/lecture/wM6YD/nullable-types
 *
 * "Existem situacoes em que podemos supor que uma expressao nao eh nula, mas para o compilador
 * fazer essa suposicao nao eh possivel. Eh mais seguro preparar o codigo para lidar com uma excecao, caso
 * a sua suposicao nao esteja correta"
 *
 * */


class Sample<T>(private val samples: List<T>? = null) {

    private fun isEnabled() = samples?.isNotEmpty() ?: false

    fun execute() {
        /**
         * Abaixo um exemplo onde o compilador nao pode inferir se o atributo
         * "samples" eh ou nao nulo, uma vez que a logica para verificacao
         * esta encapsulada num outro metodo
         * */
        if (isEnabled()) {
            // acabamos sendo forcados a usar o operador NotNullAssertion, mas com alguma seguranca
            for (sample in samples!!) {
                println(sample)
            }
        }
    }

}


// resolvi de cabeca, so estou registrando algo que achei interessante
fun puzzle(x:Int? = null) {
    val mX: Int? = x
    val y = 2
    // aqui temos mX ou 0
    println(mX ?: 0 + y)
    // dando precedencia ao ?:
    // mX ou 0 depois soma-se a y
    println((mX ?: 0) + y)
    // aqui mX ou (0 + y)
    println(mX ?: (0 + y))
}


fun main() {
    puzzle()
    puzzle(1)
}