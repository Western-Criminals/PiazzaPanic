package com.westerncriminals.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.westerncriminals.game.PiazzaPanic;
import com.westerncriminals.game.scenes.Hud;

public class PlayScreen implements Screen{
	private PiazzaPanic game;
	private OrthographicCamera gamecam;
	private Viewport gamePort;
	private Hud hud;
	
	
	public PlayScreen(PiazzaPanic game) {
		this.game = game;
		gamecam = new OrthographicCamera();
		gamePort = new FitViewport(PiazzaPanic.V_WIDTH,PiazzaPanic.V_HEIGHT, gamecam);
		hud = new Hud(game.batch);
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		game.batch.setProjectionMatrix(hud.stage.getCamera().combined);
		hud.stage.draw();
	}

	@Override
	public void resize(int width, int height) {
		gamePort.update(width, height);
		
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
