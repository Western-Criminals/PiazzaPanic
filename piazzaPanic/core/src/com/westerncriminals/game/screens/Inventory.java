package com.westerncriminals.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.westerncriminals.game.PiazzaPanic;

import org.javatuples.Pair;

import java.util.ArrayList;
import java.util.List;

public class Inventory extends PiazzaPanic {
    private ShapeRenderer shapeRenderer;
    private PiazzaPanic game;
    private Table invTable = new Table();
    private int INV_WIDTH;
    private int INV_HEIGHT;
    private Pair<Integer, Integer> MIDDLE;
    private List<String> inv_lst;
    private List<Label> invLabels = new ArrayList<Label>();

    private void updateLabels(List<String> inv_lst) {
        this.invLabels.clear();
        for (String itemName : inv_lst) {
            Label itemLabel = new  Label(itemName, new Label.LabelStyle(new BitmapFont(), Color.PURPLE));
            this.invLabels.add(itemLabel);
        }
    }
    public Inventory(PiazzaPanic game, int w, int h, int xpos, int ypos, List<String> inv_lst) {
        this.game = game;
        INV_WIDTH = w;
        INV_HEIGHT = h;
        MIDDLE = new Pair<Integer, Integer> (MainMenu.width/2 + xpos, MainMenu.height/2 + ypos);
        this.inv_lst = inv_lst;
        updateLabels(inv_lst);
    }
    public void setInv(List<String> inv_lst) {
        this.inv_lst = inv_lst;
        updateLabels(inv_lst);
    }
    public void render(PiazzaPanic game) {
        this.game = game;
        this.game.batch.begin();
        if (Gdx.input.isKeyPressed(Input.Keys.I)) {
            shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
            shapeRenderer.setColor(0, 0, 0, 255);
            shapeRenderer.rect(MIDDLE.getValue0(), MIDDLE.getValue1(), INV_WIDTH, INV_HEIGHT);
            shapeRenderer.end();

            for (Label itemLabel : this.invLabels) {
                invTable.add(itemLabel).expandY();
            }
        }
        this.game.batch.end();
    }
    @Override
    public void dispose () {
        shapeRenderer.dispose();
    }
}
