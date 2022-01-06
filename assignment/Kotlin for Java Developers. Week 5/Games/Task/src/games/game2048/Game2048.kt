package games.game2048

import board.*
import board.fromDirectionToPosition
import games.game.Game

/*
 * Your task is to implement the game 2048 https://en.wikipedia.org/wiki/2048_(video_game).
 * Implement the utility methods below.
 *
 * After implementing it you can try to play the game running 'PlayGame2048'.
 */
fun newGame2048(initializer: Game2048Initializer<Int> = RandomGame2048Initializer): Game =
    Game2048(initializer)

class Game2048(private val initializer: Game2048Initializer<Int>) : Game {
    private val board = createGameBoard<Int?>(4)

    override fun initialize() {
        repeat(2) {
            board.addNewValue(initializer)
        }
    }

    override fun canMove() = board.any { it == null }

    override fun hasWon() = board.any { it == 2048 }

    override fun processMove(direction: Direction) {
        if (board.moveValues(direction)) {
            board.addNewValue(initializer)
        }
    }

    override fun get(i: Int, j: Int): Int? = board.run { get(getCell(i, j)) }
}

/*
 * Add a new value produced by 'initializer' to a specified cell in a board.
 */
fun GameBoard<Int?>.addNewValue(initializer: Game2048Initializer<Int>) {
    val pair = initializer.nextValue(this)
    pair?.let { (cell, value) ->
        this[cell] = value
    }
}

/*
 * Update the values stored in a board,
 * so that the values were "moved" in a specified rowOrColumn only.
 * Use the helper function 'moveAndMergeEqual' (in Game2048Helper.kt).
 * The values should be moved to the beginning of the row (or column),
 * in the same manner as in the function 'moveAndMergeEqual'.
 * Return 'true' if the values were moved and 'false' otherwise.
 */
fun GameBoard<Int?>.moveValuesInRowOrColumn(rowOrColumn: List<Cell>): Boolean {
    val nonNullOriginValues = rowOrColumn
        .filter { this[it] != null }
        .map { "${this[it]}" }

    val mergedValues = nonNullOriginValues
        .moveAndMergeEqual { value ->
            value.repeat(2)
        }

    rowOrColumn.forEachIndexed { idx, ceil ->
        this[ceil] = if (idx < mergedValues.size) {
            mergedValues[idx].toInt()
        } else {
            null
        }
    }

    return mergedValues.size < rowOrColumn.size
}

/*
 * Update the values stored in a board,
 * so that the values were "moved" to the specified direction
 * following the rules of the 2048 game .
 * Use the 'moveValuesInRowOrColumn' function above.
 * Return 'true' if the values were moved and 'false' otherwise.
 */
fun GameBoard<Int?>.moveValues(direction: Direction): Boolean {


    fun merge(list: List<Int>, direction: Direction): List<Int>? {
        return list.takeIf(List<Int>::isNotEmpty)
            ?.let { values ->
                val s = values.moveAndMergeEqual {
                    it * 2
                }
                /*
                    Quando movemos colunas para direita ou linhas
                    para baixo devemos tomar o cuidado de como o merge
                    das colunas e linhas é feito para um caso especifiico
                    - Quando num tabuleiro de dimensao par o numero
                    de elementos na linha ou coluna é impar e todos iguais
                    somamos fazemos o merge da direita para esquerda

                        - o problema é que a forma que o exercicio foi
                        estruturado nao conseguimos controlar isso na funcao
                        moveAndMergeEqual {} mas como os numeros sao iguais
                        podemos fazer o merge da esqquerda para direita e inverter
                 */

                val check = values.size and 1 == 1 &&
                        values.size > 1 &&
                        values.size < this.width &&
                        values.isAllEquals()

                if ((direction == Direction.RIGHT || direction == Direction.DOWN) && check) {
                    s.reversed()
                } else {
                    s
                }
            }
    }

    fun move(i: Int, direction: Direction, values: List<Int>) {
        when (direction) {
            Direction.RIGHT -> {
                // preencher o board da direita para esquerda
                val row = getRow(i, width downTo 1)
                var idx = values.size - 1
                row.forEach { cell ->
                    this[cell] = if (idx >= 0) {
                        values[idx--]
                    } else {
                        null
                    }
                }
            }
            // preencher o board da esquerda para direita
            Direction.LEFT -> {
                val row = getRow(i, 1..width)
                row.forEachIndexed { idx, cell ->
                    this[cell] = if (idx < values.size) {
                        values[idx]
                    } else {
                        null
                    }
                }
            }

            Direction.DOWN -> {
                // preencher o board de baixo pra cima
                val row = getColumn(width downTo 1, i)
                var idx = values.size - 1
                row.forEach { cell ->
                    this[cell] = if (idx >= 0) {
                        values[idx--]
                    } else {
                        null
                    }
                }
            }

            else -> {
                // UP - preenche o board de cima para baixo
                val row = getColumn(1..width, i)
                row.forEachIndexed { idx, cell ->
                    this[cell] = if (idx < values.size) {
                        values[idx]
                    } else {
                        null
                    }
                }
            }
        }
    }

    var flag = false

    when (direction) {
        Direction.RIGHT, Direction.LEFT -> {
            for (i in 1..width) {
                val row = getRow(i, 1..width)
                val filtered = row
                    .filter { cell -> this[cell] != null }
                val notNull: List<Int> = filtered
                    .mapNotNull { cell -> this[cell] }

                merge(notNull, direction)?.let { values ->
                    move(i, direction, values)
                    if (!flag) {
                        val filtered2 = getRow(i, 1..width)
                            .filter { cell -> this[cell] != null }
                        flag = filtered != filtered2
                    }
                }
            }
        }
        else -> {
            for (i in 1..width) {
                val column = getColumn(1..width, i)
                val filtered = column
                    .filterNot { cell -> this[cell] == null }
                val notNull = filtered
                    .mapNotNull { cell -> this[cell] }

                merge(notNull, direction)?.let { values ->
                    move(i, direction, values)
                    if (!flag) {
                        val filtered2 = getColumn(1..width, i)
                            .filterNot { cell -> this[cell] == null }
                        flag = filtered != filtered2
                    }
                }
            }
        }
    }
    return flag
}