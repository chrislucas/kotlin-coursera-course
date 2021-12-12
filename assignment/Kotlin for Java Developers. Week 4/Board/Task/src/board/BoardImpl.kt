package board


fun createSquareBoard(width: Int): SquareBoard = TableSquareBoard(width)

fun <T> createGameBoard(width: Int): GameBoard<T> = TableGameBoard(width)