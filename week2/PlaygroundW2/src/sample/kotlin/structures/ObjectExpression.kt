package sample.kotlin

/**
 * https://kotlinlang.org/docs/reference/object-declarations.html#companion-objects
 *
 * Object Expressions and Declarations
 *
 * 1) Object sao usados nos casos que precisamos de uma classe ligeiramente
 * diferente de uma ja existente, mas nao queremos usar o recurso de heranca,
 * declarando uma subclasse
 *
 * Sintaxe para criar um objeto anonimo a partir de uma classe
 *
 * val instance = object: Class {
 *      override fun foo() {}
 * }
 *
 * se a superclasse possui um construtor, esse construtor deve ser explicitamente
 * utilizado ao criar uma instancia anonima
 *
 * val instace = object: SuperClassA(B), InterfaceA {}
 *
 * */

/**
 * Se precisarmos criar um simples objeto ao inves de uma classe
 * podemos simplesmente cria-lo atraves da seguinte sintaxe
 * val p = object {
 *      val x: Type
 *      val y: Type
 *      ...
 * }
 *
 * Objecto anonimos so podem ser usados somente como tipos
 * em declaracoes locais (dentro de funcoes) ou como retorno de funcoes privadas
 * */

fun sampleMatrixObject(m: Int, n: Int) {
    val identity = object {
        val matrix = Array(m) { _ -> Array(n) { 0 } }
        init {
            for (i in 0 until m) {
                matrix[i][i] = 0
            }
        }
    }
    println(identity.matrix)
}

// o Quando aplicado em uma funcao publica o retorno eh um objeto do Tipo Any
fun sampleObject() = object {
    val description = "chrisluccas"
}

private fun createIdentityMatrix(m: Int, n: Int) = object {

    // codigo dentro de um object expression pode acessar variaveis de fora do seu escopo
    // isso eh denominado de closure
    val identity = Array(m) { _ -> Array(n) { 0 } }
    init {
        for (i in 0 until m) {
            identity[i][i] = 0
        }
    }
}

fun main() {
    sampleMatrixObject(4, 4)
    val mat = createIdentityMatrix(5, 5).identity
    println(mat)
}