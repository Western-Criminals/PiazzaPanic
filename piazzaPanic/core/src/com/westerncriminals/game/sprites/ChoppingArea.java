package com.westerncriminals.game.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.World;
import com.westerncriminals.game.PiazzaPanic;
import com.westerncriminals.game.screens.PlayScreen;

public class ChoppingArea extends InteractiveTileObject
{


	public ChoppingArea(PlayScreen screen, Rectangle bounds)
	{
		super(screen, bounds);
		fixture.setUserData(this);
		setCategoryFilter(PiazzaPanic.CATEGORY_STATION_SIX);
	}

	@Override
	public void onInteraction(Fixture chefBody){
		if (chefBody.getUserData() == "chefOne")
		{
			Gdx.app.log("Chef","This is here chop1");
			if (chefOne.itemStack.notEmpty())
				if (chefOne.itemStack.contains("Lettuce", true)) {
					chefOne.itemStack.removeValue("Lettuce", true);
					chefOne.itemStack.add("Chopped Lettuce");
				}
			if (chefOne.itemStack.contains("Tomato", true)) {
				chefOne.itemStack.removeValue("Tomato", true);
				chefOne.itemStack.add("Chopped Tomato");
			}
		}
		else
		{
			if (chefTwo.itemStack.notEmpty())
				chefTwo.itemStack.pop();
		}
	}


}