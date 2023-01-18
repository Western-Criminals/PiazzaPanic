package com.westerncriminals.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.westerncriminals.game.PiazzaPanic;
import com.westerncriminals.game.scenes.Hud;

public class PlayScreen implements Screen{
	private PiazzaPanic game;
	private OrthographicCamera gamecam;
	private Viewport gamePort;
	private Hud hud;
	private String hello;
	
	private TmxMapLoader maploader;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;
	
	public PlayScreen(PiazzaPanic game) {
		this.game = game;
		gamecam = new OrthographicCamera();
		gamePort = new FitViewport(PiazzaPanic.V_WIDTH,PiazzaPanic.V_HEIGHT, gamecam);
		//gamePort.setToOrtho(false, Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2)
		gamePort.apply();
		hud = new Hud(game.batch);
		
		maploader = new TmxMapLoader();
        map = maploader.load("kitchenTestTwo.tmx");
        renderer = new OrthogonalTiledMapRenderer(map);
        gamecam.position.set(gamePort.getWorldWidth() / 2, gamePort.getWorldHeight() /2, 0);
        
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}
	
	public void update(float dt) {
		handleInput();
		gamecam.update();
		renderer.setView(gamecam);
	}

	private void handleInput() {
		// TODO Auto-generated method stub
		hello = "lol";
	}

	@Override
	public void render(float delta) {
		update(delta);
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		renderer.render();
		
		game.batch.setProjectionMatrix(hud.stage.getCamera().combined);
		hud.stage.draw();
	}

	@Override
	public void resize(int width, int height) {
		gamePort.update(width, height);
		gamecam.setToOrtho(false,width,height);
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}
	

	
}
