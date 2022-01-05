package games.gameOfFifteen

import board.*
import games.game.Game

/*
 * Implement the Game of Fifteen (https://en.wikipedia.org/wiki/15_puzzle).
 * When you finish, you can play the game by executing 'PlayGameOfFifteen'.
 */
fun newGameOfFifteen(initializer: GameOfFifteenInitializer = RandomGameInitializer()): Game =
    GameImpl(initializer)


class GameImpl(private val initValue: GameOfFifteenInitializer) : Game {

    private val board = createGameBoard<Int?>(4)

    private var lastMovement: Direction? = null

    override fun initialize() {
        val values = initValue.initialPermutation
        var idx = 0
        board.getAllCells().forEach { cell ->
            if (idx < values.size) {
                board[cell] = values[idx++]
            }
        }
        //println(board)
    }

    override fun canMove(): Boolean {
        return true //getValuesInBoard().parityOfPermutation()
    }

    override fun hasWon(): Boolean = getValuesInBoard().isSorted()

    private fun getValuesInBoard(): List<Int> =
        board.getAllCells().mapNotNull { cell -> board[cell] }

    override fun processMove(direction: Direction) {
        //lastMovement = direction
        fun filter(cells: List<Cell>): List<Int> {
            return cells
                .filter { cell -> board[cell] != null }
                .mapNotNull { cell -> board[cell] }
        }
        fun moveRowOrColumn() {
            val width = board.width
            when (direction) {
                Direction.LEFT, Direction.RIGHT -> {
                    for (i in 1..width) {
                        val filtered = filter(board.getRow(i, 1..width))
                        if (filtered.size < width) {
                            /*
                                pegar a posicao na linha que eh nula (ESPACO
                                VAZIO no Tabuleiro) para mover
                                o elemento a sua esquerda ou direita para
                                essa posicao nula
                                - SE o movimento foi para DIREITA
                                devo pegar o elemento a ESQUERDA do espaco
                                e move-lo para direita, preenchendo o espaco vazio
                                e marcar a posicao do elemento a esquerda como nulo
                                - Se o movimento for para ESQUERDA devo
                                pegar o elemento a DIREITA do espaco vazio, preencher
                                o espaco vazio com ele e marcar a antiga posicao como vazia
                            * */

                            val emptyCell = board.getRow(i, 1..width)
                                .find { board[it] == null }

                            emptyCell?.let { cell ->
                                if (direction == Direction.LEFT) {
                                    with(board) {
                                        val leftCell = getCell(cell.i, cell.j + 1)
                                        this[cell] = this[leftCell]
                                        this[leftCell] = null
                                    }
                                } else {
                                    with(board) {
                                        val rightCell = getCell(cell.i, cell.j - 1)
                                        this[cell] = this[rightCell]
                                        this[rightCell] = null
                                    }
                                }
                            }
                            break
                        }
                    }
                }
                else -> {
                    for (i in 1..width) {
                        val filtered = filter(board.getColumn(1..width, i))
                        if (filtered.size < width) {
                            /*
                                1 - Pegar a posicao que tem um espaco vazio na colula

                                - SE o movimento for PARA CIMA (UP) achar a posicao
                                do espaco, pegar o elemento logo ABAIXO dele posiciona-lo
                                no local do espaco e por fim transformar a posicao antiga do
                                elemento movido em espaco
                                - SE o movimento for PARA BAIXO (DOWN) devo achar o elemento
                                diretamente a cima e move-lo para baixo e a posicao onde ele
                                estava devo marcar como um espaco vazio

                             */
                            val emptyCell = board.getColumn(1..width, i)
                                .find { cell -> board[cell] == null }
                            emptyCell?.let {
                                    cell ->
                                if (direction == Direction.UP) {
                                    with(board) {
                                        val downCell = getCell(cell.i + 1, cell.j)
                                        this[cell] = this[downCell]
                                        this[downCell] = null
                                    }
                                } else {
                                    with(board) {
                                        val upCell = getCell(cell.i - 1, cell.j)
                                        this[cell] = this[upCell]
                                        this[upCell] = null
                                    }
                                }
                            }
                            break
                        }
                    }
                }
            }
        }
        moveRowOrColumn()
    }

    override fun get(i: Int, j: Int): Int? {
        return board.getCell(i, j)
            .run { board[this] }
    }
}

fun main() {
    val gameBoard = newGameOfFifteen()
    gameBoard.initialize()
}