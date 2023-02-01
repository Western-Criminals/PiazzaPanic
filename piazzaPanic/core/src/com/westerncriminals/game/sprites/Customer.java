package com.westerncriminals.game.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
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

public class Customer extends NPC{

	private float stateTime;
	public Array<Object> itemStack;
	private TextureRegion chefIdle;
	public boolean customerPresent;

	
	public Customer(PlayScreen screen, float x, float y ) {
		super(screen, x , y);
		defineNPC();
		chefIdle = new TextureRegion(screen.getAtlas().findRegion("Chef A1"),21, 9, 9,17); 
		stateTime = 0;
        setBounds(getX(), getY(), 20f/ PiazzaPanic.PPM, 34f / PiazzaPanic.PPM);
        setRegion(chefIdle);
		itemStack = new Array<Object>();
		customerPresent = false;
	}
	

	 public void update(float dt){
		 stateTime += dt;
		 if (!customerPresent) {
			 //world.destroyBody(b2body);
			 stateTime = 0;
		 }
		 else if (customerPresent)
	        b2body.setLinearVelocity(velocity);
	        setPosition(b2body.getPosition().x - getWidth() / 2, b2body.getPosition().y - getHeight() / 2);
	    }
	

	@Override
	protected void defineNPC() {
		BodyDef bdef = new BodyDef();
		bdef.position.set((((float) getX()) + 55) / PiazzaPanic.PPM, (((float) getY()) -35) / PiazzaPanic.PPM);
        bdef.type = BodyDef.BodyType.DynamicBody;
        b2body = world.createBody(bdef);

        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(10f/ PiazzaPanic.PPM);
        fdef.filter.categoryBits = PiazzaPanic.CATEGORY_CUSTOMER;
        fdef.filter.maskBits = -1;

        fdef.shape = shape;
        b2body.createFixture(fdef).setUserData(this);
	}
	
	public void draw(Batch batch){
		if (customerPresent)
			super.draw(batch);
    }
}
