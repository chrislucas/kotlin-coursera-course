package sample.kotlin

import java.lang.StringBuilder
import kotlin.math.sqrt

// FONTES
// https://kotlinlang.org/docs/reference/lambdas.html#instantiating-a-function-type
// AULA sobre functions do curso Kotlin for Java developers no Coursera

/**
 * Function Types
 *
 * Funcoes em kotlin podem ser adicionadas a variaveis e/ou estruturas de dados,
 * passadas como argumentos em outras funcoes e retornadas por outras funcoes.
 *
 * Kotlin como uma linguagem de tipagem estatica usa de uma familia de tipos
 * de funcoes para representa-las e prover um conjunto especializado de construtos
 * tal como 'lambda functions
 *
 * High order function: Uma funcao que recebe uma outra como argumento ou retorna uma funcao
 *
 * function type:
 * Notacao (T, R) -> S
 * um tipo funcao que recebe 2 args T, R e retorna um Tipo S
 *
 *  - Todas as function types tem seus argumentos de tipos listados entre parenteses (A, B, C) -> R (ou Unit)
 *
 *  - Temos o tipo Function Linera With Receiver
 *      - com a seguinte notacao AnyType.(A, C ...) -> R
 *
 * Criando instancias de function type
 *
 * Podemos usar lambdas para criar instancias de function type atraves da seguinte sintaze
 *
 * lambda expression: com argumentos { a, b, c -> DO SOMETHIONG }
 *
 * exemplo da notacao
 * Com o function Type
 * val fn: (Atype, Btype, Ctype) -> Unit {a, b, c ->  DO SOMETHING}
 * ou sem o function type
 * val binOp = {a: Int, b: Int, op:(Int, Int) -> Int -> op(a, b) }
 *
 * Note o uso do ':' e '=' respectivamente com o function type e sem ele
 *
 * Sobre se eh possivel criar uma anonymous function e lambdas generica
 * // https://stackoverflow.com/questions/44880442/how-to-write-lambdas-with-generics-in-kotlin
 * nao
 *
 * Formas de instanciar uma Function Type (Resumo)
 *  - code block
 *      - lambda expression
 *          val p: { a, b, c ... -> operacao vai aqui }
 *           (a: Int, b: Int) -> Unit = {a, b -> println("$a, $b")} // da pra omitir o nome dos parametros
 *      - anonymous function: fun(data: T) -> R { op here }
 *  - Atraves do chamado callable reference
 *      - ::nomedafuncao
 *          Exemplo: ::println, ::isEmpty
 *          So funciona com funcoes, se tentar com variaveis
 *
 *  - Atraves de instancias de classes que implementam function type como se fossem interfaces
 *      class IsOdd: (Int) -> Boolean  {
 *          override operator fun invoke(x: Int): Boolean = DO SOMEHTING
 *      }
 * */

fun sampleLambdaExpression() {
    val op: (a: Int, b: Int) -> Unit = {a, b -> println("$a, $b")}
}

val binaryOp: (Int, Int, (Int, Int) -> Int) -> Int = { x, y, op ->
    op(x, y)
}


typealias Predicate<Value> = (Value) -> Boolean

class IsOdd : Predicate<Int> {
    override fun invoke(p: Int): Boolean = p and 1 == 1
}

// https://kotlinlang.org/docs/reference/lambdas.html#instantiating-a-function-type
//
class BinaryMapping<P, Q> : (P, P, (P, P) -> Q) -> Q {
    override fun invoke(p1: P, p2: P, op: (P, P) -> Q): Q =
        op(p1, p2)
}

fun <T> Collection<T>.log(fmt: String): String {
    val builder = StringBuilder()
    this.forEach { builder.append(String.format(fmt, it)) }
    return builder.toString()
}

fun Int.isOdd() = this and 1 == 1

fun sampleFilter() {
    val range = (1..1000000).toList()
    //println(range.filter { IsOdd().invoke(it) })
    // callable reference
    println(range.filter { it::isOdd.invoke() }.count())

    println(range.last(::anotherIsPrime))

    //range.forEach { println("$it: ${it::isOdd}") }
    //val p = 1::isOdd
    //println(p)
}
/*
*                  n     Primes <= n
*  ---------------------------------
*                 10               4
*                100              25
*              1,000             168
*             10,000           1,229
*            100,000           9,592
*          1,000,000          78,498
*         10,000,000         664,579
*        100,000,000       5,761,455
*      1,000,000,000      50,847,534
*/
val primer = fun(p: Int): Boolean {
    return if (p <= 1 || p > 2 && p % 2 == 0 || p > 3 && p % 3 == 0) {
        false
    } else {
        val limit = sqrt(p * 1.0).toInt()
        var rs = true
        for (i in 5..limit) {
            if (p % i == 0) {
                rs = false
                break
            }
        }
        rs
    }
}

fun anotherIsPrime(i: Int) = primer(i)

fun sampleFunctionWithReceiverInsideFun(range: IntRange) {
    // function type: Function with Receiver
    // testando a possibilidade de usar essa function type dentro de uma funcao
    val isPrime: Int.() -> Boolean = {
        // nao eh necessario a declaracao 'return' numa Function With Receuver
        primer(this)
    }
    println(range.toList().filter { it.isPrime() }.count())
    // Unsupported [References to variables aren't supported yet]
    //println(range.toList().filter(::isPrime))
}

fun sampleCallableReference(range: IntRange) {
    /**
     *  https://try.kotlinlang.org/#/Examples/Callable%20references/Reference%20to%20a%20function/Reference%20to%20a%20function.kt
     * */
    range.toList().run {
        // aqui eu posso usar uma callabble reference, pq a existe uma funcao de fato
        // lcaol function
        val primes = this.filter(::anotherIsPrime).count()
        println("Primes: $primes")
        // extension function
        val odds = this.filter(Int::isOdd).count()
        println("Odds: $odds")

        println("Last prime $range: ${this.last(::anotherIsPrime)}")

        println("Last odd $range: ${this.last(Int::isOdd)}")
    }
}

fun sampleAnonymousFunInsideFun(range: IntRange) {
    // pequeno teste
    val isPrime = fun Int.(): Boolean {
        // na sintaxe de funcao anonima preciso usar a declaracao 'return'
        return primer(this)
    }
    println(range.toList().filter { it.isPrime() }.count())

    // referencias a variaveis nao sao suportadas
    //println(range.toList().filter(::isPrime))
}

fun sampleFunTypeReference() {
    //val log = (1 .. 100).toList()::log("%d")
    // lambda expression sintaxex
    val lambdaSum = { a: Int, b: Int -> a + b }
    println(lambdaSum.invoke(10, 20))
    // outra forma de escrever uma lambda expression, usando Function Type (A, B, ...) -> C
    val binaryIntOp: (Int, Int, (Int, Int) -> Int) -> Int = { p, q, op -> op(p, q) }
}

// usando anonymous function
// val isOdd = fun(p: Int): Boolean = p and 1 == 1

// usando high order function para compor funcoes
// usando anonimous function
fun composite(p: Int): (Int) -> Unit = fun(q: Int) = println(p xor q)

fun main() {
    //sampleFilter()
    //composite(127)(2)

    val range = 1 .. 1000000
    //sampleFunctionWithReceiverInsideFun(range)
    //sampleAnonymousFunInsideFun(range)

    sampleCallableReference(range)
}