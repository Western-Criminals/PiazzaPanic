package com.westerncriminals.game.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.westerncriminals.game.PiazzaPanic;
import com.westerncriminals.game.screens.PlayScreen;

public class ServingTile extends InteractiveTileObject{
	public ServingTile(PlayScreen screen, Rectangle bounds) {
		super(screen, bounds);
    	fixture.setUserData(this);
    	setCategoryFilter(PiazzaPanic.CATEGORY_STATION_SEVEN);
    	
	}

	public void onInteraction(Fixture chefBody){
		if (chefBody.getUserData() == "chefOne") 
		{
			Gdx.app.log("Chef","This is here SERVING STATION");
			if (chefOne.itemStack.contains("Finished Burger", true)) {
				chefOne.itemStack.removeValue("Finished Burger", true);
				if (hud.bCount > 0);
					hud.bCount--;
			}
			if (chefOne.itemStack.contains("Finished Salad", true)) {
				chefOne.itemStack.removeValue("Finished Salad", true);
				if (hud.saladCount > 0);
					hud.saladCount--;
			}
		}
		else 
		{
			if (chefTwo.itemStack.notEmpty())
				chefTwo.itemStack.pop();
		}
	}
}
