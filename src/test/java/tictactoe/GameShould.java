package tictactoe;

import static java.util.Arrays.stream;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static tictactoe.Player.NOBODY;
import static tictactoe.Player.X;
import static tictactoe.Square.BOTTOM_LEFT;
import static tictactoe.Square.BOTTOM_MIDDLE;
import static tictactoe.Square.BOTTOM_RIGHT;
import static tictactoe.Square.CENTRE_LEFT;
import static tictactoe.Square.CENTRE_MIDDLE;
import static tictactoe.Square.CENTRE_RIGHT;
import static tictactoe.Square.TOP_LEFT;
import static tictactoe.Square.TOP_MIDDLE;
import static tictactoe.Square.TOP_RIGHT;
import static tictactoe.Status.DRAW;
import static tictactoe.Status.GAME_ON;
import static tictactoe.Status.SQUARE_ALREADY_PLAYED;
import static tictactoe.Status.X_HAS_WON;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class GameShould {

  @Test
  public void wait_for_x_to_play_first() {
    var game = new Game();

    assertEquals(game.state(), new GameState(GAME_ON, X));
  }

  @Test
  public void alternate_the_players() {
    var game = new Game();
    game = game.play(TOP_LEFT);
    game = game.play(TOP_MIDDLE);

    assertEquals(game.state(), new GameState(GAME_ON, X));
  }

  @Test
  public void game_should_not_allow_a_square_to_be_played_twice() {
    var game = new Game();
    game = play(TOP_LEFT, TOP_MIDDLE, TOP_LEFT);

    assertEquals(game.state(), new GameState(SQUARE_ALREADY_PLAYED, X));
  }

  private Game play(Square... squares) {
    return stream(squares)
        .reduce(new Game(), Game::play, (a, b) -> null);
  }

  // X O X
  // O X X
  // O X O
  @Test
  public void recognise_a_draw() {
    var game = play(
        TOP_LEFT,
        Square.TOP_MIDDLE,
        TOP_RIGHT,
        CENTRE_LEFT,
        CENTRE_MIDDLE,
        BOTTOM_LEFT,
        CENTRE_RIGHT,
        BOTTOM_MIDDLE,
        BOTTOM_RIGHT);

    assertEquals(game.state(), new GameState(DRAW, NOBODY));
  }

  @ParameterizedTest
  @CsvSource({
      "TOP_LEFT,CENTRE_LEFT,TOP_MIDDLE,CENTRE_MIDDLE,TOP_RIGHT"
  })
  public void recognise_when_x_has_won(Square s1, Square s2, Square s3, Square s4, Square s5) {
    var game = play(s1, s2, s3, s4, s5);

    assertEquals(game.state(), new GameState(X_HAS_WON, NOBODY));
  }
}
