package com.westerncriminals.game.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.World;

public class ChoppingArea extends InteractiveTileObject
{
	final short CATEGORY_STATION_TWO = 0x0008;
	public Chef chefOne;
	public Chef chefTwo;
	String example;

	public ChoppingArea(World world, TiledMap map, Rectangle bounds, Chef chefOne, Chef chefTwo) 
	{
		super(world, map, bounds);
    	fixture.setUserData(this);
    	this.chefOne = chefOne;
    	this.chefTwo = chefTwo;
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
