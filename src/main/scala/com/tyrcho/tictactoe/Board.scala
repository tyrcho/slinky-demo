package com.tyrcho.tictactoe

import com.tyrcho.tictactoe.domain.CellState
import slinky.core.StatelessComponent
import slinky.core.annotations.react
import slinky.web.html.{className, div}

@react
class Board extends StatelessComponent {

  case class Props(
                    squares: Vector[CellState],
                    handleClick: Int => Unit,
                    nextIsX: Boolean,
                    winner: CellState
                  )

  def renderSquare(i: Int) =
    Square(props.squares(i), () => props.handleClick(i))

  def render = {
    div(
      div(className := "status")(
        props.winner.x.fold(s"Next player: ${if (props.nextIsX) "X" else "O"}")(_ => s"${props.winner} has won")
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
