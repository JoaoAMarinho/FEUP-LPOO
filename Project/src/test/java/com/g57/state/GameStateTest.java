package com.g57.state;

import com.g57.Game;
import com.g57.model.element.button.Button;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

class GameStateTest {
    private GameState gameState;
    private Game game;
    private List<Button> buttons;

    @BeforeEach
    void setUp() {
        this.game = Mockito.mock(Game.class);
        this.gameState = Mockito.mock(GameState.class);
        this.buttons = Arrays.asList(new Button(null, null, Arrays.asList("Color")));
        Mockito.when(gameState.getGame()).thenReturn(game);
        Mockito.when(gameState.getButtons()).thenReturn(buttons);
    }

    @Test
    void testChangeState() {

        GameState newState = new GameState(game, buttons) {
            @Override
            public void start() {}

            @Override
            public void step(Game game, long time) throws IOException {}
        };
        newState.changeState(gameState);

        Mockito.verify(game,Mockito.times(1)).setGameState(gameState);
    }

    @Test
    void testGetGame() {
        Game newGame = gameState.getGame();
        Assertions.assertEquals(newGame, game);
    }

    @Test
    void testGetButtons() {
        List<Button> newButtons = gameState.getButtons();
        Assertions.assertEquals(newButtons.size(), 1);
        Assertions.assertEquals(newButtons, buttons);
    }
}