package com.westerncriminals.game.tools;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.westerncriminals.game.PiazzaPanic;
import com.westerncriminals.game.sprites.BurgerFryer;

public class B2WorldCreator {
	final short CATEGORY_COUNTER = 0X0002;
	
	
	public B2WorldCreator(World world, TiledMap map){
        BodyDef bdef = new BodyDef();
        PolygonShape shape = new PolygonShape();
        FixtureDef fdef = new FixtureDef();
        Body body;
        
        for(MapObject object: map.getLayers().get(2).getObjects().getByType(RectangleMapObject.class)){
        	Rectangle rect = ((RectangleMapObject) object).getRectangle();
        	
        	bdef.type = BodyDef.BodyType.StaticBody;
        	bdef.position.set((rect.getX() + rect.getWidth() /2) / PiazzaPanic.PPM, (rect.getY() + rect.getHeight() /2) / PiazzaPanic.PPM);
        	
        	body = world.createBody(bdef);
        	
        	shape.setAsBox(rect.getWidth()/ 2 / PiazzaPanic.PPM, rect.getHeight()/ 2 / PiazzaPanic.PPM);
        	fdef.shape = shape;
        	fdef.filter.categoryBits = CATEGORY_COUNTER;
    		fdef.filter.maskBits = -1;
        	body.createFixture(fdef).setUserData("counter");
        }
        
        for(MapObject object: map.getLayers().get(3).getObjects().getByType(RectangleMapObject.class)){
        	Rectangle rect = ((RectangleMapObject) object).getRectangle();
        	new BurgerFryer(world, map, rect);
        }
	}

}