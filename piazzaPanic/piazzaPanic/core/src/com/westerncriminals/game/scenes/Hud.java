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
import com.westerncriminals.game.sprites.Chef;

public class Hud implements Disposable{
	public Stage stage;
	private Viewport viewport;
	
	private float timeCount;
	private Integer bCount;
	private Integer saladCount;
	private Integer worldTime;

	
	Label timerLabel;
	Label bLabelNum;
	Label burgLabel;
	Label timeLabel;
	Label saladLabel;
	Label sLabel;
	Label ingrediantLabel;
	
	public Hud(SpriteBatch sb) {
		timeCount = 0;
		bCount = 0;
		saladCount = 0;
		worldTime = 0;
		
		viewport = new FitViewport(PiazzaPanic.V_WIDTH, PiazzaPanic.V_HEIGHT, new OrthographicCamera());
		stage = new Stage(viewport, sb);
		
		Table table = new Table();
		table.bottom();
		table.setFillParent(true);
		
		burgLabel = new Label("Burgers", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
		timeLabel = new Label("TIME", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
		saladLabel = new Label("Salads", new Label.LabelStyle(new BitmapFont(), Color.WHITE));

		
		table.add(timeLabel).expandX();
		table.add(burgLabel).expandX();
		table.add(saladLabel).expandX();
		
		
		table.row();
		
		timerLabel = new Label(String.format("%06d",worldTime), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
		bLabelNum = new Label(String.format("%01d", bCount), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
		sLabel = new Label(String.format("%01d", saladCount), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
		ingrediantLabel = new Label(String.format("%01d", 0), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
		
		table.add(timerLabel).expandX();//.padTop(10);
		table.add(bLabelNum).expandX();//.padTop(10);
		table.add(sLabel);
		
		table.row();
		
		table.add(ingrediantLabel);
		
		
		stage.addActor(table);
		
	}
	
	public void update(float dt) {
		timeCount += dt;
		if(timeCount >= 1) {
			worldTime++;
			timerLabel.setText(String.format("%06d",(worldTime)));
			timeCount = 0;
		}
	}

	@Override
	public void dispose() {
		stage.dispose();
		
		
	}

}
