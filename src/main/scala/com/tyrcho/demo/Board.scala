package com.tyrcho.demo

import slinky.core.Component
import slinky.core.annotations.react
import slinky.web.html._


@react class Board extends Component {
  type Props = Unit // no props

  case class State(
                    squares: Vector[String] = Vector.tabulate(9)(_.toString),
                    xIsNext: Boolean = true
                  ) {

    def next: String = if (xIsNext) "X" else "O"

    def play(i: Int): State = State(
      squares = squares.updated(i, next),
      xIsNext = !xIsNext
    )

    def winner: Option[String] = {
      val lines = Seq(
        Seq(0, 1, 2),
        Seq(3, 4, 5),
        Seq(6, 7, 8),
        Seq(0, 3, 6),
        Seq(1, 4, 7),
        Seq(2, 5, 8),
        Seq(0, 4, 8),
        Seq(2, 4, 6),
      )
      lines
        .collect { case line if line.map(squares).distinct.size == 1 => squares(line.head) }
        .headOption

    }
  }

  override def initialState: State = State()

  def renderSquare(i: Int) = Square(state.squares(i), () => handleClick(i))

  def handleClick(i: Int) = setState(state.play(i))

  def render = {
    div(
      div(className := "status")(
        state.winner.fold(s"Next player: ${state.next}")(w => s"$w has won")),
      div(className := "board-row")(
        renderSquare(0),
        renderSquare(1),
        renderSquare(2),
      ),
      div(className := "board-row")(
        renderSquare(3),
        renderSquare(4),
        renderSquare(5),
      ),
      div(className := "board-row")(
        renderSquare(6),
        renderSquare(7),
        renderSquare(8),
      ),
    )
  }
}
