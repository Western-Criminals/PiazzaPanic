package com.westerncriminals.game.sprites;

import org.json.JSONArray;

public class Dish {

    private String name;
    private int duration;
    private JSONArray ingredients;
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

    public int getIngredientDuration() {
        return this.duration / this.ingredients.length();
    }

    public void update() {}
}
