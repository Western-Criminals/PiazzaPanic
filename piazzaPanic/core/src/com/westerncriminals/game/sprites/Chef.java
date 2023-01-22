package com.westerncriminals.game.sprites;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.westerncriminals.game.PiazzaPanic;
import com.westerncriminals.game.screens.PlayScreen;

public class Chef extends Sprite{
	final short CATEGORY_PLAYER = 0x0001;
	final short CATEGORY_COUNTER = 0X0002;
	final short CATEGORY_STATION_ONE = 0X0004;
	final short MASK_PLAYER = CATEGORY_COUNTER | CATEGORY_STATION_ONE;
	
	
	public World world;
	public Body b2body; 
	private TextureRegion chefIdle;
	
	public Chef(World world, PlayScreen screen, int chefNum) {
		super(screen.getAtlas().findRegion("Chef A1"));
		this.world = world;
		if (chefNum == 1)
			defineChef();
		else
			defineChefTwo();
		chefIdle = new TextureRegion(getTexture(), 21, 10, 10,17); // 6 , 3 , 3 , 3
		setBounds(0, 0, 20f/PiazzaPanic.PPM, 34f/PiazzaPanic.PPM);
		setRegion(chefIdle);
	}
	
	public void defineChef() {
		BodyDef bdef = new BodyDef();
		bdef.position.set(32/ PiazzaPanic.PPM,32/ PiazzaPanic.PPM);
		bdef.type = BodyDef.BodyType.DynamicBody;
		b2body = world.createBody(bdef);
		
		FixtureDef fdef = new FixtureDef();
		CircleShape shape = new CircleShape();
		shape.setRadius(10f/ PiazzaPanic.PPM);
		
		fdef.shape = shape;
		fdef.filter.categoryBits = CATEGORY_PLAYER;
		fdef.filter.maskBits = MASK_PLAYER;
		b2body.createFixture(fdef);
		
	}
	
	private void defineChefTwo() {
		BodyDef bdef = new BodyDef();
		bdef.position.set(300/ PiazzaPanic.PPM,32/ PiazzaPanic.PPM);
		bdef.type = BodyDef.BodyType.DynamicBody;
		b2body = world.createBody(bdef);
		
		FixtureDef fdef = new FixtureDef();
		CircleShape shape = new CircleShape();
		shape.setRadius(10f/ PiazzaPanic.PPM);
		
		fdef.shape = shape;
		fdef.filter.categoryBits = CATEGORY_PLAYER;
		b2body.createFixture(fdef);
		
	}
	
	
	public void update(float dt) {
		setPosition(b2body.getPosition().x - getWidth() /2, b2body.getPosition().y - getHeight()/4);
	}
}