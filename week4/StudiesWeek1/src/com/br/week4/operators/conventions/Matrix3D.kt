package com.br.week4.operators.conventions

typealias  Matrix3D<T> = Array<Array<Array<T?>>>

operator fun <T> Matrix3D<T>.set(i: Int, j: Int, k: Int, value: T) {
    this[i][j][k] = value
}

operator fun <T> Matrix3D<T>.get(i: Int, j: Int, k: Int) = this[i][j][k]

private inline fun <reified T> create(x: Int, y: Int, z: Int, init: () -> T?): Matrix3D<T?> =
    Array(x) { Array(y) { Array(z) { init() } } }


fun main() {
    val m3D = create(10, 10, 10) { 1 }
    println(m3D[0][1][0])
}