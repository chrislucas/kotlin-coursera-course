package com.br.playground3.functions.group.groupingby.integers

import com.br.playground3.exts.log

/**
 * Veja tbm as anotacoes no arquivo {@see FunWithGroupingByIntegersV1}
 *
 * https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-grouping/
 * "Representa Uma estrutura/colecao de elementos que possui uma funcao keyOf(element), passando
 * um elemento para essa funcao obtemos um valor chave associado ao elemento.
 * Uma estrutura de dados "Grouping" serve como um passo intermediario para aplicar as operacoes de group-e-fold
 *
 *      - Primeiro agrupa uma colecao de elementos associando cada elemento a uma chave (key)
 *      - Depois aplica-se a operacao "fold" em cada grupo como alguma funcao de agregacao (aggregating)
 *
 * Um grouping eh criado a partir da extension function groupingBy. Algumas colecoes como
 * Iterable e CharSequence implementam essa extfun.
 *
 * A extension function espera uma colecao do Tipo T
 *      Iterable<T>.groupingBy()
 *
 * E gera uma estrutra do tipo Grouping<T, K>
 *      - onde T o valor da colecao que foi aplicado a operacao de agrupamento
 *      - K e uma chave vinculada ao valor T
 *          - essa chave eh obtida atraves de uma funcao lambda que groupingBy pede
 *              - keySelector: (T) -> K
 * A Estrutura Grouping<T,K> tem as funcoes
 *      - fun sourceIterator(): Iterator<T> = this@groupingBy.iterator()
 *          - um iterator que passa para navegar pelos elementos da colecao original
 *      e
 *      - fun keyOf(element: T): K = keySelector(element)
 *          - uma funcao que recebe um elemento da colecao original e transforma num outro valor
 *          que sera o valor chave vinculado ao elemento
 *
 *      - As extension function groupingBy cria um objeto anonimo do tipo Grouping<T, K>
 *        e a implementacao de keyOf chama a funcao lambda keySelector (element: T) -> K que nos implementamos
 *        ao chamar a extension function groupingBy. Lembrando que esse element: T vem da colecao Iterable<T>
 *
 *
 * https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/grouping-by.html
 * */

private fun groupingRangeOfIntegerUsingModularFunction(range: IntRange, mod: Int) {
    val group = range.groupingBy { it % mod }
    println("Range: $range")
    group.sourceIterator().forEach { value ->
        val key = group.keyOf(value)
        println("$key, $value")
    }
}

/**
 * https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-grouping/#extension-functions
 *
 * Essa funcao retorna um Map<K, R> onde K e a chave definida pela funcao lambda keySelector (element: T) -> K
 * passada para funcao groupingBy.
 *
 * a funcao aggregate chama a funcao aggregateTo que funciona seguindo os seguintes passos
 *   1) itera por cada elemento do Iterable de Grouping
 *   2) Recupera a chave associada ao ith elemento atraves da funcao keyOf definida pelo objeto anonimo
 *   de Grouping criada pela extension function groupingBy
 *      2.1) a funcao keyOf implementada no codigo fonte do kotlin chama a funcao lambda keySelector
 *   3) Essa chave eh utilizada no Map que sera retornado pela funcao aggregate
 *      3.1) o valor do mapa eh recuperado atraves da chave
 *      3.2) esse valor eh passado para funcao lambda que passamos para aggregate como um acumulador
 *      ele pode ser nulo uma vez que a chave utilizada para recuperar no mapa pode ainda nao existir
 *      3.3) no caso desse "acumulador" ser nulo, geralmente temos que implementar uma tratativa para retornar
 *      um valor inicial para vincular a chave, assim na proxima vez que essa chave for utilizada o valor passado
 *      para funcao lambda sera um valor com acumulo das operacoes anteriores
 *   4) aplicado a operacao (funcao lambda que passamos como argumento para funcao aggregate) o retorno
 *   da operacao e adicionado ao mapa com a chave sendo o retorno da funcao keyOf citada no passo 2.1
 *
 * */
private fun groupingRangeOfIntegerUsingModularFunctionAndAggregatingGroups(range: IntRange, mod: Int) {
    val map = range.groupingBy { it % mod }
        .aggregate { _, accumulator: MutableList<Int>?, element, _ ->
            if (accumulator != null) {
                accumulator.add(element)
                accumulator
            } else {
                val acc = mutableListOf(element)
                acc
            }
        }
    map.log()
}

/**
 * https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/fold.html
 * A partir de um Grouping<T, K> que conseguimos criar atraves da ext function
 * groupingBy(keySelector: (T) -> K) podemos aplicar a operacao fold que aplica
 * uma operacao em cada elemento de cada grupo definido em Grouping. O retorno
 * dessa operacao define o valor do mapa Map<K, V> que a funcao fold retorna
 * a chave desse mapa eh o valor K de grouping que eh obtido pela funcao keyOf
 * que basicamente chama a funcao keySelector na implementacao padrao da extension
 * function que encontramos no codigo fonte do kotlin
 *
 * Obs: a funcao fold chama a funcao aggregate
 *      1) Uma das implementacoes de fold tem a assinatura
 *         Clazz<T>.fold(initValue: (K, T) -> R, op: (K, acc: R, T) -> R)
 *         (*Troque Clazz por Iterable, CharSequence entre outros)
 *         ou seja temos uma assinatura que permite criar uma lambda que define qual o valor inicial para o
 *         acumulador. cada chave do mapa final tera uma valor R que será um valor construido atraves da
 *         agregacao de operacoes
 *     2) uma segunda implementacao tem a seguinte assinatura
 *          Clazz<T>.fold(initialValue: R, op(acc: R, element: T) ->)
 *
 *
 * */
private fun groupingRangeOfIntegerUsingModularFunctionAndFolding(range: IntRange, mod: Int) {

    /**
     * Local function
     * fun setInitialValueOfOperation(_: Int, _: Int): MutableList<Int> = mutableListOf()
     * ou
     * */
    val setInitialValueOfOperation: (Int, Int) -> MutableList<Int> = { _, _ -> mutableListOf() }

    val operation: (Int, MutableList<Int>, Int) -> MutableList<Int> = { _, acc, element ->
        acc.add(element)
        acc
    }
    /**
     * Assinatura da funcao fold usada abaixo
     *
            Grouping<T, K>.fold(initialValueSelector: (key: K, element: T) -> R,
                operation: (key: K, accumulator: R, element: T) -> R)
                : Map<K, R>

            initialValueSelector: (key: K, element: T) -> R
            funcao lambda que retorna um Tipo que sera utilizado como acumulador
            o interessante aqui eh que como eh uma funcao lambda, sempre que
            ela for chamada um objeto novo será criado. isso eh um comportamento importante
            dependendo do que se quer fazer como por exemplo agrupar numeros num mapa
            cuja chave Y eh um valor dado por uma funcao F(x): Y e o valor eh uma lista de numeros
            que quando passados para funca F retorna Y sempre

            Segundo a documentacao:
            "initialValueSelector a function that provides an initial value of accumulator for each group."
     * */
    val map = range.groupingBy { it % mod }.fold(setInitialValueOfOperation, operation)
    map.log()
}

private fun anotherWayUsingFold(range: IntRange, mod: Int) {

    val operation: (MutableList<Int>, Int) -> MutableList<Int> = { acc, element ->
        acc.add(element)
        acc
    }
    /**
        Grouping<T, K>.fold(
                initialValue: R,
                operation: (accumulator: R, element: T) -> R
            )
         O interessante dessa assinatura eh o valor initialValue. Diferente a funcao
         fold que recebe como argumento uma funcao lambda para definir o valor inicial do acumulador
         essa funcao espera um objeto do tipo R. Esse objeto sera sempre utilizado como acumulador
         assim o resultado final eh um mapa Map<K, V> onde todas as chaves compartilham o mesmo acumulador
         Dependendo da estrutura de dados que queremos contruir usar essa funcao nao eh a indicada.
         Segundo a doc "An initial value of accumulator is the same initialValue for each group"
     * */
    val map = range.groupingBy { it % mod }.fold(mutableListOf(), operation)
    map.log()
}

fun main() {
    //groupingRangeOfIntegerUsingModularFunction(1..1000, 25)
    //groupingRangeOfIntegerUsingModularFunctionAndAggregatingGroups(1..1000, 25)
    //groupingRangeOfIntegerUsingModularFunctionAndFolding(1..100, 25)
    anotherWayUsingFold(1..100, 25)
}