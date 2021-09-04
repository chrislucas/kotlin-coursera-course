package com.br.week4.operators.conventions


typealias  Matrix<T> = Array<Array<T?>>

operator fun <T> Matrix<T>.set(i: Int, j: Int, value: T) { this[i][j] = value }

operator fun <T> Matrix<T>.get(i: Int, j: Int) = this[i][j]

private inline fun <reified T> create(x: Int, y: Int): Matrix<T?> = create(x, y) { null }

private inline fun <reified T> create(x: Int, y: Int, init: () -> T?): Matrix<T?> = Array(x) { Array<T?>(y) { init() } }

fun main() {
    val matrix = create<Double>(3, 3) { 0.0 }

    matrix[1, 2] = 12.0

    println(matrix[0, 0])
    println(matrix[1, 2])
}