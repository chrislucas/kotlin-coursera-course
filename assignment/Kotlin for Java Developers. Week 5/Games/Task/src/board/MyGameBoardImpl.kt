package board

import kotlin.math.min

class MyGameBoardImpl<T>(override val width: Int) : GameBoard<T> {

    private val squareBoard = MySquareBoard(width)

    override fun getCellOrNull(i: Int, j: Int): Cell? = squareBoard.getCellOrNull(i, j)

    override fun getCell(i: Int, j: Int): Cell = squareBoard.getCell(i, j)

    override fun getAllCells(): Collection<Cell> = squareBoard.getAllCells()

    override fun getRow(i: Int, jRange: IntProgression): List<Cell> =
        squareBoard.getRow(i, jRange)

    override fun getColumn(iRange: IntProgression, j: Int): List<Cell> =
        squareBoard.getColumn(iRange, j)

    override fun Cell.getNeighbour(direction: Direction): Cell? =
        squareBoard.getNeighbourCell(this, direction)

    override fun get(cell: Cell): T? {
        return cell.let { (i, j) ->
            if (squareBoard.isValidCell(i, j)) {
                squareBoard.contentCells[i, j] as T?
            } else {
                null
            }
        }
    }

    override fun set(cell: Cell, value: T?) {
        cell.let { (i, j) ->
            if (squareBoard.isValidCell(i, j)) {
                squareBoard.contentCells[i, j] = value
            }
        }
    }

    override fun filter(predicate: (T?) -> Boolean): Collection<Cell> =
        squareBoard.listAllCells.filter { cell -> predicate(get(cell)) }

    override fun find(predicate: (T?) -> Boolean): Cell? =
        squareBoard.listAllCells.find { cell -> predicate(get(cell)) }


    override fun any(predicate: (T?) -> Boolean): Boolean =
        squareBoard.listAllCells.any { predicate(get(it)) }

    override fun all(predicate: (T?) -> Boolean): Boolean =
        squareBoard.listAllCells.all { predicate(get(it)) }
}

class MySquareBoard(override val width: Int, fn: () -> Any? = { null }) : SquareBoard {

    val contentCells = createMatrixWithInitFun(width + 1, width + 1) { _, _ -> fn() }

    private val matrixCells = createMatrixWithInitFun(width + 1, width + 1) { i, j -> Cell(i, j) }

    val listAllCells: MutableList<Cell>
        get() {
            val all = mutableListOf<Cell>()
            for (i in 0 until width)
                for (j in 0 until width)
                    all += matrixCells[i + 1, j + 1]
            return all
        }

    override fun getCellOrNull(i: Int, j: Int): Cell? {
        return if (isValidCell(i, j)) {
            matrixCells[i, j]
        } else {
            null
        }
    }

    override fun getCell(i: Int, j: Int): Cell {
        return if (isValidCell(i, j)) {
            matrixCells[i, j]
        } else {
            throw IllegalArgumentException("($i, $j) exceed the dimension of board")
        }
    }

    override fun getAllCells(): Collection<Cell> = listAllCells

    override fun getRow(i: Int, jRange: IntProgression): List<Cell> {
        val row = mutableListOf<Cell>()
        if (i <= width) {
            val range = jRange.run {
                if (first <= last) {
                    first..min(width, last)
                } else {
                    min(width, first) downTo last
                }
            }
            for (k in range) {
                row += matrixCells[i, k]
            }
        }
        return row
    }

    override fun getColumn(iRange: IntProgression, j: Int): List<Cell> {
        val column = mutableListOf<Cell>()
        if (j <= width) {
            val range = iRange.run {
                if (first <= last) {
                    first..min(width, last)
                } else {
                    min(width, first) downTo last
                }
            }
            for (k in range) {
                column += matrixCells[k, j]
            }
        }
        return column
    }

    override fun Cell.getNeighbour(direction: Direction): Cell? =
        getNeighbourCell(this, direction)

    fun getNeighbourCell(cell: Cell, direction: Direction): Cell? =
        checkDirection(cell, direction, matrixCells)
}

internal fun SquareBoard.isValidCell(i: Int, j: Int) = i in 1..width && j in 1..width

internal fun SquareBoard.isValidCell(cell: Cell): Boolean =
    cell.let { (i, j) ->
        isValidCell(i, j)
    }

internal fun SquareBoard.checkDirection(cell: Cell, direction: Direction, cells: Array<Array<Cell>>): Cell? {
    val (i, j) = cell
    val pair = when (direction) {
        Direction.UP -> i - 1 to j
        Direction.DOWN -> i + 1 to j
        Direction.LEFT -> i to j - 1
        else -> i to j + 1
    }
    return pair.let { (i, j) ->
        if (isValidCell(i, j)) {
            cells[i, j]
        } else {
            null
        }
    }
}