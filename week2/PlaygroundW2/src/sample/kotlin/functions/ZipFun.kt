package sample.kotlin.functions

/**
 * Explorando a funcao zip
 *
 * Ela eh uma extension function de colecoes e por padrao ela
 * agrupa o conteudo de duas colecoes P e Q numa unica colecao S de pares
 *
 * S tem o tamanho da menor colecao, P ou Q.
 * Os pares sao formados a partir da i-esima posicao de P e Q ate min(P.size, Q.size)
 *
 * Veja o codigo fonte
 *
 * https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/zip.html
 *
 * https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.text/zip.html
 * */

infix fun <T, C: Iterable<T>> C.pairalize(that: C) =
    this.zip(that)

infix fun CharSequence.pairalize(that: CharSequence) =
    this.zip(that)

fun main() {
    println("chris" pairalize "lucas")
    println(listOf(1,2,3) pairalize listOf(4,5))
}