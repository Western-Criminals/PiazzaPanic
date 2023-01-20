package com.westerncriminals.game.screens;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Game;
import com.westerncriminals.game.PiazzaPanic;

public interface ButtonResponse {
        public void execute();
    }
class GameStart implements ButtonResponse {
    private static PiazzaPanic game;
    public void execute() {
        game.setScreen(new PlayScreen(game));
    }
}
class GameExit implements ButtonResponse {
    public void execute() {
        Gdx.app.exit();
    }
}