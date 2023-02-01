package com.westerncriminals.game.sprites;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.westerncriminals.game.PiazzaPanic;
import com.westerncriminals.game.screens.PlayScreen;

public class Customer extends Sprite{

	
	public Array<Object> itemStack;
	public World world;
	public Body b2body; 
	private TextureRegion chefIdle;
	float timeCount;
	
	public Customer(World world, PlayScreen screen) {
		super(screen.getAtlas().findRegion("Chef A1"));
		this.world = world;
		defineCustomer();
		chefIdle = new TextureRegion(getTexture(), 21, 10, 10,17); // 6 , 3 , 3 , 3
		setBounds(0, 0, 20f/PiazzaPanic.PPM, 34f/PiazzaPanic.PPM);
		setRegion(chefIdle); 
		itemStack = new Array<Object>();
	}
	
	public void defineCustomer() {
		BodyDef bdef = new BodyDef();
		timeCount = 0;
		bdef.position.set( -15 / PiazzaPanic.PPM, 30/ PiazzaPanic.PPM);
		bdef.type = BodyDef.BodyType.DynamicBody;
		b2body = world.createBody(bdef);
		
		FixtureDef fdef = new FixtureDef();
		CircleShape shape = new CircleShape();
		shape.setRadius(10f/ PiazzaPanic.PPM);
		
		fdef.shape = shape;
		
		
		b2body.createFixture(fdef).setUserData("customer");
		
		
				
		// may need to create a new fixture for contact, maybe
		
	}
	public void update(float dt) {
		setPosition(b2body.getPosition().x - getWidth() /2, b2body.getPosition().y - getHeight()/4);
	}
	
	public void walkTowardCounter(float dt) {
		while(true) {
			timeCount += dt;
			if (timeCount <= 5)
				this.b2body.setLinearVelocity(new Vector2(5, 0));
			else
				break;
		}
	}
}
