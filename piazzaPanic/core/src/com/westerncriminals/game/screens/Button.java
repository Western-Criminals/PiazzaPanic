package com.westerncriminals.game.screens;



import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.westerncriminals.game.PiazzaPanic;
import com.westerncriminals.game.screens.ButtonResponse;
import com.westerncriminals.game.screens.MainMenu;

public class Button extends PiazzaPanic {
    private PiazzaPanic game;
    private int BUTTON_WIDTH;
    private int BUTTON_HEIGHT;
    private float BUTTON_Y;
    private float MIDDLE;
    private Texture t_active;
    private Texture t_inactive;
    private ButtonResponse response;
    public Button(PiazzaPanic g, int xpos, int ypos, Texture t_a, Texture t_i, ButtonResponse r) {
        game = g;
        BUTTON_WIDTH = 200;
        BUTTON_HEIGHT = 100;
        BUTTON_Y = MainMenu.gamecam.position.y/2 + ypos;
        MIDDLE = MainMenu.gamecam.position.x + xpos;
        t_active = t_a;
        t_inactive = t_i;
        response = r;
    }
    public Button(PiazzaPanic g, int w, int h, int xpos, int ypos, Texture t_a, Texture t_i, ButtonResponse r) {
        game = g;
        BUTTON_WIDTH = w;
        BUTTON_HEIGHT = h;
        BUTTON_Y = MainMenu.gamecam.position.y/2 + ypos;
        MIDDLE = MainMenu.gamecam.position.x + xpos;
        t_active = t_a;
        t_inactive = t_i;
        response = r;
    }
    public void render(PiazzaPanic game) {
        this.game=game;
        game.batch.begin();
        if(Gdx.input.getX() > MIDDLE - BUTTON_WIDTH/2 && Gdx.input.getX() < MIDDLE + BUTTON_WIDTH/2 && Gdx.input.getY() < MainMenu.gamecam.position.y*2 - BUTTON_Y && Gdx.input.getY() > MainMenu.gamecam.position.y*2 - BUTTON_Y - BUTTON_HEIGHT){
            game.batch.draw(t_active,MIDDLE - BUTTON_WIDTH/2,BUTTON_Y,BUTTON_WIDTH,BUTTON_HEIGHT);
            if(Gdx.input.isTouched()){
                response.run(game);
            }
        }
        else{
            game.batch.draw(t_inactive,MIDDLE - BUTTON_WIDTH/2,BUTTON_Y,BUTTON_WIDTH,BUTTON_HEIGHT);
        }
        game.batch.end();
    }
}
