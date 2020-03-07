package com.tyrcho.demo

import slinky.core.StatelessComponent
import slinky.core.annotations.react
import slinky.web.html.{button, className}

@react class Square extends StatelessComponent {

  case class Props(value: Int)

  def render = {
    button(className := "square")(props.value)
  }
}
