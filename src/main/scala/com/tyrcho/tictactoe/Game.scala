package com.tyrcho.tictactoe

import com.tyrcho.tictactoe.domain.CellState
import slinky.core.Component
import slinky.core.annotations.react
import slinky.web.html._

@react
class Game extends Component {
  type Props = Unit // no props

  override def initialState: State = State()

  case class State(
                    history: List[Vector[CellState]] = List(Vector.fill(9)(CellState(None))),
                    xIsNext: Boolean = true,
                    stepNumber: Int = 0
                  ) {

    def nextIsX: Boolean = stepNumber % 2 == 0

    def current: Vector[CellState] = history.reverse(stepNumber)

    def play(i: Int): State = {
      val board = current.updated(i, CellState(Some(nextIsX)))
      State(
        history = board :: history.takeRight(stepNumber + 1),
        xIsNext = !xIsNext,
        stepNumber = stepNumber + 1
      )
    }

    def jumpTo(i: Int): State = copy(stepNumber = i)

    def winner: CellState = {
      val lines = Seq(
        Seq(0, 1, 2),
        Seq(3, 4, 5),
        Seq(6, 7, 8),
        Seq(0, 3, 6),
        Seq(1, 4, 7),
        Seq(2, 5, 8),
        Seq(0, 4, 8),
        Seq(2, 4, 6)
      )
      lines
        .find { line =>
          line.map(current).distinct.size == 1
        }.map(line => current(line.head))
      match {
        case None => CellState(None)
        case Some(cs) => cs
      }
    }

  }

  def handleClick(i: Int) =
    if (state.winner.isEmpty && state.current(i).isEmpty)
      setState(state.play(i))

  def jump(i: Int): Unit =
    setState(state.jumpTo(i))

  def render = {
    div(className := "game")(
      div(className := "game-board")(
        Board(state.current, handleClick, state.nextIsX, state.winner)
      ),
      div(className := "game-info")(
        ol(
          (0 to state.stepNumber).map { i =>
            li(key := i.toString)(
              button(onClick := (_ => jump(i)))(
                if (i == 0) "debut"
                else s"tour #$i"
              )
            )
          }
        )
      )
    )
  }
}
