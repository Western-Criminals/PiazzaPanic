package com.westerncriminals.game.sprites;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.westerncriminals.game.screens.PlayScreen;

public abstract class NPC extends Sprite{
	
	 protected World world;
	 protected PlayScreen screen;
	 public Body b2body;
	 public Vector2 velocity;
	
	 public NPC(PlayScreen screen, float x, float y){
	        this.world = screen.getWorld();
	        this.screen = screen;
	        setPosition(x, y);
	        defineNPC();
	        velocity = new Vector2(0, (float) 0.7);
	        b2body.setActive(false);
	    }
	 
	 protected abstract void defineNPC();
	 public abstract void update(float dt);
	 public abstract int getId();
	 public void reverseVelocity(boolean x, boolean y) {
		 if(x)
			 velocity.x = -velocity.x;
		 if(y)
			 velocity.y = -velocity.y;
	 }
}
