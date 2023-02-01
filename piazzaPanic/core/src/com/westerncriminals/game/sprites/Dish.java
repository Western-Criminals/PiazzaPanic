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

import org.json.JSONArray;

public class Dish extends Sprite {

    private String name;
    private int duration;
    private JSONArray ingredients;
    public Body b2body;
    public Dish(String n, int d, JSONArray i) {
        name = n;
        duration = d;
        ingredients = i;
    }

    public void setDishName(String name) {
        this.name = name;
    }

    public void setTotalDuration(int duration) {
        this.duration = duration;
    }

    public String getDishName() {
        return this.name;
    }

    public int getTotalDuration() {
        return this.duration;
    }

    public float getIngredientDuration() {
        return (float)this.duration / this.ingredients.length();
    }
    
    public void defineDish(float x, float y) {}

    public void update() {
        setPosition(b2body.getPosition().x - getWidth() /2, b2body.getPosition().y - getHeight()/4);
    }
}
