package com.westerncriminals.game.screens;
import com.badlogic.gdx.Gdx;

public interface ButtonResponse {
        public void execute();
    }
class GameStart implements ButtonResponse {
    public void execute() {

        //
    }
}
class GameExit implements ButtonResponse {
    public void execute() {
        Gdx.app.exit();
    }
}