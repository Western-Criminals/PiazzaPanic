package com.westerncriminals.game.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.westerncriminals.game.PiazzaPanic;
import com.westerncriminals.game.screens.PlayScreen;


public class BurgerFryer extends InteractiveTileObject{
	final short CATEGORY_STATION_ONE = 0x0004;
	public Chef chefOne;
	public Chef chefTwo;
	String example;

	public BurgerFryer(World world, TiledMap map, Rectangle bounds, Chef chefOne, Chef chefTwo) {
		super(world, map, bounds);
    	fixture.setUserData(this);
    	this.chefOne = chefOne;
    	this.chefTwo = chefTwo;
	}

	public void onInteraction(Fixture chefBody) {
		if (chefBody.getUserData() == "chefOne") 
		{
			Gdx.app.log("Chef","This is here fryer");
			if (chefOne.itemStack.notEmpty())
				example = (String) chefOne.itemStack.pop();
				Gdx.app.log("Tester", example);
		}
		else 
		{
			if (chefTwo.itemStack.notEmpty())
				chefTwo.itemStack.pop();
		}
	}
	

}
