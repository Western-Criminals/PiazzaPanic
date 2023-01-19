package com.westerncriminals.game.scenes;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.westerncriminals.game.PiazzaPanic;

public class Hud implements Disposable{
	public Stage stage;
	private Viewport viewport;
	
	private Integer scoreCount;
	
	Label scoreLabel;
	
	public Hud(SpriteBatch sb) {
		scoreCount = 0;
		
		viewport = new FitViewport(PiazzaPanic.V_WIDTH, PiazzaPanic.V_HEIGHT, new OrthographicCamera());
		stage = new Stage(viewport, sb);
		
		Table table = new Table();
		table.bottom();
		table.setFillParent(true);
		
		scoreLabel = new Label(String.format("%06d",scoreCount), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
		
		table.add(scoreLabel).padTop(10);
		
		stage.addActor(table);
		
		
	}

	@Override
	public void dispose() {
		stage.dispose();
		
		
	}
}
