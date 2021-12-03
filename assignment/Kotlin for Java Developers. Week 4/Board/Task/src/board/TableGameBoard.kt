package board

import kotlin.math.min


private inline fun <reified T> matrix2D(w: Int, h: Int, fn: (Int, Int) -> T?): Array<Array<T?>> =
    Array(w) { i -> Array(h) { j -> fn(i, j) } }


class TableGameBoard<T>(override val width: Int) : GameBoard<T> {

    private val content = matrix2D<T?>(width, width) { _, _ -> null }

    override fun getCellOrNull(i: Int, j: Int): Cell? {
        return if (i > width || j > width) {
            null
        } else {
            Cell(i, j)
        }
    }

    override fun getCell(i: Int, j: Int): Cell {
        return if (i > width || j > width) {
            throw IllegalArgumentException("($i, $j) exceed the dimension of board")
        } else {
            Cell(i, j)
        }
    }

    override fun getAllCells(): Collection<Cell> {
        val mutableList = mutableListOf<Cell>()

        for (i in 0 until width) {
            for (j in 0 until width)
                mutableList += Cell(i, j)
        }
        return mutableList
    }

    override fun getRow(i: Int, jRange: IntProgression): List<Cell> {
        val mutableList = mutableListOf<Cell>()
        if (i < width) {
            val range = if (jRange.first < jRange.last) {
                jRange.first..min(width, jRange.last)
            } else {
                min(width, jRange.last)..jRange.first
            }
            for (k in range) {
                mutableList += Cell(i, k)
            }
        }
        return mutableList
    }

    override fun getColumn(iRange: IntProgression, j: Int): List<Cell> {
        val mutableList = mutableListOf<Cell>()
        if (j < width) {
            val range = if (iRange.first < iRange.last) {
                iRange.first..min(width, iRange.last)
            } else {
                min(width, iRange.last)..iRange.first
            }
            for (k in range) {
                mutableList += Cell(k, j)
            }
        }
        return mutableList
    }

    override fun Cell.getNeighbour(direction: Direction): Cell? {
        TODO("Not yet implemented")
    }

    override operator fun get(cell: Cell): T? {
        val (i, j) = cell
        return content[i][j]
    }

    override operator fun set(cell: Cell, value: T?) {
        TODO("Not yet implemented")
    }

    override fun filter(predicate: (T?) -> Boolean): Collection<Cell> {
        TODO("Not yet implemented")
    }

    override fun find(predicate: (T?) -> Boolean): Cell? {
        TODO("Not yet implemented")
    }

    override fun any(predicate: (T?) -> Boolean): Boolean {
        TODO("Not yet implemented")
    }

    override fun all(predicate: (T?) -> Boolean): Boolean {
        TODO("Not yet implemented")
    }
}