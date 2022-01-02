package board


fun createSquareBoard(width: Int): SquareBoard = MySquareBoard(width)
fun <T> createGameBoard(width: Int): GameBoard<T> = MyGameBoardImpl(width)

