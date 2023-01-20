package com.westerncriminals.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.westerncriminals.game.PiazzaPanic;
import com.westerncriminals.game.screens.ButtonResponse;

public class Button extends PiazzaPanic {
    private static PiazzaPanic game;
    private static int BUTTON_WIDTH;
    private static int BUTTON_HEIGHT;
    private static int BUTTON_Y;
    private static int MIDDLE;
    private static Texture t_active;
    private static Texture t_inactive;
    private static ButtonResponse response;
    public Button(PiazzaPanic g, Texture t_a, Texture t_i, ButtonResponse r) {
        game = g;
        BUTTON_WIDTH = 200;
        BUTTON_HEIGHT = 100;
        BUTTON_Y = 100;
        MIDDLE = PiazzaPanic.V_WIDTH/2 - BUTTON_WIDTH/2;
        t_active = t_a;
        t_inactive = t_i;
        response = r;
    }
    public Button(PiazzaPanic g, int w, int h, int y, Texture t_a, Texture t_i, ButtonResponse r) {
        game = g;
        BUTTON_WIDTH = w;
        BUTTON_HEIGHT = h;
        BUTTON_Y = y;
        MIDDLE = PiazzaPanic.V_WIDTH/2 - BUTTON_WIDTH/2;
        t_active = t_a;
        t_inactive = t_i;
        response = r;
    }
    @Override
    public void render() {
        Gdx.gl.glClearColor(100, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.begin();
        if(Gdx.input.getX() > MIDDLE - BUTTON_WIDTH*2 && Gdx.input.getX() < MIDDLE - BUTTON_WIDTH && Gdx.input.getY() < PiazzaPanic.V_HEIGHT - BUTTON_Y && Gdx.input.getY() > PiazzaPanic.V_HEIGHT - BUTTON_Y*2 ){
            game.batch.draw(t_active,MIDDLE - BUTTON_WIDTH*2,BUTTON_Y,BUTTON_WIDTH,BUTTON_HEIGHT );
            if(Gdx.input.isTouched()){
                response.execute();
            }
        }
        else{
            game.batch.draw(t_inactive,MIDDLE - BUTTON_WIDTH*2,BUTTON_Y,BUTTON_WIDTH,BUTTON_HEIGHT );
        }
        game.batch.end();
    }
}
