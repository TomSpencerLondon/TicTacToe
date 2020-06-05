package tictactoe;

import static java.util.Arrays.stream;
import static org.fest.assertions.Assertions.assertThat;
import static tictactoe.Player.O;
import static tictactoe.Player.X;
import static tictactoe.Square.TOP_LEFT;
import static tictactoe.Square.TOP_MIDDLE;
import static tictactoe.Status.GAME_ON;
import static tictactoe.Status.SQUARE_ALREADY_PLAYED;

import java.util.Arrays;
import junitparams.JUnitParamsRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(JUnitParamsRunner.class)
public class GameShould {

  @Test
  public void wait_for_x_to_play_first() {
    var game = new Game();

    assertThat(game.state()).isEqualTo(
        new GameState(GAME_ON, X)
    );
  }

 @Test
  public void alternate_the_players() {
    var game = new Game();
    game = game.play(TOP_LEFT);
    game = game.play(TOP_MIDDLE);

    assertThat(game.state()).isEqualTo(
        new GameState(GAME_ON, X)
    );
  }

  @Test
  public void game_should_not_allow_a_square_to_be_played_twice() {
    var game = new Game();
    game = play(TOP_LEFT, TOP_MIDDLE, TOP_LEFT);

    assertThat(game.state()).isEqualTo(
        new GameState(SQUARE_ALREADY_PLAYED, X)
    );
  }

  private Game play(Square ...squares){
    return stream(squares)
        .reduce(new Game(), Game::play, (a, b) -> null);
  }

  // TODO: 38:05 https://www.youtube.com/watch?v=bRIPiF68NUM&t=695s
  // X O X
  // O X X
  // O X O
  @Test
  public void recognise_a_draw() {
    var game = play(
      TOP_LEFT,
      TOP_MIDDLE,
        TOP_RIGHT,
        CENTRE_LEFT,
        CENTRE_MIDDLE,
        BOTTOM_LEFT,
        CENTRE_RIGHT,
        BOTTOM_MIDDLE,
        BOTTOM_RIGHT);
  }
}
