package sprites;

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
	public World world;
	public Body b2body; 
	private TextureRegion chefIdle;
	
	public Chef(World world, PlayScreen screen) {
		super(screen.getAtlas().findRegion("Chef A1"));
		this.world = world;
		defineChef();
		chefIdle = new TextureRegion(getTexture(), 6, 3, 3,3); // 6 , 3 , 3 , 3 
		setBounds(0, 0, 3  / PiazzaPanic.PPM , 3 / PiazzaPanic.PPM);
		setRegion(chefIdle);
	}
	
	public void update(float dt) {
		setPosition(b2body.getPosition().x - getWidth() /2, b2body.getPosition().y - getHeight());
	}
	
	public void defineChef() {
		BodyDef bdef = new BodyDef();
		bdef.position.set(32/ PiazzaPanic.PPM,32/ PiazzaPanic.PPM);
		bdef.type = BodyDef.BodyType.DynamicBody;
		b2body = world.createBody(bdef);
		
		FixtureDef fdef = new FixtureDef();
		CircleShape shape = new CircleShape();
		shape.setRadius(15f/ PiazzaPanic.PPM);
		
		fdef.shape = shape;
		b2body.createFixture(fdef);
		
	}
}
