package com.westerncriminals.game.sprites;

public class Dish {

    private String name;
    private int duration;
    public Dish(String n, int d) {
        name = n;
        duration = d;
    }

    public void setDishName(String name) {
        this.name = name;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getDishName() {
        return this.name;
    }

    public int getDuration() {
        return this.duration;
    }

    public void update() {}
}
