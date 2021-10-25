package com.br.week4.conventions.structcube

typealias Cube<T> = Array<Array<Array<T>>>

inline fun <reified T> create(x: Int, y: Int, z: Int, fn: () -> T?): Cube<T?> =
    Array(x) { Array(y) { Array(z) { fn() } } }

operator fun <T> Cube<T>.get(x: Int, y: Int, z: Int) = this[x][y][z]

operator fun <T> Cube<T>.set(x: Int, y: Int, z: Int, value: T) {
    this[x][y][z] = value
}

data class Piece(val tag: String)

class GenStructCube<T>(x: Int, y: Int, z: Int, create: (Int, Int, Int) -> Cube<T?>) {

    data class Dimension(val x: Int, val y: Int, val z: Int)

    private val dimension: Dimension = Dimension(x, y, z)

    val mCube = create(x, y, z)

    operator fun get(x: Int, y: Int, z: Int) = mCube[x][y][z]

    override fun toString(): String = "Cube $dimension"
}

inline operator fun <reified T> GenStructCube<T>.set(x: Int, y: Int, z: Int, value: T?) {
    mCube[x][y][z] = value
}

private fun createCube() {
    val cube = GenStructCube<Piece?>(3,3,3) { x,y,z -> create(x, y, z) { null } }
    cube[0, 0, 0] = Piece("#1")
    println(cube)
}

fun main() {
    createCube()
}

