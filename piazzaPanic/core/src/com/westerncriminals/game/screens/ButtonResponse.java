package com.westerncriminals.game.screens;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Game;
import com.westerncriminals.game.PiazzaPanic;

public interface ButtonResponse {
    public void run(PiazzaPanic game);
    }
class GameStart implements ButtonResponse {
    PiazzaPanic game;
    public void run(PiazzaPanic game) {
        this.game = game;
        game.setScreen(new PlayScreen(game));
    }
}
class GameExit implements ButtonResponse {
    public void run(PiazzaPanic game) {
        Gdx.app.exit();
    }
}