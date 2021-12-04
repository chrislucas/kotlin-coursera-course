package board

import kotlin.math.min

private inline fun <reified T> createMatrix2D(w: Int, h: Int, fn: (Int, Int) -> T?): Array<Array<T?>> =
    Array(w) { i -> Array(h) { j -> fn(i, j) } }

open class TableSquareBoard(override val width: Int) : SquareBoard {

    private val mWith: Int
        get() = width

    protected val content = createMatrix2D<Any?>(mWith, mWith) { _, _ -> null }

    protected val collection: MutableList<Cell>
        get() {
            val mutableList = mutableListOf<Cell>()
            for (i in 0 until mWith) {
                for (j in 0 until mWith)
                    mutableList += Cell(i + 1, j + 1)
            }
            return mutableList
        }

    override fun getCellOrNull(i: Int, j: Int): Cell? {
        return if (i > mWith || j > mWith) {
            null
        } else {
            Cell(i, j)
        }
    }

    override fun getCell(i: Int, j: Int): Cell {
        return if (i > mWith || j > mWith) {
            throw IllegalArgumentException("($i, $j) exceed the dimension of board")
        } else {
            Cell(i, j)
        }
    }

    override fun getAllCells(): Collection<Cell> = collection

    override fun getRow(i: Int, jRange: IntProgression): List<Cell> {
        val mutableList = mutableListOf<Cell>()
        if (i < mWith) {
            val range = if (jRange.first < jRange.last) {
                jRange.first..min(mWith, jRange.last)
            } else {
                min(mWith, jRange.last)..jRange.first
            }
            for (k in range) {
                mutableList += Cell(i, k)
            }
        }
        return mutableList
    }

    override fun getColumn(iRange: IntProgression, j: Int): List<Cell> {
        val mutableList = mutableListOf<Cell>()
        if (j < mWith) {
            val range = if (iRange.first < iRange.last) {
                iRange.first..min(mWith, iRange.last)
            } else {
                min(mWith, iRange.last)..iRange.first
            }
            for (k in range) {
                mutableList += Cell(k, j)
            }
        }
        return mutableList
    }

    override fun Cell.getNeighbour(direction: Direction): Cell? = checkDirection(direction, this)

}