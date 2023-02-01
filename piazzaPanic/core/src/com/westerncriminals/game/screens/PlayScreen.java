package com.westerncriminals.game.screens;

// import com.badlogic.gdx.Files;
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
import com.westerncriminals.game.sprites.BurgerFryer;
import com.westerncriminals.game.sprites.Chef;
import com.westerncriminals.game.sprites.Customer;
import com.westerncriminals.game.sprites.Dish;
import com.westerncriminals.game.screens.Inventory;

import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class PlayScreen implements Screen{
	JSONObject settings;
	JSONObject dishes;
	
	private PiazzaPanic game;
	private TextureAtlas atlas;
	
	private OrthographicCamera gamecam;
	private Viewport gamePort;
	private Hud hud;


	private Inventory inv;
	private Chef chefOne;
	private Chef chefTwo;
	private Customer customer;
	
	private int chefControlled;
	private Dish burger;
	private Dish salad;
	
	private TmxMapLoader maploader;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;
    private World world;
    private Box2DDebugRenderer b2dr;
    
	
	public PlayScreen(PiazzaPanic game) {
		try {
			String contents = new String(Files.readString(Paths.get("./settings.json")));
			settings = new JSONObject(contents);
			dishes = settings.getJSONObject("dishes");
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	
		atlas = new TextureAtlas("chefAtlas.txt");
		
		this.game = game;
		gamecam = new OrthographicCamera();
		gamePort = new FitViewport(PiazzaPanic.V_WIDTH / PiazzaPanic.PPM ,PiazzaPanic.V_HEIGHT /  PiazzaPanic.PPM , gamecam);
		gamePort.apply();
		
		chefControlled = 1;

		maploader = new TmxMapLoader();
		//inv = new Inventory(this.game, 200, 200, 0, 0, new ArrayList<String>());
        map = maploader.load("finalKitchenFr.tmx");
        renderer = new OrthogonalTiledMapRenderer(map, 1f/ PiazzaPanic.PPM);
        gamecam.position.set(gamePort.getWorldWidth() / 2 , gamePort.getWorldHeight() / 2 , 0);
        
        world = new World(new Vector2(0,0), true);
        b2dr = new Box2DDebugRenderer();
        
        customer = new Customer(this, (float) 100f, (float) 50f);
		hud = new Hud(game.batch, customer);
        
        chefOne = new Chef(this, 55);
        chefTwo = new Chef(this, 250); 
        
        new B2WorldCreator(this);
		burger = new Dish(dishes.getJSONObject("0").getString("name"), dishes.getJSONObject("0").getInt("duration"), dishes.getJSONObject("0").getJSONArray("ingredients"));
		salad = new Dish(dishes.getJSONObject("1").getString("name"), dishes.getJSONObject("1").getInt("duration"), dishes.getJSONObject("1").getJSONArray("ingredients"));
        
        world.setContactListener(new WorldContactListener());
	}
	
	public TextureAtlas getAtlas() {
		return atlas;
	}
	
	
	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}
	
	public void update(float dt) {
		handleInput();
		hud.update(dt);
		chefOne.update(dt);
		chefTwo.update(dt);
		customer.update(dt); 
		world.step(1/60f, 6, 2);
		gamecam.update();
		renderer.setView(gamecam);
	}
	

	private void handleInput() {
		float velX = 0, velY = 0;
		
		if (Gdx.input.isKeyJustPressed(Input.Keys.Q))
				chefControlled = 3 - chefControlled;
		if (Gdx.input.isKeyJustPressed(Input.Keys.I) && chefOne.itemStack.notEmpty()) {
			Gdx.app.log("I", (String) chefOne.itemStack.toString());
			Gdx.app.log("Amount", String.format("%03d", chefOne.itemStack.size));
		}
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
		//if (Gdx.input.isKeyPressed(Input.Keys.I))
			// inv.setInv(stuff);
			//inv.setVisibility(!(inv.getVisibility()));
	}

	@Override
	public void render(float delta) {
		update(delta);
		;
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		renderer.render();
		
		b2dr.render(world, gamecam.combined);
		game.batch.setProjectionMatrix(gamecam.combined);
		game.batch.begin();
		chefOne.draw(game.batch);
		chefTwo.draw(game.batch);
		customer.draw(game.batch);
		game.batch.end();

		//if (inv.getVisibility()) {
			//inv.stage.draw();
		//}
		
		game.batch.setProjectionMatrix(hud.stage.getCamera().combined);
		hud.stage.draw();
	}
	
	public TiledMap getMap() {
		return map;
	}
	
	public World getWorld() {
		return world;
	}
	
	public Chef getChefOne(){
		return chefOne;
	}
	
	public Chef getChefTwo() {
		return chefTwo;
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
		inv.dispose();
	}
}
