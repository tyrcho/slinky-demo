package com.tyrcho.tictactoe.domain

case class CellState(x: Option[Boolean]) {
  override def toString: String =
    x.fold("")(v => if (v) "X" else "O")

  def isEmpty: Boolean = x.isEmpty
}
