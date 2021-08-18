package com.br.week4.oop.clazz.modifiers.hierarchy.restriction

/**
 * Sealed modifier
 *
 * Restricts class hierarchy:
 *
 * all subclasses must be located in the same file
 *
 * Sealed classes possuem o construtor privado o que impede
 * de criarmos subclasses ou instancia-las num arquivo Java
 * */

sealed class Expression

class WrapInteger(val value: Int) : Expression()

class SumInteger(val lf: Expression, val ri: Expression) : Expression()

class SubtractInteger(val lf: Expression, val ri: Expression) : Expression()

/**
 * Usando sealed class para definir uma hierarquia de classes (super classes e subclasses)
 * temos a vantagem de no momento que usarmos uma expressao com objetivo de comparar um tipo
 * como É UM como a expressao when(variavel) em kotlin, a linguagem implementa uma restricao
 * dos possiveis tipos que a variavel avaliada pode tomar
 * */

private fun evalExpr(expr: Expression): Int = when (expr) {
    /**
     * Veja que diferente de uma expressao construida para avaliar expr: Expression
     * como uma subclasse de uma interface mas agora sendo uma subclasse de uma selead
     * class, nao precisamos utilziar uma expressao else pois os subtipos sao resstringidos
     * somente aos ja definidos WrapInteger. SumInteger e SubtractInteger
     * */
    is WrapInteger -> expr.value
    is SumInteger -> evalExpr(expr.lf) + evalExpr(expr.ri)
    is SubtractInteger -> evalExpr(expr.lf) - evalExpr(expr.ri)
}


private fun test1() {
    val a = SumInteger(WrapInteger(1), WrapInteger(1))
    val b = SumInteger(WrapInteger(1), WrapInteger(1))
    val c = SumInteger(a, b)

    println(evalExpr(c))

    val d = SumInteger(c, WrapInteger(2))
    val e = SumInteger(d, WrapInteger(evalExpr(d)))
    println(evalExpr(d))

    println(evalExpr(e))
}


private fun test2() {
    val a = SumInteger(WrapInteger(1), WrapInteger(1))
    val b = SumInteger(a, WrapInteger(evalExpr(a)))
    val c = SumInteger(b, WrapInteger(evalExpr(b)))
    println(evalExpr(c))

    val d = SubtractInteger(c, WrapInteger(-1))
    println(evalExpr(d))
}

private fun test3() {
    val a = SumInteger(
        WrapInteger(1),
        SumInteger(WrapInteger(1), SumInteger(WrapInteger(1), WrapInteger(1)))
    )
    println(evalExpr(a))

    val b = SumInteger(a, SumInteger(WrapInteger(1), WrapInteger(1)))
    val c = SumInteger(b, SumInteger(WrapInteger(1), WrapInteger(1)))
    val e = SumInteger(c, SumInteger(WrapInteger(1), WrapInteger(1)))
    println(evalExpr(e))
}

fun main() {
    test3()
}

