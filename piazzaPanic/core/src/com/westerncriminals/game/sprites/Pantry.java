package com.westerncriminals.game.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.World;
import com.westerncriminals.game.PiazzaPanic;
import com.westerncriminals.game.screens.PlayScreen;

public class Pantry extends InteractiveTileObject{

	String example;

	public Pantry(PlayScreen screen, Rectangle bounds) {
		super(screen, bounds);
		fixture.setUserData(this);
		setCategoryFilter(PiazzaPanic.CATEGORY_STATION_THREE);
	}

	@Override
	public void onInteraction(Fixture chefBody) {
		if (chefBody.getUserData() == "chefOne")
		{
			if (chefOne.itemStack.contains("Patty", true) || chefOne.itemStack.contains("Burger", true))
				Gdx.app.log("No", "Already contains patty or burger");
			else
				chefOne.itemStack.insert(0, "Patty");
		}
		else
		{
			if (chefTwo.itemStack.notEmpty())
				chefTwo.itemStack.pop();
		}
	}
}