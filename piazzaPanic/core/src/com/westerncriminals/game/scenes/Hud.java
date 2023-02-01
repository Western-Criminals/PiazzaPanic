package com.westerncriminals.game.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import java.util.Random;
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
import com.westerncriminals.game.screens.PlayScreen;
import com.westerncriminals.game.sprites.Customer;

public class Hud implements Disposable{
	public Stage stage;
	private Viewport viewport;
	private Customer customer;
	
	private Integer scoreCount;
	private Integer bCount;
	private Integer saladCount;
	private float timeCount;
	private Integer worldTime;
	Integer first;
	Integer numOrders;
	Random rand = new Random();

	Label timerLabel;
	Label scoreLabel;
	Label bLabelNum;
	Label burgLabel;
	Label timeLabel;
	Label saladLabel;
	Label sLabel;
	
	boolean customerFlag;
	
	public Hud(SpriteBatch sb, Customer customer, Boolean customerFlag) {
		first = 5;
		numOrders = 0;
		scoreCount = 0;
		bCount = 0;
		saladCount = 0;
		worldTime = 0;
		timeCount = 0;
		this.customer = customer;
		this.customerFlag = false;
		
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
		
		scoreLabel = new Label(String.format("%06d",scoreCount), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
		timerLabel = new Label(String.format("%06d",worldTime), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
		bLabelNum = new Label(String.format("%01d", bCount), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
		sLabel = new Label(String.format("%01d", saladCount), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
		
		table.add(timerLabel).expandX();
		table.add(bLabelNum).expandX();
		table.add(sLabel);
		
		stage.addActor(table);
		bLabelNum.setText(String.format("%01d", (bCount++)));
		sLabel.setText(String.format("%01d", (saladCount++)));
	}
	
	public void update(float dt) {
		timeCount += dt;
		if(timeCount >= 1) {
			worldTime++;
			timerLabel.setText(String.format("%06d",(worldTime)));
			timeCount = 0;
			if (worldTime  == first) {
				if (numOrders < 6) {
					addOrder();
					PlayScreen.customerFlag = true;
				}
			}
		}
		
	}
	
	public void addOrder() {
		float percentos =  rand.nextFloat();
		first += 5;
		numOrders++;
		if (percentos < 0.5 && numOrders < 6) {
			bLabelNum.setText(String.format("%01d", (bCount++)));
		}
		else if (percentos > 0.5 && numOrders < 6) {
			sLabel.setText(String.format("%01d", (saladCount++)));
		}
		else
			Gdx.app.log("Lol", "limit reached");
		
	}
	

	@Override
	public void dispose() {
		stage.dispose();
		
		
	}

}
