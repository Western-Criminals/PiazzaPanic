package com.westerncriminals.game.scenes;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.westerncriminals.game.PiazzaPanic;

import java.util.ArrayList;
import java.util.List;

public class Inventory extends PiazzaPanic {
    private ShapeRenderer shapeRenderer;
    private PiazzaPanic game;
    public Stage stage;
    private Viewport viewport;
    private Table invTable = new Table();
    private int INV_WIDTH;
    private int INV_HEIGHT;
    private int midX;
    private int midY;
    private List<String> inv_lst;
    private List<Label> invLabels = new ArrayList<Label>();
    private static boolean visible;

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
        midX = PiazzaPanic.V_WIDTH/2 + xpos;
        midY = PiazzaPanic.V_HEIGHT/2 + ypos;
        this.inv_lst = inv_lst;
        updateLabels(inv_lst);

        viewport = new FitViewport(PiazzaPanic.V_WIDTH, PiazzaPanic.V_HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport, this.game.batch);
        invTable.top();
        invTable.setFillParent(true);
        for (Label itemLabel : this.invLabels) {
            invTable.add(itemLabel).expandY();
            invTable.row();
        }
        stage.addActor(invTable);
    }
    public void setInv(List<String> inv_lst) {
        this.inv_lst = inv_lst;
        updateLabels(inv_lst);
    }
    public void setVisibility(boolean visible) {
        this.visible = visible;
    }
    public boolean getVisibility() {
        return visible;
    }

    public void render(PiazzaPanic game) {
        this.game = game; 
        this.game.batch.begin();

        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(0, 0, 0, 255);
        shapeRenderer.rect(midX, midY, INV_WIDTH, INV_HEIGHT);
        shapeRenderer.end();
        this.game.batch.end();
    }
    @Override
    public void dispose () {
        shapeRenderer.dispose();
        stage.dispose();
    }
}
