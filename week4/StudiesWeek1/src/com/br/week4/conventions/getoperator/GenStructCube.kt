package com.br.week4.conventions.getoperator

typealias Cube<T> = Array<Array<Array<T>>>

inline fun <reified T> create(x: Int, y: Int, z: Int, fn: () -> T?): Cube<T?> =
    Array(x) { Array(y) { Array(z) { fn() } } }

operator fun <T> Cube<T>.get(x: Int, y: Int, z: Int) = this[x][y][z]

operator fun <T> Cube<T>.set(x: Int, y: Int, z: Int, value: T) {
    this[x][y][z] = value
}

class GenStructCube<T>(x: Int, y: Int, z: Int, create: (Int, Int, Int) -> Cube<T?>) {

    data class Dimension(val x: Int, val y: Int, val z: Int)

    private val dimension: Dimension = Dimension(x, y, z)

    val mCube: Cube<T?> = create(x, y, z)

    /**
     * Cannot use 'T' as reified type parameter. Use a class instead.
     * https://stackoverflow.com/questions/46869353/kotlin-generics-arrayt-results-in-cannot-use-t-as-a-reified-type-parameter-u
     * */

    operator fun get(x: Int, y: Int, z: Int) = mCube[x][y][z]

    override fun toString(): String = "Cube $dimension"
}

inline operator fun <reified T> GenStructCube<T>.set(x: Int, y: Int, z: Int, value: T?) {
    mCube[x][y][z] = value
}

private fun checkGetCubeValue() {
    val cubeInt: Cube<Int?> = create(3, 3, 3) { null }
    cubeInt[0, 0, 0] = 1
    println(cubeInt[0, 0, 0])

    val cubeDouble: Cube<Double> = Array(3) { Array(1) { Array(3) { 0.0 } } }
    cubeDouble[1, 0, 1] = 1.0

    println(cubeDouble[1, 0, 1])
}

private fun checkGetOpCubeStruct() {
    val cube = GenStructCube(1, 2, 3) { x, y, z -> create(x, y, z) { 0.0 } }
    //val cube = GenStructCube<Double?>(1, 2, 3) { x, y, z -> create(x, y, z) { null } }

    println(cube)
    println(cube[0, 0, 0])

    cube[0, 0, 0] = 1.0
    println(cube[0, 0, 0])

    cube[0, 1, 0] = 2.0
    println(cube[0, 1, 0])
}


fun main() {
    checkGetOpCubeStruct()
}