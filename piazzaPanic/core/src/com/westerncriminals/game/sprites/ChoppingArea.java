package com.westerncriminals.game.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;

public class ChoppingArea extends InteractiveTileObject{
	final short CATEGORY_STATION_TWO = 0X0006;

	public ChoppingArea(World world, TiledMap map, Rectangle bounds) {
		super(world, map, bounds);
    	fixture.setUserData(this);
	}

	@Override
	public void onInteraction() {
		Gdx.app.log("ChoppingBoard", "Collision");
	}
	

}
