package sprites;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.westerncriminals.game.PiazzaPanic;

public class Chef extends Sprite{
	public World world;
	public Body b2body;
	
	public Chef(World world) {
		this.world = world;
		defineChef();
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
