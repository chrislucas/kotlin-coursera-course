package com.br.week4.operators.conventions.settersyntax

import com.br.week4.operators.conventions.Matrix
import com.br.week4.operators.conventions.create
import com.br.week4.operators.conventions.string


interface Behavior

class MockBehavior : Behavior

abstract class ChessPiece(open val behavior: Behavior)

data class Knight(override val behavior: Behavior) : ChessPiece(behavior)

data class Bishop(override val behavior: Behavior) : ChessPiece(behavior)

data class Pawn(override val behavior: Behavior) : ChessPiece(behavior)

class Board(val matrix: Matrix<ChessPiece?>)

operator fun Board.set(x: Int, y: Int, piece: ChessPiece) {
    matrix[x][y] = piece
}

fun main() {
    val board = Board(create(8, 8))
    board[0, 0] = Pawn(MockBehavior())
    board[0, 1] = Bishop(MockBehavior())
    board[7, 7] = Knight(MockBehavior())

    for (line in board.matrix) {
        println(line.string)
    }
}
