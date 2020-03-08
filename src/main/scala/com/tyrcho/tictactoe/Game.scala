package com.tyrcho.tictactoe

import com.tyrcho.tictactoe.domain.{BoardState, GameWithHistory}
import slinky.core.Component
import slinky.core.annotations.react
import slinky.web.html._

@react
class Game extends Component {
  type Props = Unit // no props

  override def initialState: State = State()

  case class State(gameWithHistory: GameWithHistory = GameWithHistory()) {

    def nextIsX: Boolean = gameWithHistory.nextIsX

    def current: BoardState = gameWithHistory.current

    def maxStep: Int = gameWithHistory.maxStep

    def play(i: Int): State =
      State(gameWithHistory = gameWithHistory.play(i))

    def jumpTo(i: Int): State =
      State(gameWithHistory.jumpTo(i))

    def canPlay(i: Int): Boolean = gameWithHistory.current.canPlay(i)
  }

  def handleClick(i: Int) =
    if (state.canPlay(i))
      setState(state.play(i))

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
      (0 until state.maxStep).map { i =>
        li(key := i.toString)(
          button(
            onClick := (_ => setState(_.jumpTo(i)))
          )(
            if (i == 0) "debut"
            else s"tour #$i"
          )
        )
      }
    )

}
