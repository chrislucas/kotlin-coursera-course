package board

import kotlin.math.min

open class TableSquareBoard(override val width: Int) : SquareBoard {

    protected val content: Array<Array<Any?>> = createMatrix<Any?>(width + 1, width + 1) { null }

    private val matrix: Array<Array<Cell>> = createMatrixWithInitFun(width + 1, width + 1) { i, j -> Cell(i, j) }

    protected val collection: MutableList<Cell>
        get() {
            val mutableList = mutableListOf<Cell>()
            for (i in 0 until width) {
                for (j in 0 until width)
                    mutableList += matrix[i + 1, j + 1]
            }
            return mutableList
        }

    override fun getCellOrNull(i: Int, j: Int): Cell? {
        return if (i > width || j > width) {
            null
        } else {
            matrix[i, j]
        }
    }

    override fun getCell(i: Int, j: Int): Cell {
        return if (i > width || j > width) {
            throw IllegalArgumentException("($i, $j) exceed the dimension of board")
        } else {
            matrix[i, j]
        }
    }

    override fun getAllCells(): Collection<Cell> = collection

    override fun getRow(i: Int, jRange: IntProgression): List<Cell> {
        val mutableList = mutableListOf<Cell>()
        if (i <= width) {
            val range = jRange.run {
                if (first <= last) {
                    first..min(width, last)
                } else {
                    min(width, first) downTo last
                }
            }
            for (k in range) {
                mutableList += matrix[i, k]
            }
        }
        return mutableList
    }

    override fun getColumn(iRange: IntProgression, j: Int): List<Cell> {
        val mutableList = mutableListOf<Cell>()
        if (j <= width) {
            val range = iRange.run {
                if (first <= last) {
                    first..min(width, last)
                } else {
                    min(width, first) downTo last
                }
            }
            for (k in range) {
                mutableList += matrix[k, j]
            }
        }
        return mutableList
    }

    override fun Cell.getNeighbour(direction: Direction): Cell? =
        checkDirection(direction, this)?.run {
            val (i, j) = this
            matrix[i, j]
        }

}

fun main() {
    val matrix = createMatrix<Int?>(3, 3) { null }
    println(matrix)
}