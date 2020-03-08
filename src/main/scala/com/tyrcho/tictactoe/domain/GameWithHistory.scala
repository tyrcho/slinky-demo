package com.tyrcho.tictactoe.domain

case class GameWithHistory(
    history: Vector[BoardState] = Vector(BoardState()),
    currentTurn: Int = 0
) {
  def nextIsX: Boolean = history(currentTurn).xIsNext

  def current: BoardState = history.reverse(currentTurn)

  def maxStep: Int = history.size

  def play(i: Int): GameWithHistory =
    GameWithHistory(
      history = current.play(i) +: history.takeRight(currentTurn + 1),
      currentTurn = currentTurn + 1
    )

  def jumpTo(i: Int): GameWithHistory =
    copy(currentTurn = i)
}
