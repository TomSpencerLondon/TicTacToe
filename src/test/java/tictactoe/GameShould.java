package tictactoe;

import static org.fest.assertions.Assertions.assertThat;
import static tictactoe.Player.X;
import static tictactoe.Status.GAME_ON;

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
}
