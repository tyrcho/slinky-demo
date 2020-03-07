package com.tyrcho.demo

import slinky.core.StatelessComponent
import slinky.core.annotations.react
import slinky.web.html._

@react class Board extends StatelessComponent {
  type Props = Unit // no props

  def renderSquare(i: Int) = Square(i)

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
