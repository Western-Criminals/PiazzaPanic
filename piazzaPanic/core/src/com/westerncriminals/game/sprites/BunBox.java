package com.westerncriminals.game.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.World;

public class BunBox extends InteractiveTileObject{
    final short CATEGORY_STATION_FOUR = 0x0012;
    public Chef chefOne;
    public Chef chefTwo;


    public BunBox(World world, TiledMap map, Rectangle bounds, Chef chefOne, Chef chefTwo) {
        super(world, map, bounds);
        fixture.setUserData(this);
        this.chefOne = chefOne;
        this.chefTwo = chefTwo;
    }

    public void onInteraction(Fixture chefBody){
        if (chefBody.getUserData() == "chefOne")
        {
            Gdx.app.log("Chef","This is here bunBox");
            if (chefOne.itemStack.contains("Bun", true)) {
                Gdx.app.log("DebugMsg", "No more buns for you");
            }
            else {
                chefOne.itemStack.add("Bun");
            }
        }
        else
        {
            if (chefTwo.itemStack.notEmpty())
                chefTwo.itemStack.pop();
        }
    }

}