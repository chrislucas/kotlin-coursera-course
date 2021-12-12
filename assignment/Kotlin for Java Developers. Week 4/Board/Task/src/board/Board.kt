package board

data class Cell(val i: Int, val j: Int) {
    override fun toString() = "($i, $j)"
}

enum class Direction {
    UP, DOWN, RIGHT, LEFT;

    fun reversed() = when (this) {
        UP -> DOWN
        DOWN -> UP
        RIGHT -> LEFT
        LEFT -> RIGHT
    }
}

inline fun <reified T> createMatrix(w: Int, h: Int, fn: () -> T?): Array<Array<T?>> =
    Array(w) { Array(h) { fn() } }

inline fun <reified T> createMatrixWithInitFun(w: Int, h: Int, fn: (Int, Int) -> T): Array<Array<T>> =
    Array(w) { i -> Array(h) { j -> fn(i, j) } }

operator fun <T> Array<Array<T>>.get(i: Int, j: Int) = this[i][j]

fun SquareBoard.isValidCell(cell: Cell): Boolean {
    val (i, j) = cell
    return i in 1..width && j in 1..width
}

internal fun SquareBoard.checkDirection(direction: Direction, cell: Cell): Cell? {

    fun create(cell: Cell, direction: Direction): Cell {
        val (i, j) = cell
        return when (direction) {
            Direction.UP -> Cell(i - 1, j)
            Direction.DOWN -> Cell(i + 1, j)
            Direction.LEFT -> Cell(i, j - 1)
            else -> Cell(i, j + 1)
        }
    }

    return create(cell, direction).run {
        if (isValidCell(this)) {
            this
        } else {
            null
        }
    }
}

interface SquareBoard {
    val width: Int

    fun getCellOrNull(i: Int, j: Int): Cell?
    fun getCell(i: Int, j: Int): Cell

    fun getAllCells(): Collection<Cell>

    fun getRow(i: Int, jRange: IntProgression): List<Cell>
    fun getColumn(iRange: IntProgression, j: Int): List<Cell>

    fun Cell.getNeighbour(direction: Direction): Cell?
}

interface GameBoard<T> : SquareBoard {

    operator fun get(cell: Cell): T?
    operator fun set(cell: Cell, value: T?)

    fun filter(predicate: (T?) -> Boolean): Collection<Cell>
    fun find(predicate: (T?) -> Boolean): Cell?
    fun any(predicate: (T?) -> Boolean): Boolean
    fun all(predicate: (T?) -> Boolean): Boolean
}