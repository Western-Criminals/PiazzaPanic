package com.westerncriminals.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.westerncriminals.game.PiazzaPanic;

import org.javatuples.Pair;

import java.util.List;

public class Inventory extends PiazzaPanic {
    private PiazzaPanic game;
    private int INV_WIDTH;
    private int INV_HEIGHT;
    private Pair<Integer, Integer> MIDDLE;
    private List<String> inv_lst;

    public Inventory(PiazzaPanic game, int w, int h, int xpos, int ypos, List<String> inv_lst) {
        this.game = game;
        INV_WIDTH = w;
        INV_HEIGHT = h;
        MIDDLE = new Pair<Integer, Integer> (MainMenu.width/2 + xpos, MainMenu.height/2 + ypos);
        this.inv_lst = inv_lst;
    }
    public void setInv(List<String> inv_lst) {
        this.inv_lst = inv_lst;
    }
    public void render(PiazzaPanic game) {
        this.game = game;
        this.game.batch.begin();
        this.game.batch.end();
    }
}