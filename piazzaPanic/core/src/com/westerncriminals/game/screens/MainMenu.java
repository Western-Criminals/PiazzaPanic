package com.westerncriminals.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.westerncriminals.game.PiazzaPanic;
import com.badlogic.gdx.Game;
import com.westerncriminals.game.screens.ButtonResponse;

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
    Button PlayButton;
    Button ExitButton;
    public static int width;
    public static int height;
    public MainMenu(PiazzaPanic game) {
        this.game = game;
        width = 1200;
        height = 900;
        PlayButtonActive = new Texture("playbuttonactive.png");
        PlayButtonInactive = new Texture("playbuttoninactive.png");
        ExitButtonActive = new Texture("exitbuttonactive.png");
        ExitButtonInactive = new Texture("exitbuttoninactive.png");
        ExitButton = new Button(game, 200, -200, ExitButtonActive, ExitButtonInactive, new GameExit());
        PlayButton = new Button(game, -200,-200, PlayButtonActive, PlayButtonInactive,  new GameStart());
    }
    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        update();
        Gdx.gl.glClearColor(100, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        //game.batch.begin();
        ExitButton.render(game);
        PlayButton.render(game);
        //game.batch.end();
    }

    private void update() {

    }

    @Override
    public void resize(int w, int h) {
        this.width = w;
        this.height = h;
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
