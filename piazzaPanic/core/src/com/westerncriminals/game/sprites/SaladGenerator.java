package com.westerncriminals.game.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.World;
import com.westerncriminals.game.PiazzaPanic;
import com.westerncriminals.game.screens.PlayScreen;

public class SaladGenerator extends InteractiveTileObject{
	
	
	public SaladGenerator(PlayScreen screen, Rectangle bounds) {
		super(screen, bounds);
    	fixture.setUserData(this);
    	setCategoryFilter(PiazzaPanic.CATEGORY_STATION_TWO);
	}

	public void onInteraction(Fixture chefBody){
		if (chefBody.getUserData() == "chefOne") 
		{
			Gdx.app.log("Chef","This is here saladGen");
			if (chefOne.itemStack.contains("Lettuce", true )) {
				Gdx.app.log("DebugMsg", "No more lettuce for you");
			}
			else {
				chefOne.itemStack.add("Lettuce");
			}
			if (chefOne.itemStack.contains("Tomato", true )) {
				Gdx.app.log("DebugMsg", "No more Tomato for you");
			}
			else {
				chefOne.itemStack.add("Tomato");
			}
		}
		else 
		{
			if (chefTwo.itemStack.notEmpty())
				chefTwo.itemStack.pop();
		}
	}

}
