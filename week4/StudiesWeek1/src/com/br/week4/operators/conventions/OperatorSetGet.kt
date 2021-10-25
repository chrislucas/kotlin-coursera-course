package com.br.week4.operators.conventions

data class Matrix2D<T>(var x: Int, val y: Int, val init: (Int, Int) -> Array<Array<T?>>) {
    val array: Array<Array<T?>> = init(x, y)
}

/*
    private inline operator fun <reified T> ((Int, Int) -> Array<Array<T?>>).getValue(
        mat2D: Matrix2D<T>,
        prop: KProperty<*>
    ): Array<Array<T?>> = genericArray2D(mat2D.x, mat2D.y)
*/

private inline fun <reified T> genericArray2D(x: Int, y: Int): Array<Array<T?>> = genericArray2D(x, y) { null }

private inline fun <reified T> genericArray2D(x: Int, y: Int, init: () -> T?): Array<Array<T?>> =
    Array(x) { Array<T?>(y) { init() } }

operator fun Matrix2D<Double>.set(i: Int, j: Int, value: Double) {
    this.array[i][j] = value
}

operator fun Matrix2D<Double>.get(i: Int, j: Int) = this.array[i][j]

private fun test1() {
    val matrix = Matrix2D<Double>(5, 10) { x, y -> genericArray2D(x, y) { -.5 } }
    matrix[0, 0] = 1.0
    println(matrix[0, 0])
    println(matrix[0, 1])
    println(matrix[0, 2])
}

private fun test2() {
    val matrix = Matrix2D<Double>(5, 10, ::genericArray2D)
    matrix[0, 0] = 1.0
    println(matrix[0, 0])
    println(matrix[0, 1])
    println(matrix[0, 2])
}

fun main() {
    test2()
}