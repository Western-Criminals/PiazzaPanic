package com.westerncriminals.game.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.World;
import com.westerncriminals.game.PiazzaPanic;
import com.westerncriminals.game.screens.PlayScreen;

public class AssemblyStation extends InteractiveTileObject{
	
	
	
	public AssemblyStation(PlayScreen screen, Rectangle bounds) {
		super(screen, bounds);
    	fixture.setUserData(this);
    	setCategoryFilter(PiazzaPanic.CATEGORY_STATION_FIVE);
    	
	}

	public void onInteraction(Fixture chefBody){
		if (chefBody.getUserData() == "chefOne") 
		{
			Gdx.app.log("Chef","This is here AssemblyStation");
			if (chefOne.itemStack.contains("Bun", true) && chefOne.itemStack.contains("Burger", true)) {
				chefOne.itemStack.removeValue("Bun", true);
				chefOne.itemStack.removeValue("Burger", true);
				chefOne.itemStack.add("Finished Burger");
			}
			if (chefOne.itemStack.contains("Chopped Tomato", true) && chefOne.itemStack.contains("Chopped Lettuce", true)) {
				chefOne.itemStack.removeValue("Chopped Tomato", true);
				chefOne.itemStack.removeValue("Chopped Lettuce", true);
				chefOne.itemStack.add("Finished Salad"); 
			}
		}
		else 
		{
			if (chefTwo.itemStack.notEmpty())
				chefTwo.itemStack.pop();
		}
	}
}
