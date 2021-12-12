package com.br.samples.init

open class A(open val value: String) {
    init {
        // Se uma subclasse de A for criada o codigo abaixo vai imprimir
        // null pq  primeiro sera executado o construtor de A e por consequencia
        // esse bloco init que vai ser escrito dentro dele. Ao tentar acessar o campo
        // value, o que realmente esta sendo feito e acessar o metodo getter do campo
        // na subclasse, mas como o construtor dela ainda nao foi executado, esse valor eh nulo

        /**
         * Accessing non-final property value in constructor
         * Um aviso do compilador por estarmos acessando uma variavel que nao eh FINAL, ou seja
         * seu valor pode nao ter sido definido ainda, dentro do construtor
         * */
        println(value)
        // exemplo  de como pode ocorrer um NPE mesmo trabalhando somente com kotlin
        // Se tentarmos acessar algum atributo de value,
        if (value != null && value.length > 2) {
            println( this.value.substring(0, 1))
        }
    }
}

class B(override val value: String) : A(value)

fun main() {
    val b = B("a")
    println("Valor da propriedade 'value da classe B ${b.value}")
}