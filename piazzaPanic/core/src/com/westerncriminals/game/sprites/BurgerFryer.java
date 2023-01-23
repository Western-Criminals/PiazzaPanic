package com.westerncriminals.game.sprites;

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
    	body.createFixture(fdef);
	}

}
