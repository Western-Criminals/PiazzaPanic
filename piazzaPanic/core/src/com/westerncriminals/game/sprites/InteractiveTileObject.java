package com.westerncriminals.game.sprites;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Filter;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.westerncriminals.game.PiazzaPanic;
import com.westerncriminals.game.screens.PlayScreen;

public abstract class InteractiveTileObject {
	final short CATEGORY_STATION_ONE = 0x0004;
	protected World world;
    protected TiledMap map;
    protected Rectangle bounds;
    protected Body body;
    protected PlayScreen screen;
    protected MapObject object;

    protected Fixture fixture;

    public InteractiveTileObject(World world, TiledMap map, Rectangle bounds) {
    	
    	this.world = world;
    	this.map = map;
    	this.bounds = bounds;
    	
    	BodyDef bdef = new BodyDef();
		FixtureDef fdef = new FixtureDef();
		PolygonShape shape = new PolygonShape();
    	
    	bdef.type = BodyDef.BodyType.StaticBody;
    	bdef.position.set((bounds.getX() + bounds.getWidth() /2) / PiazzaPanic.PPM, (bounds.getY() + bounds.getHeight() /2)/ PiazzaPanic.PPM);
    	
    	body = world.createBody(bdef);
    	
    	shape.setAsBox(bounds.getWidth()/ 2 / PiazzaPanic.PPM, bounds.getHeight()/ 2 / PiazzaPanic.PPM);
    	fdef.shape = shape;
    	fdef.filter.categoryBits = CATEGORY_STATION_ONE;
		fdef.filter.maskBits = -1;
    	fixture = body.createFixture(fdef);
    	
    }
    
    

	public abstract void onInteraction(Fixture chefBody);

}
