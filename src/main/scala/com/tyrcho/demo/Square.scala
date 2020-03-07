package com.tyrcho.demo

import slinky.core.StatelessComponent
import slinky.core.annotations.react
import slinky.web.html._

@react class Square extends StatelessComponent {

  case class Props(value: String, handleClick: () => Unit)

  def render = {
    button(
      onClick := (_ => props.handleClick())
    )(
      props.value
    )
  }
}
