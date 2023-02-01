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

	String example;
	Integer fryTimer;
	Boolean cooking;
	Integer timerCount;

	public BurgerFryer(PlayScreen screen, Rectangle bounds) {
		super(screen, bounds);
		fixture.setUserData(this);
		setCategoryFilter(PiazzaPanic.CATEGORY_STATION_ONE);
	}

	public void onInteraction(Fixture chefBody){
		if (chefBody.getUserData() == "chefOne")
		{
			Gdx.app.log("Chef","This is here fryer");
			if (chefOne.itemStack.notEmpty()) {
				if (chefOne.itemStack.contains("Patty", true)){
					chefOne.itemStack.removeValue("Patty", true);
					chefOne.itemStack.add("Burger");
					Gdx.app.log("Tester", "patty now cooked");
				}
			}
			else
			{
				if (chefTwo.itemStack.notEmpty())
					chefTwo.itemStack.pop();
			}
		}
	}
}