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


    public static OrthographicCamera gamecam;
    public static Viewport gamePort;
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
    Stage stage;
    public MainMenu(PiazzaPanic game) {
        this.game = game;
        stage = new Stage();

        gamecam = new OrthographicCamera();
        gamePort = new FitViewport(PiazzaPanic.V_WIDTH, PiazzaPanic.V_HEIGHT, gamecam);
        gamecam.position.set(gamePort.getWorldWidth() / 2, gamePort.getWorldHeight()/2,0);

        Gdx.input.setInputProcessor(stage);
        PlayButtonActive = new Texture("playbuttonactive.png");
        PlayButtonInactive = new Texture("playbuttoninactive.png");
        ExitButtonActive = new Texture("exitbuttonactive.png");
        ExitButtonInactive = new Texture("exitbuttoninactive.png");
        ExitButton = new Button(game, ExitButtonActive, ExitButtonInactive, 400,100, new GameExit());
        PlayButton = new Button(game, PlayButtonActive, PlayButtonInactive, 0,100, new GameStart());
    }
    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        update();
        Gdx.gl.glClearColor(100, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.begin();
        ExitButton.render();
        PlayButton.render();
        game.batch.end();
    }

    private void update() {
        gamecam.update();
    }

    @Override
    public void resize(int width, int height) {
        gamePort.update(width, height);
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
