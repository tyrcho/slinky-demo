package com.tyrcho.tictactoe

import com.tyrcho.tictactoe.domain.BoardState
import slinky.core.Component
import slinky.core.annotations.react
import slinky.web.html._

@react
class Game extends Component {
  type Props = Unit // no props

  override def initialState: State = State()

  case class State(
                    history: List[BoardState] = List(BoardState()),
                    stepNumber: Int = 0
                  ) {

    def nextIsX: Boolean = history(stepNumber).xIsNext

    def current: BoardState = history.reverse(stepNumber)

    def play(i: Int): State = {
      State(
        history = history.head.play(i) :: history.takeRight(stepNumber + 1),
        stepNumber = stepNumber + 1
      )
    }

    def jumpTo(i: Int): State = copy(stepNumber = i)
  }

  def handleClick(i: Int) =
    if (state.current.canPlay(i))
      setState(state.play(i))

  def jump(i: Int): Unit =
    setState(state.jumpTo(i))

  def render = {
    div(className := "game")(
      div(className := "game-board")(
        Board(state.current, handleClick)
      ),
      div(className := "game-info")(
        renderHistory
      )
    )
  }

  private def renderHistory =
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

}
