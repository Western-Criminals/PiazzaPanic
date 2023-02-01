package com.westerncriminals.game.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.World;

public class SaladGenerator extends InteractiveTileObject{
    final short CATEGORY_STATION_TWO = 0x0010;
    public Chef chefOne;
    public Chef chefTwo;

    public SaladGenerator(World world, TiledMap map, Rectangle bounds, Chef chefOne, Chef chefTwo) {
        super(world, map, bounds);
        fixture.setUserData(this);
        this.chefOne = chefOne;
        this.chefTwo = chefTwo;
    }

    public void onInteraction(Fixture chefBody){
        if (chefBody.getUserData() == "chefOne")
        {
            Gdx.app.log("Chef","This is here saladGen");
            if (chefOne.itemStack.contains("Lettuce", true )) {
                Gdx.app.log("DebugMsg", "No more lettuce for you");
            }
            else {
                chefOne.itemStack.add("Lettuce");
            }
            if (chefOne.itemStack.contains("Tomato", true )) {
                Gdx.app.log("DebugMsg", "No more Tomato for you");
            }
            else {
                chefOne.itemStack.add("Tomato");
            }
        }
        else
        {
            if (chefTwo.itemStack.notEmpty())
                chefTwo.itemStack.pop();
        }
    }

}
