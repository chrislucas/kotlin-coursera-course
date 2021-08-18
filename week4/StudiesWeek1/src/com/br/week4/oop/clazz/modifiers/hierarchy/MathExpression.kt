package com.br.week4.oop.clazz.modifiers.hierarchy

import java.lang.IllegalArgumentException

interface Expression


class WrapInt(val value:Int): Expression

class SumInt(val left: Expression, val right: Expression): Expression


fun eval(expression: Expression) : Int? =
    when(expression) {
        is WrapInt -> expression.value
        is SumInt -> {
            val left = eval(expression.left)
            val right = eval(expression.right)
            if (left != null && right != null) {
                left + right
            } else {
                null
            }
        }
        else -> null
    }

fun eval2(expression: Expression) : Int =
    when(expression) {
        is WrapInt -> expression.value
        is SumInt -> eval2(expression.left) + eval2(expression.right)
        else -> throw IllegalArgumentException("")
    }

private fun test1() {
    val value = eval(SumInt(WrapInt(1), WrapInt(2)))
    println(value)
}

private fun test2() {
    val value2 = eval(SumInt(WrapInt(1), WrapInt(2)))?.run {
        eval( SumInt(WrapInt(3), WrapInt(this)) )?.run {
            eval(SumInt(WrapInt(6), WrapInt(this)))
        }
    }
    println(value2 == 12)
}

private fun test3() {
    val value = eval2(SumInt(WrapInt(1), WrapInt(-1))).run {
        eval2(SumInt(WrapInt(3), WrapInt(this)))
    }

    println(value)
}

fun main() {
    test3()
}