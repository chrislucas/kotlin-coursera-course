package com.br.week4.operators.conventions.iteratoris

import com.br.week4.operators.conventions.Matrix
import com.br.week4.operators.conventions.create
import com.br.week4.operators.conventions.string


private fun <T> iterate(array: Array<T>) {
    for (i in array) {
        println(i)
    }
}

private fun <T> iterate(matrix: Matrix<T>) {
    for (array in matrix) {
        println(array.string)
    }
}

private fun testMatrixIt() {
    val matrix = create<Double>(3, 3) { 0.0 }
    iterate(matrix)
}

private fun testItExtMatrix() {
    val it = create<Double>(3, 3) { 0.0 }.it()
    while (it.hasNext()) {
        println(it.next().string)
    }
}

fun main() {
    testItExtMatrix()
}