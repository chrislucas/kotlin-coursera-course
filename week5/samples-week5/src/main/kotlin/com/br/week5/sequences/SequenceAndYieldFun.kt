package com.br.week5.sequences

private fun checkYield() {
    val seq = sequence {
        var x = 0
        while (true) {
            /*
                Yield is a regular library function in kotlin.
                Yield como muitas outras funcoes sao implementadas
                em bibliotecas da linguagem e nao como feature/builtin da linguagem

                Essa funcao permite retornar/produzir valores/elementos de forma
                personalizavel
             */
            yield(x++)
        }
    }
    println(seq.take(100).toList())
}


private fun challengeHowManyPrintsAndYields() = sequence {
    println("Yield 1st element")
    yield(1)
    println("Yield a range")
    yieldAll(1..3)
    println("Yield a list")
    yieldAll(listOf(1, 2, 3, 4, 5))
}

private fun checkChallengeHowManyPrintsAndYields() {
    val seq = challengeHowManyPrintsAndYields()
    val s = seq.map { it * it }
        //.filter { it > 10 }
        /*
             n < 5 nao executa os 3 printlns pq e o numero minimo
             de elementos que yield precisa produzir.
             O primeiro yield produz 1 elemento, o segundo 3, total de 4 elementos
             produzido e o quinto elemento está na lista que eh produizada no terceiro yield
         */
        .take(5)
        .toList()

    println(s)
    println("****************************************************")

    val t = seq.map { it * it }
        .first()
    println(t)
    println("****************************************************")

    val u = seq
        .map { it * it }
            /*
                se it > 24 entao a funcao executaria os 3 println
                pq ao aplicar a operacao dentro do map it * it somente
                o numero 5 da ultima lista teria o valor acima de 24.

                Se filter deixar a sequencia vazia, ma exception eh lancada
                Exception in thread "main" java.util.NoSuchElementException: Sequence is empty.

                se it > 8 por exemplo, a funcao para no segundo println pois o segundo yield retorna uma
                lista com o numero 3 que quando passa pela funcao map torna-se 9.
             */
        .filter { it > 8 }
        .first()

    println(u)
    println("****************************************************")

}

fun main() {
    //checkYield()
    checkChallengeHowManyPrintsAndYields()
}