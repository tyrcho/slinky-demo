package com.tyrcho.demo

import slinky.core.StatelessComponent
import slinky.core.annotations.react
import slinky.web.html._
@react
class Board extends StatelessComponent {

  case class Props(
      squares: Vector[String],
      handleClick: Int => Unit,
      next: String,
      winner: Option[String]
  )

  def renderSquare(i: Int) =
    Square(props.squares(i), () => props.handleClick(i))

  def render = {
    div(
      div(className := "status")(
        props.winner.fold(s"Next player: ${props.next}")(w => s"$w has won")
      ),
      div(className := "board-row")(
        renderSquare(0),
        renderSquare(1),
        renderSquare(2)
      ),
      div(className := "board-row")(
        renderSquare(3),
        renderSquare(4),
        renderSquare(5)
      ),
      div(className := "board-row")(
        renderSquare(6),
        renderSquare(7),
        renderSquare(8)
      )
    )
  }
}
