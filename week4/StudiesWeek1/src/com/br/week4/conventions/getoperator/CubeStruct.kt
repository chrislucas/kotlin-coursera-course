package com.br.week4.conventions.getoperator


typealias Cube<T> = Array<Array<Array<T>>>

inline fun <reified T> create(x: Int, y: Int, z: Int, fn: () -> T?): Cube<T?> =
    Array(x) { Array(y) { Array(z) { fn() } } }

operator fun <T> Cube<T>.get(x: Int, y: Int, z: Int) = this[x][y][z]

operator fun <T> Cube<T>.set(x: Int, y: Int, z: Int, value: T) {
    this[x][y][z] = value
}


class CubeStruct<T>(x: Int, y: Int, z: Int, private val cube: Cube<T?>) {

    data class Dimension(val x: Int, val y: Int, val z: Int)

    private val dimension: Dimension = Dimension(x, y, z)


    /**
     * Cannot use 'T' as reified type parameter. Use a class instead.
     * https://stackoverflow.com/questions/46869353/kotlin-generics-arrayt-results-in-cannot-use-t-as-a-reified-type-parameter-u
     * */
    //val cube : Cube<Any?> = Array(x) {Array(y) { Array(z) { null } } }

    operator fun <T> set(x: Int, y: Int, z: Int, value: T?) {
        //cube[x][y][z] = value
    }

    operator fun get(x: Int, y: Int, z: Int) = cube[x][y][z]

    override fun toString(): String = "Cube $dimension"


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
    val cubeStruct = CubeStruct<Double?>(1, 2, 3, create(1, 2, 3) { null })

    println(cubeStruct)

    cubeStruct[0, 0, 0] = 1.0
}

fun main() {
    checkGetOpCubeStruct()
}