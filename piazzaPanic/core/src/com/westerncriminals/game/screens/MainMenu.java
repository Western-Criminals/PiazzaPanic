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
    }
    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(100, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.begin();
        if(Gdx.input.getX() > MIDDLE - BUTTON_WIDTH*2 && Gdx.input.getX() < MIDDLE - BUTTON_WIDTH && Gdx.input.getY() < PiazzaPanic.V_HEIGHT - BUTTON_Y && Gdx.input.getY() > PiazzaPanic.V_HEIGHT - BUTTON_Y*2 ){
            game.batch.draw(PlayButtonActive,MIDDLE - BUTTON_WIDTH*2,BUTTON_Y,BUTTON_WIDTH,BUTTON_HEIGHT );
            if(Gdx.input.isTouched()){
                game.setScreen(new PlayScreen(game));
            }
        }
        else{
            game.batch.draw(PlayButtonInactive,MIDDLE - BUTTON_WIDTH*2,BUTTON_Y,BUTTON_WIDTH,BUTTON_HEIGHT );
        }
        if(Gdx.input.getX() > MIDDLE + BUTTON_WIDTH*2 && Gdx.input.getX() < MIDDLE + BUTTON_WIDTH*3 && Gdx.input.getY() < PiazzaPanic.V_HEIGHT - BUTTON_Y && Gdx.input.getY() > PiazzaPanic.V_HEIGHT - BUTTON_Y*2 ){
            game.batch.draw(ExitButtonActive,MIDDLE + BUTTON_WIDTH*2,BUTTON_Y,BUTTON_WIDTH,BUTTON_HEIGHT );
            if(Gdx.input.isTouched()){
                Gdx.app.exit();
            }
        }
        else{
            game.batch.draw(ExitButtonInactive,MIDDLE + BUTTON_WIDTH*2,BUTTON_Y,BUTTON_WIDTH,BUTTON_HEIGHT );
        }

        game.batch.end();
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
