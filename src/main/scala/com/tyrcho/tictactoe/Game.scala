package com.tyrcho.tictactoe

import com.tyrcho.tictactoe.domain.{BoardState, CellState, GameWithHistory}
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

    def winner: CellState = current.winner

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
        div(className := "status")(
          h1(
            state.winner.x match {
              case None =>
                s"Next player: ${if (state.nextIsX) "X" else "O"}"
              case Some(_) => s"${state.winner} has won"
            }
          )
        ),
        renderHistory
      )
    )
  }

  private def renderHistory =
    ol(
      (0 until state.maxStep).map { i =>
        li(key := i.toString)(
          button(
            onClick := (_ => setState(_.jumpTo(i))),
            className := (if (i == state.gameWithHistory.currentTurn) "history-selected" else "history")
          )(
            if (i == 0) "debut"
            else s"tour #$i"
          )
        )
      }
    )

}
