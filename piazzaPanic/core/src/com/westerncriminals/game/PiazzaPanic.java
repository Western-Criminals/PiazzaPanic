package com.westerncriminals.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.westerncriminals.game.screens.*;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;




public class PiazzaPanic extends Game {
	public static final int PPM = 20;
	public static final int V_WIDTH = 320;
	public static final int V_HEIGHT = 280;
	public static final short CATEGORY_PLAYER = 1;
	public static final short CATEGORY_COUNTER = 2;
	public static final short CATEGORY_STATION_ONE = 4;
	public static final short CATEGORY_STATION_TWO = 8;
	public static final short CATEGORY_STATION_THREE = 16;
	public static final short CATEGORY_STATION_FOUR = 32;
	public static final short CATEGORY_STATION_FIVE = 64;
	public static final short CATEGORY_STATION_SIX = 128;
	public static final short CATEGORY_CUSTOMER = 256;
	
	
	public SpriteBatch batch;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		setScreen(new MainMenu(this));
	}

	@Override
	public void render () { super.render(); }
	
	@Override
	public void dispose () {
		batch.dispose();
	}
}