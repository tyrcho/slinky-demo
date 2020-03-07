package com.tyrcho.demo

import slinky.core.Component
import slinky.core.annotations.react
import slinky.web.html._

@react
class Game extends Component {
  type Props = Unit // no props

  override def initialState: State = State()

  case class State(
                    history: List[Vector[String]] = List(Vector.tabulate(9)(_.toString)),
                    xIsNext: Boolean = true,
                  ) {

    def next: String = if (xIsNext) "X" else "O"

    def play(i: Int): State = State(
      history = history.head.updated(i, next) :: history,
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
      val squares = history.head
      lines
        .collect { case line if line.map(squares).distinct.size == 1 => squares(line.head) }
        .headOption

    }

  }

  def handleClick(i: Int) =
    if (state.winner.isEmpty && state.history.head(i) == i.toString)
      setState(state.play(i))


  def render = {
    div(className := "game")(
      div(className := "game-board")(
        Board(state.history.head, handleClick, state.next, state.winner)
      ),
    )
  }
}
