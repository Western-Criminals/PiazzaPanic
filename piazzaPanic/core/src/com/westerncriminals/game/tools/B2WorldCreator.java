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
import com.badlogic.gdx.utils.Array;
import com.westerncriminals.game.PiazzaPanic;
import com.westerncriminals.game.scenes.Hud;
import com.westerncriminals.game.screens.PlayScreen;
import com.westerncriminals.game.sprites.AssemblyStation;
import com.westerncriminals.game.sprites.BunBox;
import com.westerncriminals.game.sprites.BurgerFryer;
import com.westerncriminals.game.sprites.Chef;
import com.westerncriminals.game.sprites.ChoppingArea;
import com.westerncriminals.game.sprites.Customer;
import com.westerncriminals.game.sprites.Pantry;
import com.westerncriminals.game.sprites.SaladGenerator;
import com.westerncriminals.game.sprites.ServingTile;


public class B2WorldCreator {
	private Array<Customer> customers;

	
	public B2WorldCreator(PlayScreen screen){
		World world = screen.getWorld();
		TiledMap map = screen.getMap();
        
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
        	fdef.filter.categoryBits = PiazzaPanic.CATEGORY_COUNTER; 
    		fdef.filter.maskBits = -1;
        	body.createFixture(fdef).setUserData("counter");
        }
        
        for(MapObject object: map.getLayers().get(3).getObjects().getByType(RectangleMapObject.class)){
        	Rectangle rect = ((RectangleMapObject) object).getRectangle();
        	new BurgerFryer(screen, rect);
        }
        
        for(MapObject object: map.getLayers().get(4).getObjects().getByType(RectangleMapObject.class)){
        	Rectangle rect = ((RectangleMapObject) object).getRectangle();
        	new SaladGenerator(screen, rect);
        }
        
		for(MapObject object: map.getLayers().get(5).getObjects().getByType(RectangleMapObject.class)){
			Rectangle rect = ((RectangleMapObject) object).getRectangle();
			new Pantry(screen, rect);
		}
		
		for(MapObject object: map.getLayers().get(6).getObjects().getByType(RectangleMapObject.class)){
			Rectangle rect = ((RectangleMapObject) object).getRectangle();
			new BunBox(screen, rect);
		}
		
		for(MapObject object: map.getLayers().get(7).getObjects().getByType(RectangleMapObject.class)){
			Rectangle rect = ((RectangleMapObject) object).getRectangle();
			new AssemblyStation(screen, rect);
		}
		
		for(MapObject object: map.getLayers().get(8).getObjects().getByType(RectangleMapObject.class)){
			Rectangle rect = ((RectangleMapObject) object).getRectangle();
			new ChoppingArea(screen, rect);
		}
		for(MapObject object: map.getLayers().get(9).getObjects().getByType(RectangleMapObject.class)){
			Rectangle rect = ((RectangleMapObject) object).getRectangle();
			new ServingTile(screen, rect);
		}
		
		customers = new Array<Customer>();
		int i = 0;
		for(MapObject object: map.getLayers().get(10).getObjects().getByType(RectangleMapObject.class)){
			Rectangle rect = ((RectangleMapObject) object).getRectangle();
			customers.add(new Customer(screen, rect.getX() / PiazzaPanic.PPM,  rect.getY() / PiazzaPanic.PPM, i));
			i++;
		}		
	}
	public Array<Customer> getCustomers(){
		return customers;
	}
}