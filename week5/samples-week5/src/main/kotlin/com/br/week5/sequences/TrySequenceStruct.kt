package com.br.week5.sequences

/*
    https://www.coursera.org/learn/kotlin-for-java-developers/lecture/HwXl7/more-about-sequences

    Collection Vs Sequence

    operacoes de transformacao aplicadas em Colecoes tem o comportamento denominnado Eager Evaluation ou
    Horizontal Evaluation, isso significa que todos os elementos da colecao serao transformados pela
    funcao de operacao, do primeiro ao ultimo, por isso leva-se o nome de Horizontal Eval.

    Em conparacao, na estrutura de Sequence o comportamento das operacoes leva o nome de Lazy Evaluation ou
    Vertical Evaluation, isso signidica que: Primeiro, as operacoes sao aplicadas somente quando seu resultado
    eh solicitado (veja exemplo na funcao comparisonCollectionsAndSequencies) e segundo quando a funcao que devolve
    o resultado termina, funcao que aplica a transformacao em cada elemento encerra

    Exemplo de Vertical Evaluation
    seja S uma sequencia de 1 a 100, F uma funcao de transformacao que transforma o i-esimo numero no seu quadrado
    e R uma funcao que procura o i-esimo numero transformado maior que 10

    1) a funcao F so comeca a transformar os n elementos de S quando a funcao R de busca eh chamada
    2) a funcao F encerra assim que a funcao R devolve o resultado
        - Dessa forma vemos uma vertilizacao da operacao, o i-esimo elemento eh transformado pela funcao F
          a funcao R avalia e verifica se deve retornar e encerra, se sim retorna e a funcao F tbm encerra
          do contrario funcao F segue para o i-esimo + 1 elemento até que R retorne

 */


private fun comparisonCollectionsAndSequencies() {
    val numbers = (1..10).toList()
    val sequence = numbers.asSequence()

    /*
        Eager evaluation
        quando chamamos uma nova operacao ela cria uma nova colecao de dados. Podemos
        verificar isso ao jogar o resultado da operacao para uma variavel e veremos as colecoes
        intermediarias sendo criadas.

        Eager evaluation sempre executa a(s) operacoes para todos os elementos de uma colecao
     */
    val p = numbers
        .map { Pair(it, it * it) }  // cria-se uma nova collection (1^2, 2ˆ2, 3^2 ... n^2)
        .find { it.second > 60 }     // essa eh uma operacao que nao cria uma nova colecao por motivos obvios
    println(p)
    /*
        Lazy evaluation
        Usando sequence e Lazy evaluation nenhuma operacao eh realizada até que o resultado
        dela seja requisitado.

        No exemplo abaixo a operacao map so eh executada quando o resultado dela eh solicitado, no caso
        pela funcao find. Além disso, a operacao map para sua execucao assim que find retorna o resultado
        desejado.

        O comportamento tardio/lazy resumisse a realizar uma operacao sobre a sequencia somente quando o
        resulado dessa operacao for soolicirado
     */
    val q = sequence
        .map { Pair(it, it * it) }  // aqui nao eh criada uma nova colecao
        .find { it.second > 60 }
    println(q)

}

private fun compareCollectionAndSequenceEval() {
    fun p(i: Int): Int {
        print("p($i)")
        return i
    }

    fun q(i: Int): Boolean {
        print("q($i)")
        return i and 1 == 0
    }

    val list = (1 .. 10).toList()
    println("Print operations in collection")
    /*
        As operacoes na colecao sao feitas na ordem que sao chamadas.
        Primeiro a operacao map em todos os elementos depois filter
     */
    list.map(::p).filter(::q)
    println("\nPrint operation in sequence transformed in collection\n****************************************************\n")
    /*
        Aqui como a colecao foi transformada em sequencia entao as operacoes
        alternam: aplica-se map e filter para o primeiro elemeto, depois novamente
        map e filter para o segundo e assim por diante até o n-esimo elemento.
        Como a sequencia e transformada novamente em lista/colecao entao as funcoes
        de transformacao sao executadas no modelo Eager Evaluation
     */
    list.asSequence().map(::p).filter(::q).toList()
    println("\nPrint op in sequence after terminal op is called\n****************************************************")
    /*
        aqui as funcao p e q nao serao executadas até que os dados
        da estrutura sequence sejam requisitados, ou o que chamam de terminal operation

        exemplo

        sequence. map {} // intermediate op
                . filter {} // intermediate op
                . toList() // terminal op
                or
                .find {} // terminal operation
        Operacoes intermediarias retornam outra estrutura sequence/collection operacoes terminais retornar valores
        unicos, boolean, int ou qualquer outro tipo de dado.
     */
    val r = list.asSequence().map(::p).filter(::q)

    val it = r.iterator()

    // Aqui as funcoes p e q sao executadas
    it.forEach { println(it) }

    // aqui tambem
    //println(r.find { it > 6 })

}


fun main() {
    compareCollectionAndSequenceEval()
}