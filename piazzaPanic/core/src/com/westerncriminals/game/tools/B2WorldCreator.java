package com.westerncriminals.game.tools;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.westerncriminals.game.PiazzaPanic;
import com.westerncriminals.game.sprites.BurgerFryer;
import com.westerncriminals.game.sprites.Chef;
import com.westerncriminals.game.sprites.ChoppingArea;
import com.westerncriminals.game.sprites.Pantry;

public class B2WorldCreator {
	final short CATEGORY_COUNTER = 0X0002;
	public Chef chefOne;
	public Chef chefTwo;
	
	public B2WorldCreator(World world, TiledMap map, Chef chefOne, Chef chefTwo){
        BodyDef bdef = new BodyDef();
        PolygonShape shape = new PolygonShape();
        FixtureDef fdef = new FixtureDef();
        Body body;
        this.chefOne = chefOne;
        this.chefTwo = chefTwo;
        
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
        	new BurgerFryer(world, map, rect, chefOne, chefTwo);
        }
		for(MapObject object: map.getLayers().get(7).getObjects().getByType(RectangleMapObject.class)){
			Rectangle rect = ((RectangleMapObject) object).getRectangle();
			new ChoppingArea(world, map, rect, chefOne, chefTwo);
		}
		for(MapObject object: map.getLayers().get(6).getObjects().getByType(RectangleMapObject.class)){
			Rectangle rect = ((RectangleMapObject) object).getRectangle();
			new Pantry(world, map, rect, chefOne, chefTwo);
		}
	}

}