package com.tyrcho.demo

import slinky.core.FunctionalComponent
import slinky.core.annotations.react
import slinky.web.html._

@react object Square {
  case class Props(value: String, handleClick: () => Unit)

  val component = FunctionalComponent[Props] { props =>
    button(
      onClick := (_ => props.handleClick())
    )(
      props.value
    )
  }
}
