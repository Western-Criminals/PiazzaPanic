package com.westerncriminals.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.westerncriminals.game.PiazzaPanic;
import com.westerncriminals.game.scenes.Hud;
import com.westerncriminals.game.tools.B2WorldCreator;
import com.westerncriminals.game.tools.WorldContactListener;
import com.westerncriminals.game.sprites.Chef;

public class PlayScreen implements Screen{
	
	private PiazzaPanic game;
	private TextureAtlas atlas;
	
	private OrthographicCamera gamecam;
	private Viewport gamePort;
	private Hud hud;
	
	private Chef chefOne;
	private Chef chefTwo;
	private int chefControlled;
	
	private TmxMapLoader maploader;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;
    
    private World world;
    private Box2DDebugRenderer b2dr;
    
	
	public PlayScreen(PiazzaPanic game) {
		atlas = new TextureAtlas("chefAtlas.txt");
		
		this.game = game;
		gamecam = new OrthographicCamera();
		gamePort = new FitViewport(PiazzaPanic.V_WIDTH / PiazzaPanic.PPM ,PiazzaPanic.V_HEIGHT /  PiazzaPanic.PPM , gamecam);
		gamePort.apply();
		chefControlled = 1;
<<<<<<< Updated upstream
		
=======
		hud = new Hud(game.batch);
>>>>>>> Stashed changes
		
		maploader = new TmxMapLoader();
        map = maploader.load("finalKitchen.tmx");
        renderer = new OrthogonalTiledMapRenderer(map, 1f/ PiazzaPanic.PPM);
        gamecam.position.set(gamePort.getWorldWidth() / 2 , gamePort.getWorldHeight() / 2 , 0);
        
        world = new World(new Vector2(0,0), true);
        b2dr = new Box2DDebugRenderer();
        
        new B2WorldCreator(world,map);
        
<<<<<<< Updated upstream
        chefOne = new Chef(world, this, 1, 55);
        chefTwo = new Chef(world, this, 2, 250);
        
        world.setContactListener(new WorldContactListener());
=======
        chefOne = new Chef(world, this, 1, 32);
        chefTwo = new Chef(world, this, 2, 300);
        
>>>>>>> Stashed changes
	}
	
	public TextureAtlas getAtlas(){
		return atlas;
	}
	
	
	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}
	
	public void update(float dt) {
		handleInput();
		chefOne.update(dt);
		chefTwo.update(dt);
		hud.update(dt);
		world.step(1/60f, 6, 2);
		gamecam.update();
		renderer.setView(gamecam);
	}
	

	private void handleInput() {
		float velX = 0, velY = 0;
		
		if (Gdx.input.isKeyPressed(Input.Keys.Q))
				chefControlled = 3 - chefControlled;
	    if(Gdx.input.isKeyPressed(Input.Keys.W)) {
	        velY = 10.0f ;
	    } else if(Gdx.input.isKeyPressed(Input.Keys.D)) {
	        velX = 10.0f;
	    } else if(Gdx.input.isKeyPressed(Input.Keys.S)) {
	        velY = -10.0f;
	    } else if(Gdx.input.isKeyPressed(Input.Keys.A)) {
	         velX = -10.0f;
	    }
	    if (chefControlled == 1)
	    	chefOne.b2body.setLinearVelocity(new Vector2(velX, velY));
	    else
	    	chefTwo.b2body.setLinearVelocity(new Vector2(velX, velY));
	}

	@Override
	public void render(float delta) {
		update(delta);
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		renderer.render();
		
		b2dr.render(world, gamecam.combined);
		game.batch.setProjectionMatrix(gamecam.combined);
		game.batch.begin();
		chefOne.draw(game.batch);
		chefTwo.draw(game.batch);
		game.batch.end();
		
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
		map.dispose();
		renderer.dispose();
		world.dispose();
		b2dr.dispose();
		hud.dispose();
	}
	

	
}