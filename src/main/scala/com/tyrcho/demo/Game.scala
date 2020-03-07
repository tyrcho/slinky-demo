package com.tyrcho.demo

import slinky.core.Component
import slinky.core.annotations.react
import slinky.web.html._

@react
class Game extends Component {
  type Props = Unit // no props

  override def initialState: State = State()

  case class State(
                    history: List[Vector[String]] = List(Vector.tabulate(9)(_.toString)),
                    xIsNext: Boolean = true,
                    stepNumber: Int = 0
                  ) {

    def next: String = if (stepNumber % 2 == 0) "X" else "O"

    def current: Vector[String] = history.reverse(stepNumber)

    def play(i: Int): State = {
      val board = current.updated(i, next)
      State(
        history = board :: history.takeRight(stepNumber + 1),
        xIsNext = !xIsNext,
        stepNumber = stepNumber + 1
      )
    }

    def jumpTo(i: Int): State = copy(stepNumber = i)

    def winner: Option[String] = {
      val lines = Seq(
        Seq(0, 1, 2),
        Seq(3, 4, 5),
        Seq(6, 7, 8),
        Seq(0, 3, 6),
        Seq(1, 4, 7),
        Seq(2, 5, 8),
        Seq(0, 4, 8),
        Seq(2, 4, 6),
      )
      lines
        .collect { case line if line.map(current).distinct.size == 1 => current(line.head) }
        .headOption

    }

  }

  def handleClick(i: Int) =
    if (state.winner.isEmpty && state.current(i) == i.toString)
      setState(state.play(i))

  def jump(i: Int): Unit =
    setState(state.jumpTo(i))

  def render = {
    div(className := "game")(
      div(className := "game-board")(
        Board(state.current, handleClick, state.next, state.winner)
      ),
      div(className := "game-info")(
        ol(
          (0 to state.stepNumber).map { i =>
            li(key := i.toString)(
              button(onClick := (_ => jump(i)))(
                if (i == 0) "debut" else s"tour #$i"))
          }
        )
      )
    )
  }
}
