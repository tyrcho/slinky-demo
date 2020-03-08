package com.tyrcho.tictactoe.domain

case class BoardState(
                       cells: Vector[CellState] = Vector.fill(9)(CellState(None)),
                       turn: Int = 0
                     ) {

  def canPlay(i: Int): Boolean =
    cells(i).isEmpty && winner.isEmpty

  def xIsNext: Boolean = turn % 2 == 0

  def play(i: Int): BoardState =
    BoardState(
      cells = cells.updated(i, CellState(Some(xIsNext))),
      turn = turn + 1
    )

  def winner: CellState = {
    val lines = Seq(
      Seq(0, 1, 2),
      Seq(3, 4, 5),
      Seq(6, 7, 8),
      Seq(0, 3, 6),
      Seq(1, 4, 7),
      Seq(2, 5, 8),
      Seq(0, 4, 8),
      Seq(2, 4, 6)
    )

    def winningLine(line: Seq[Int]): Option[Boolean] = {
      val head = cells(line.head)
      if (line.forall(i => cells(i) == head))
        head.x
      else None
    }


    lines
      .map(winningLine)
      .find(_.isDefined)
      .flatten
      .fold(CellState(None))(w => CellState(Some(w)))
  }
}
