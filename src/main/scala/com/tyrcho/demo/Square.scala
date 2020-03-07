package com.tyrcho.demo

import slinky.core.Component
import slinky.core.annotations.react
import slinky.web.html._

@react class Square extends Component {

  case class Props(value: Int)

  case class State(value: String)

  override def initialState: State = State(props.value.toString)

  def render = {
    button(
      onClick := (_ => setState(State("X")))
    )(
      state.value
    )
  }
}
