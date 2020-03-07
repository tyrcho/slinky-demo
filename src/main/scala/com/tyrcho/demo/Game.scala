package com.tyrcho.demo

import slinky.core.StatelessComponent
import slinky.core.annotations.react
import slinky.web.html._

@react class Game extends StatelessComponent {
  type Props = Unit // no props

  def render = {
    div(className := "game")(
      div(className := "game-board")(
        Board()
      ),
    )
  }
}
