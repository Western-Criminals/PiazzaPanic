package com.westerncriminals.game.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.World;

public class AssemblyStation extends InteractiveTileObject{
    final short CATEGORY_STATION_FIVE = 0x0014;
    public Chef chefOne;
    public Chef chefTwo;

    public AssemblyStation(World world, TiledMap map, Rectangle bounds, Chef chefOne, Chef chefTwo) {
        super(world, map, bounds);
        fixture.setUserData(this);
        this.chefOne = chefOne;
        this.chefTwo = chefTwo;
    }

    public void onInteraction(Fixture chefBody){
        if (chefBody.getUserData() == "chefOne")
        {
            Gdx.app.log("Chef","This is here AssemblyStation");
            if (chefOne.itemStack.contains("Bun", true) && chefOne.itemStack.contains("Burger", true)) {
                chefOne.itemStack.removeValue("Bun", true);
                chefOne.itemStack.removeValue("Burger", true);
                chefOne.itemStack.add("Finished Burger");
            }
            if (chefOne.itemStack.contains("Chopped Tomato", true) && chefOne.itemStack.contains("Chopped Lettuce", true)) {
                chefOne.itemStack.removeValue("Chopped Tomato", true);
                chefOne.itemStack.removeValue("Chopped Lettuce", true);
                chefOne.itemStack.add("Finished Salad");
            }
        }
        else
        {
            if (chefTwo.itemStack.notEmpty())
                chefTwo.itemStack.pop();
        }
    }
}