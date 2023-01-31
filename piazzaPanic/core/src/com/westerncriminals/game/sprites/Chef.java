package com.westerncriminals.game.sprites;


import java.util.Stack;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.westerncriminals.game.PiazzaPanic;
import com.westerncriminals.game.screens.PlayScreen;

public class Chef extends Sprite {
	final short CATEGORY_PLAYER = 0x0001;
	final short CATEGORY_COUNTER = 0X0002;
	final short CATEGORY_STATION_ONE = 0X0004;
	final short MASK_PLAYER = CATEGORY_COUNTER | CATEGORY_STATION_ONE;
	
	public Array<Object> itemStack;
	public World world;
	public Body b2body; 
	private TextureRegion chefIdle;
	
	public Chef(World world, PlayScreen screen, int chefNum, float xPos) {
		super(screen.getAtlas().findRegion("Chef A1"));
		this.world = world;
		defineChef(xPos, 50);
		chefIdle = new TextureRegion(getTexture(), 21, 10, 10,17); // 6 , 3 , 3 , 3
		setBounds(0, 0, 20f/PiazzaPanic.PPM, 34f/PiazzaPanic.PPM);
		setRegion(chefIdle); 
		itemStack = new Array<Object>();
	}
	
	public void defineChef(float x, float y) {
		BodyDef bdef = new BodyDef();
		bdef.position.set(x/ PiazzaPanic.PPM,y/ PiazzaPanic.PPM);
		bdef.type = BodyDef.BodyType.DynamicBody;
		b2body = world.createBody(bdef);
		
		FixtureDef fdef = new FixtureDef();
		CircleShape shape = new CircleShape();
		shape.setRadius(10f/ PiazzaPanic.PPM);
		
		fdef.shape = shape;
		fdef.filter.categoryBits = CATEGORY_PLAYER;
		fdef.filter.maskBits = MASK_PLAYER;
		
		if (x == 55)
			b2body.createFixture(fdef).setUserData("chefOne");
		else
			b2body.createFixture(fdef).setUserData("chefTwo");
				
		// may need to create a new fixture for contact, maybe
		
	}
	public void update(float dt) {
		setPosition(b2body.getPosition().x - getWidth() /2, b2body.getPosition().y - getHeight()/4);
	}
}
