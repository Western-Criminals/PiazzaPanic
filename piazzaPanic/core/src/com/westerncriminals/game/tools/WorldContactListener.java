package com.westerncriminals.game.tools;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.westerncriminals.game.sprites.InteractiveTileObject;
import com.westerncriminals.game.sprites.NPC;
import com.westerncriminals.game.PiazzaPanic;
import com.westerncriminals.game.sprites.BurgerFryer;

public class WorldContactListener implements ContactListener{

	@Override
	public void beginContact(Contact contact) {
		//Gdx.app.log("Begin contact", "");

		Fixture fixA = contact.getFixtureA();
		Fixture fixB = contact.getFixtureB();

		int cDef = fixA.getFilterData().categoryBits | fixB.getFilterData().categoryBits;

		if(fixA.getUserData() == "chefOne" || fixB.getUserData() == "chefOne") {
			Fixture chefBody = fixA.getUserData() == "chefOne" ? fixA : fixB;
			Fixture object = chefBody == fixA ? fixB : fixA;

			if (object.getUserData() != null && InteractiveTileObject.class.isAssignableFrom(object.getUserData().getClass())){
				( (InteractiveTileObject) object.getUserData()).onInteraction(chefBody);
			}
		}

		if(fixA.getUserData() == "chefTwo" || fixB.getUserData() == "chefTwo") {
			Fixture chefBody = fixA.getUserData() == "chefTwo" ? fixA : fixB;
			Fixture object = chefBody == fixA ? fixB : fixA;

			if (object.getUserData() != null && InteractiveTileObject.class.isAssignableFrom(object.getUserData().getClass())){
				( (InteractiveTileObject) object.getUserData()).onInteraction(chefBody);
			}
		}

		switch (cDef) {
			case PiazzaPanic.CATEGORY_CUSTOMER | PiazzaPanic.CATEGORY_COUNTER:
				if (fixA.getFilterData().categoryBits == PiazzaPanic.CATEGORY_CUSTOMER) {
					((NPC) fixA.getUserData()).reverseVelocity(false, true);
				}
				else
					((NPC )fixB.getUserData()).reverseVelocity(false, true);
		}

	}


	@Override
	public void endContact(Contact contact) {
		// TODO Auto-generated method stub

	}

	@Override
	public void preSolve(Contact contact, Manifold oldManifold) {
		// TODO Auto-generated method stub

	}

	@Override
	public void postSolve(Contact contact, ContactImpulse impulse) {
		// TODO Auto-generated method stub

	}
}
