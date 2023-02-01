package com.westerncriminals.game.scenes;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.westerncriminals.game.PiazzaPanic;
import com.westerncriminals.game.screens.PlayScreen;
import com.westerncriminals.game.sprites.Chef;
import com.westerncriminals.game.sprites.Customer;

public class InventoryChris {
	public Stage stage;
	private Viewport viewport;
	private List<Label> invLabels = new ArrayList<Label>();
	


	Label ingrediantTextLabel;
	Label ingrediantLabel;
	
	
	private Chef chefOne;
	private Chef chefTwo;
	
	
	public InventoryChris(SpriteBatch sb, PlayScreen screen) {
		this.chefOne = screen.getChefOne();
		this.chefTwo = screen.getChefTwo();

		viewport = new FitViewport(PiazzaPanic.V_WIDTH, PiazzaPanic.V_HEIGHT, new OrthographicCamera());
		stage = new Stage(viewport, sb);
		
		
		Table table = new Table();
		table.bottom();
		table.setFillParent(true);
		
		ingrediantTextLabel = new Label("Ingrediants", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
		ingrediantLabel = new Label(, new Label.LabelStyle(new BitmapFont(), Color.WHITE));
		table.add(ingrediantLabel).expandX();
	
		
		stage.addActor(table);
	}
	
	public void update(float dt) {
		timeCount += dt;
		if(timeCount >= 1) {
		
		}
		bLabelNum.setText(String.format("%01d", (bCount)));
		sLabel.setText(String.format("%01d", (saladCount)));
		if (bCount == 0 && saladCount == 0 && numOrders > 4) {
			Gdx.app.exit();
			Gdx.app.log("Game state", "Gameover, you took this many seconds to complete the game");
			Gdx.app.log("Game state", String.format("%03d", worldTime));
		}
	}
	
	public void addOrder() {
		float percentos =  rand.nextFloat();
		first += 5;
		numOrders++;
		if (percentos < 0.5 && numOrders < 6) {
			bCount++;
			bLabelNum.setText(String.format("%01d", (bCount)));
		}
		else if (percentos > 0.5 && numOrders < 6) {
			saladCount++;
			sLabel.setText(String.format("%01d", (saladCount)));
		}
		else
			Gdx.app.log("Lol", "limit reached");
		
	}
	

	@Override
	public void dispose() {
		stage.dispose();
		
		
	}

}

