package com.westerncriminals.game.sprites;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.westerncriminals.game.PiazzaPanic;
import com.westerncriminals.game.screens.PlayScreen;

public class Chef extends Sprite{
	final short CATEGORY_PLAYER = 0x0001;
	final short CATEGORY_COUNTER = 0X0002;
	final short CATEGORY_STATION_ONE = 0X0004;
	final short MASK_PLAYER = CATEGORY_COUNTER | CATEGORY_STATION_ONE;
	
	public Stage stage;
	public World world;
	public Body b2body; 
	public Stack ingrediantStack;
	private TextureRegion chefIdle;
	
<<<<<<< Updated upstream
	public Chef(World world, PlayScreen screen, int chefNum, float xPos) {
		super(screen.getAtlas().findRegion("Chef A1"));
		this.world = world;
		defineChef(xPos, 50);
=======
	Label stackLabel;
	
	public Chef(World world, PlayScreen screen, int chefNum, int xPos) {
		super(screen.getAtlas().findRegion("Chef A1"));
		this.world = world;
		ingrediantStack = new Stack();
		defineChef(xPos);
>>>>>>> Stashed changes
		chefIdle = new TextureRegion(getTexture(), 21, 10, 10,17); // 6 , 3 , 3 , 3
		setBounds(0, 0, 20f/PiazzaPanic.PPM, 34f/PiazzaPanic.PPM);
		setRegion(chefIdle);
	}
	
<<<<<<< Updated upstream
	public void defineChef(float x, float y) {
		BodyDef bdef = new BodyDef();
		bdef.position.set(x/ PiazzaPanic.PPM,y/ PiazzaPanic.PPM);
=======
	public void defineChef(int xPos) {
		BodyDef bdef = new BodyDef();
		bdef.position.set(xPos / PiazzaPanic.PPM,32/ PiazzaPanic.PPM);
>>>>>>> Stashed changes
		bdef.type = BodyDef.BodyType.DynamicBody;
		b2body = world.createBody(bdef);
		
		FixtureDef fdef = new FixtureDef();
		CircleShape shape = new CircleShape();
		shape.setRadius(10f/ PiazzaPanic.PPM);
		
		fdef.shape = shape;
		fdef.filter.categoryBits = CATEGORY_PLAYER;
		fdef.filter.maskBits = MASK_PLAYER;
		b2body.createFixture(fdef).setUserData("chefBody");
		// may need to create a new fixture for contact, maybe
		
	}
<<<<<<< Updated upstream
=======
	
>>>>>>> Stashed changes
	public void update(float dt) {
		setPosition(b2body.getPosition().x - getWidth() /2, b2body.getPosition().y - getHeight()/2);
	}
}
