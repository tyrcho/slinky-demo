package com.tyrcho.tictactoe

import com.tyrcho.tictactoe.domain.BoardState
import slinky.core.StatelessComponent
import slinky.core.annotations.react
import slinky.web.html.{className, div}

@react
class Board extends StatelessComponent {

  case class Props(
                    boardState: BoardState,
                    handleClick: Int => Unit
                  )

  def renderSquare(i: Int) =
    Square(props.boardState.cells(i), () => props.handleClick(i))

  def render =
    props.boardState.winner.x match {
      case None => div(
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
      case _ =>
        div(className := "board-won")(
          props.boardState.winner.toString
        )

    }
}
