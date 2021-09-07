package com.br.week4.operators.conventions.iteratoris

import com.br.week4.operators.conventions.Matrix

fun <T> Matrix<T>.it(): Iterator<Array<T?>> {
    val i = this.size

    return object : Iterator<Array<T?>> {

        var counter = 0

        override fun hasNext(): Boolean = counter < i

        override fun next(): Array<T?> = get(counter++)
    }
}