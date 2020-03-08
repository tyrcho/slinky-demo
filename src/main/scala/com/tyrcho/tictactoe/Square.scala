package com.tyrcho.tictactoe

import com.tyrcho.tictactoe.domain.CellState
import slinky.core.FunctionalComponent
import slinky.core.annotations.react
import slinky.web.html.{button, className, onClick}

@react
object Square {

  case class Props(value: CellState, handleClick: () => Unit)

  val component = FunctionalComponent[Props] { props =>
    button(
      className := "square",
      onClick := (_ => props.handleClick())
    )(
      props.value.toString
    )
  }
}
