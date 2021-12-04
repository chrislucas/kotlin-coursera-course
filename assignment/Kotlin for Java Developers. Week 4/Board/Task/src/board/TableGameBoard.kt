package board

class TableGameBoard<T>(override val width: Int) : TableSquareBoard(width), GameBoard<T> {

    override operator fun get(cell: Cell): T? {
        return if (isValidCell(cell)) {
            val (i, j) = cell
            content[i][j] as T
        } else {
            null
        }
    }

    override operator fun set(cell: Cell, value: T?) {
        val (i, j) = cell
        content[i][j] = value
    }

    override fun filter(predicate: (T?) -> Boolean): Collection<Cell> {
        return collection.filter { cell -> predicate(get(cell)) }
    }

    override fun find(predicate: (T?) -> Boolean): Cell? {
        return collection.find { cell -> predicate(get(cell)) }
    }

    override fun any(predicate: (T?) -> Boolean): Boolean {
        return collection.any { cell -> predicate(get(cell)) }
    }

    override fun all(predicate: (T?) -> Boolean): Boolean {
        return collection.all { cell -> predicate(get(cell)) }
    }
}