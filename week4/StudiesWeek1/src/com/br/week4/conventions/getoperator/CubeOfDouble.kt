package com.br.week4.conventions.getoperator

class CubeOfDouble(x: Int, y: Int, z: Int) {
    data class Dimension(val x: Int, val y: Int, val z: Int)

    private val dimension: Dimension = Dimension(x, y, z)

    private val cube: Cube<Double?> = Array(x) { Array(y) { Array(z) { null } } }

    operator fun set(x: Int, y: Int, z: Int, value: Double?) {
        cube[x][y][z] = value
    }

    operator fun get(x: Int, y: Int, z: Int) = cube[x][y][z]

    override fun toString(): String = "Cube $dimension"
}

private fun checkCubeOfDouble() {
    val cube = CubeOfDouble(1, 2, 3)
    cube[0, 0, 0] = 1.0
    println(cube)
}