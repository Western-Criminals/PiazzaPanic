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
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.westerncriminals.game.PiazzaPanic;
import com.westerncriminals.game.screens.PlayScreen;
import com.westerncriminals.game.sprites.Chef;
import com.westerncriminals.game.sprites.Customer;

public class InventoryChris implements Disposable{
	public Stage stage;
	private Viewport viewport;
	private List<Label> invLabels;
	Boolean pattyAdded;
	Boolean bunAdded;
	Boolean burgerAdded;
	Boolean lettuceAdded;
	Boolean tomatoAdded;
	Boolean choppedLetAdded;
	Boolean choppedTAdded;
	Boolean saladAdded;
	Integer numTimes;
	
	Table table;;

	Label ingrediantTextLabel;
	Label ingrediantLabel;
	Label pattyLabel;
	Label bunLabel;
	Label burgerLabel;
	Label blankLabel;
	Label lettuceLabel;
	Label tomatoLabel;
	Label choppedLetLabel;
	Label choppedTLabel;
	Label saladLabel;
	
	
	
	private Chef chefOne;
	private Chef chefTwo;
	
	
	public InventoryChris(SpriteBatch sb, PlayScreen screen) {
		this.chefOne = screen.getChefOne();
		this.chefTwo = screen.getChefTwo();
		invLabels = new ArrayList<Label>();
		table = new Table();
		pattyAdded = false;
		bunAdded = false;
		burgerAdded = false;
		lettuceAdded = false;
		tomatoAdded = false;
		choppedLetAdded = false;
		choppedTAdded = false;
		saladAdded = false;
		numTimes = 0;
		
		viewport = new FitViewport(PiazzaPanic.V_WIDTH, PiazzaPanic.V_HEIGHT, new OrthographicCamera());
		stage = new Stage(viewport, sb);
		
		
		
		table.top();
		table.setFillParent(true);
		
		ingrediantTextLabel = new Label("INGREDIANTS", new Label.LabelStyle(new BitmapFont(), Color.PURPLE));
		blankLabel = new Label("", new Label.LabelStyle(new BitmapFont(), Color.PURPLE));
		table.add(ingrediantTextLabel).expandX();
		table.add(blankLabel).expandX();
		table.add(blankLabel).expandX();
		table.row();
			
		
		stage.addActor(table);
	}
	
	public void update(float dt) {
		if (chefOne.itemStack.contains("Patty", true) && pattyAdded == false) {
			pattyLabel = new Label("Patty", new Label.LabelStyle(new BitmapFont(), Color.PURPLE));
			table.add(pattyLabel).expandX();
			pattyAdded = true;
			table.row();
		}
		if (chefOne.itemStack.contains("Bun", true) && bunAdded == false) {
			bunLabel = new Label("Bun", new Label.LabelStyle(new BitmapFont(), Color.PURPLE));
			table.add(bunLabel).expandX();
			bunAdded = true;
			table.row();
		}
		if (chefOne.itemStack.contains("Burger", true) && burgerAdded == false) {
			burgerLabel = new Label("Burger", new Label.LabelStyle(new BitmapFont(), Color.PURPLE));
			table.add(burgerLabel).expandX();
			burgerAdded = true;
			table.removeActor(pattyLabel);
			table.row();
		}
		if (chefOne.itemStack.contains("Lettuce", true) && lettuceAdded == false) {
			lettuceLabel = new Label("Lettuce", new Label.LabelStyle(new BitmapFont(), Color.PURPLE));
			table.add(lettuceLabel).expandX();
			lettuceAdded = true;
			table.row();
		}
		if (chefOne.itemStack.contains("Tomato", true) && lettuceAdded == false) {
			tomatoLabel = new Label("Tomato", new Label.LabelStyle(new BitmapFont(), Color.PURPLE));
			table.add(tomatoLabel).expandX();
			tomatoAdded = true;
			table.row();
		}
		if (chefOne.itemStack.contains("Chopped Lettuce", true) && choppedLetAdded == false) {
			choppedLetLabel = new Label("Chopped Lettuce", new Label.LabelStyle(new BitmapFont(), Color.PURPLE));
			table.add(choppedLetLabel).expandX();
			choppedLetAdded = true;
			table.row();
		}
	}

	

	@Override
	public void dispose() {
		stage.dispose();
		
	}

}

