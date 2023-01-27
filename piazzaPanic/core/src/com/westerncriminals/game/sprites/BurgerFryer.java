package com.westerncriminals.game.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.westerncriminals.game.PiazzaPanic;

public class BurgerFryer extends InteractiveTileObject{
	final short CATEGORY_STATION_ONE = 0X0004;

	public BurgerFryer(World world, TiledMap map, Rectangle bounds) {
		super(world, map, bounds);
    	fixture.setUserData(this);
	}

	@Override
	public void onInteraction() {
		Gdx.app.log("Fryer", "Collision");
	}
	

}
