package tictactoe;

import static tictactoe.Player.NOBODY;
import static tictactoe.Player.O;
import static tictactoe.Player.X;
import static tictactoe.Status.DRAW;
import static tictactoe.Status.GAME_ON;
import static tictactoe.Status.O_HAS_WON;
import static tictactoe.Status.SQUARE_ALREADY_PLAYED;
import static tictactoe.Status.X_HAS_WON;

public class Game {

  private final Player currentPlayer;
  private final Status status;
  private final Board board;

  public Game(){
    currentPlayer = null;
    board = new Board();
    status = GAME_ON;
  }

  private Game(Status status, Board board, Player currentPlayer) {
    this.board = board;
    this.currentPlayer = currentPlayer;

    if (board.hasWon(currentPlayer)){
      this.status = currentPlayer == X ? X_HAS_WON : O_HAS_WON;
    } else if (board.isFull()){
      this.status = DRAW;
    } else {
      this.status = status;
    }

  }

  public GameState state() {
    if (gameIsOver()){
      return new GameState(status, NOBODY);
    }
    return new GameState(status, nextPlayer());
  }

  private boolean gameIsOver() {
    return status == DRAW || status == X_HAS_WON || status == O_HAS_WON;
  }

  private Player nextPlayer() {
    if (currentPlayer == null){
      return X;
    }else {
      return currentPlayer == X ? O : X;
    }
  }

  public Game play(Square toPlay) {
    if (gameIsOver()){
      return this;
    }else if (board.alreadyTaken(toPlay)){
      return new Game(SQUARE_ALREADY_PLAYED, board, currentPlayer);
    }else {
      return new Game(GAME_ON, board.take(toPlay, nextPlayer()), nextPlayer());
    }
  }
}
