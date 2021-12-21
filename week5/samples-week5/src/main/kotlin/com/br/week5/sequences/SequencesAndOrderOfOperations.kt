package com.br.week5.sequences

/*
    https://www.coursera.org/learn/kotlin-for-java-developers/lecture/HwXl7/more-about-sequences

    Collections Vs Sequences

    Collection
        - Operation in collections are inlined
        - Intermediate collections are creates on chained call (objetos sao criados a cada chamada)
        - This causes performance overhead for chained calls
     Sequences
        - sequences resolve o problema de criar objetos intermediarios ao armazenar as operacoes que
        precisam ser executadas em cada elemento
        - porem as funcoes (map, filter e etc) que recebem lambda nao sao inline
        (classes anonimas sao criadas para execucao da funcoes lambdas)
 */


private fun checkOpOrder() {
    val seq = (1..100).asSequence()

    /*
        A ordem das operacoes importa quando operamos sobre sequencias.
        Exemplo A:
        1) aplicasse a operacao filter, depois map e por fim find
            - a operacao filter eh aplicada a todos os elementos ate que a funcao lambda passada para find retorne true
            ou todos os elementos sejam percorridos
            - a operacao map so eh aplicada para o elemento que retornar true na funcao filter

        Exemplo B:
        1) aplicasse a operaaco map, depois filter e por fim find
            - a operacao map eh aplicada a todos os elementos ate que a funcao lambda passada para find retorne true
            ou todos os elementos sejam percorridos
            - a operacao filter eh aplicada a todos os elementos que a funcao map retornar até que a funcao find
            retorne


         Veja que o exemplo b executa mais operacoes, assim vemos que a ordem importa

     */

    val aResult = seq.filter { it and 1 == 1 }
        .map {
            val p = Pair(it, it * it)
            print(p)
            p
        }
        .find { it.second > 100 }

    println("\nResultado filter e map $aResult\n")


    val bResult = seq
        .map {
            val p = Pair(it, it * it)
            print(p)
            p
        }
        .filter { it.first and 1 == 1 }
        .find { it.second > 100 }

    println("\nResultado map e filter $bResult")

}

private fun checkOrderMatter() {

    fun p(i: Int): Int {
        print("m($i) ")
        return i
    }

    fun q(i: Int) : Boolean {
        print("f($i) ")
        return i and 1  == 0
    }

    val seq = (1 .. 10).asSequence()

    seq.map (::p).filter(::q).toList()
    println()
    seq.filter (::q).map(::p).toList()
}


fun main() {
    checkOrderMatter()
}