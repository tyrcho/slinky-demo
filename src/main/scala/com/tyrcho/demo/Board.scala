package com.tyrcho.demo

import slinky.core.Component
import slinky.core.annotations.react
import slinky.web.html._

@react class Board extends Component {
  type Props = Unit // no props

  case class State(squares: Vector[String])

  override def initialState: State = State(Vector.tabulate(9)(_.toString))

  def renderSquare(i: Int) = Square(state.squares(i), () => handleClick(i))

  def handleClick(i: Int) = setState(State(state.squares.updated(i, "X")))

  def render = {
    div(
      div(className := "status")("Next player: X"),
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
