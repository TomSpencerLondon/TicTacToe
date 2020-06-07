package tictactoe;

import static tictactoe.Square.CENTRE_LEFT;
import static tictactoe.Square.CENTRE_MIDDLE;
import static tictactoe.Square.CENTRE_RIGHT;
import static tictactoe.Square.TOP_LEFT;
import static tictactoe.Square.TOP_MIDDLE;
import static tictactoe.Square.TOP_RIGHT;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

public class Board {

  private final Set<Square> takenSquares;

  public Board(){
    takenSquares = new HashSet<>();
  }

  private Board(Set<Square> takenSquares) {
    this.takenSquares = takenSquares;
  }

  public boolean alreadyTaken(Square toPlay) {
    return takenSquares.contains(toPlay);
  }

  public Board take(Square toPlay) {
    var newBoard = new HashSet<Square>(takenSquares);
    newBoard.add(toPlay);
    return new Board(newBoard);
  }

  public boolean isFull() {
    return takenSquares.size() == 9;
  }

  public boolean hasWon() {
    var winningCombos = Stream.of(
        Stream.of(TOP_LEFT, TOP_MIDDLE, TOP_RIGHT),
        Stream.of(CENTRE_LEFT, CENTRE_MIDDLE, CENTRE_RIGHT)
    );
    return winningCombos.anyMatch(combo -> combo.allMatch(square -> takenSquares.contains(square)));
  }
}
