package com.westerncriminals.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.westerncriminals.game.PiazzaPanic;
import com.badlogic.gdx.Game;

public class MainMenu implements Screen {

    private static final int BUTTON_WIDTH = 200;
    private static final int BUTTON_HEIGHT = 100;
    private static final int BUTTON_Y = 100;
    private static final int MIDDLE = PiazzaPanic.V_WIDTH/2 - BUTTON_WIDTH/2;
    PiazzaPanic game;
    Texture PlayButtonActive;
    Texture PlayButtonInactive;
    Texture ExitButtonActive;
    Texture ExitButtonInactive;
    public MainMenu(PiazzaPanic game){
        this.game = game;
        PlayButtonActive = new Texture("playbuttonactive.png");
        PlayButtonInactive = new Texture("playbuttoninactive.png");
        ExitButtonActive = new Texture("exitbuttonactive.png");
        ExitButtonInactive = new Texture("exitbuttoninactive.png");
        Button PlayButton = new Button(game, PlayButtonActive, PlayButtonInactive);
        Button ExitButton = new Button(game, ExitButtonActive, ExitButtonInactive);
        PlayButton.render();
        ExitButton.render();
    }
    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
