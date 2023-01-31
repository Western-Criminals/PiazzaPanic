package com.westerncriminals.game.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.World;

public class Pantry extends InteractiveTileObject{
	final short CATEGORY_STATION_TWO = 0x0010;
	public Chef chefOne;
	public Chef chefTwo;
	String example;

	public Pantry(World world, TiledMap map, Rectangle bounds, Chef chefOne, Chef chefTwo) {
		super(world, map, bounds);
    	fixture.setUserData(this);
    	this.chefOne = chefOne;
    	this.chefTwo = chefTwo;
	}

	@Override
	public void onInteraction(Fixture chefBody) {
		if (chefBody.getUserData() == "chefOne") 
		{
			Gdx.app.log("Chef","This is here sink");
			//if (chefOne.itemStack.notEmpty())
				//example = (String) chefOne.itemStack.pop();
				//Gdx.app.log("Tester", example);
			
			chefOne.itemStack.add("Patty");
		}
		else 
		{
			if (chefTwo.itemStack.notEmpty())
				chefTwo.itemStack.pop();
		}
	}
	

}