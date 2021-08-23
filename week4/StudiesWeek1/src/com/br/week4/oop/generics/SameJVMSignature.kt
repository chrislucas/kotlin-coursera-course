package com.br.week4.oop.generics

/**
 * Platform declaration clash: The following declarations have the same JVM signature (foo(Ljava/util/List;)Z):
 *
 * Tipos genericos sao apagados pelo compilador
 * https://docs.oracle.com/javase/tutorial/java/generics/erasure.html
 * https://stackify.com/jvm-generics-type-erasure/#
 * https://www.baeldung.com/java-type-erasure
 *
 * Generics were introduced to the Java language to provide tighter type checks at compile time and to support generic programming. To implement generics, the Java compiler applies type erasure to:

    - Replace all type parameters in generic types with their bounds or Object if the type parameters are unbounded. The produced bytecode, therefore, contains only ordinary classes, interfaces, and methods.
    Insert type casts if necessary to preserve type safety.

    - Generate bridge methods to preserve polymorphism in extended generic types.

    - Type erasure ensures that no new classes are created for parameterized types; consequently, generics incur no runtime overhead.
 *
 * entao os dois metodos abaixo nao podem coexistir pq terao a mesma assinatura
 * */
fun List<Int>.average(): Int = 0

/**
 * https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.jvm/-jvm-name/
 * https://www.baeldung.com/kotlin/jvm-annotations
 *
 * Quando aplicado antes do linha que define o package modifica o nome do arquivo
 *
 * Mas pode mudar o nome da funcao no nivel de bytecode. A nivel da JVM temos
 * 2 funcoes com assinaturas diferentes
 *
 * Em kotlin podemos chamar as duas funcoes pelo nome definido nesse arquivo, e o compilador kotlin
 * ira inferir qual funcao usar atraves do tipo de objetivo que a chama.
 *
 * */
@JvmName("averageDouble")
fun List<Double>.average(): Double = 0.0