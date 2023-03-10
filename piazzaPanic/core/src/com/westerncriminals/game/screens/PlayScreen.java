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
import com.westerncriminals.game.sprites.BurgerFryer;
import com.westerncriminals.game.sprites.Chef;
import com.westerncriminals.game.sprites.Customer;
import com.westerncriminals.game.sprites.Dish;
import com.westerncriminals.game.scenes.Inventory;

import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import  java.util.Arrays;
import java.util.List;
import java.util.Random;

public class PlayScreen implements Screen {
	JSONObject settings;
	JSONObject dishes;
	JSONObject chefs;
	JSONObject customers;
	
	private PiazzaPanic game;
	private TextureAtlas atlas;
	
	private OrthographicCamera gamecam;
	private Viewport gamePort;
	private Hud hud;

	private Inventory inv;
	public Chef chefOne;
	public Chef chefTwo;
	private Customer customer;
	private int chefControlled;
	private Dish burger;
	private Dish salad;
	public List<Dish> orders = new ArrayList<Dish>();
	
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
			chefs = settings.getJSONObject("chefs");
			customers = settings.getJSONObject("customers");
		}
		catch(IOException e) {
			e.printStackTrace();
		}

		atlas = new TextureAtlas("chefAtlas.txt");

		this.game = game;
		gamecam = new OrthographicCamera();
		gamePort = new FitViewport((float) PiazzaPanic.V_WIDTH / PiazzaPanic.PPM ,(float) PiazzaPanic.V_HEIGHT /  PiazzaPanic.PPM , gamecam);
		gamePort.apply();
		chefControlled = 1;

		maploader = new TmxMapLoader();
		inv = new Inventory(this.game, 200, 200, 0, 0, new ArrayList<String>());
        map = maploader.load("finalKitchenFr.tmx");
        renderer = new OrthogonalTiledMapRenderer(map, 1f/ PiazzaPanic.PPM);
        gamecam.position.set(gamePort.getWorldWidth() / 2 , gamePort.getWorldHeight() / 2 , 0);
        
        world = new World(new Vector2(0,0), true);
        b2dr = new Box2DDebugRenderer();

		chefOne = new Chef(this, 55, chefs.getString("img"));
		chefTwo = new Chef(this, 250, chefs.getString("img"));
		new B2WorldCreator(this);
		burger = new Dish(world, dishes.getJSONObject("0").getString("name"), dishes.getJSONObject("0").getInt("duration"), dishes.getJSONObject("0").getJSONArray("ingredients"), dishes.getJSONObject("0").getString("img"));
		salad = new Dish(world, dishes.getJSONObject("1").getString("name"), dishes.getJSONObject("1").getInt("duration"), dishes.getJSONObject("1").getJSONArray("ingredients"), dishes.getJSONObject("1").getString("img"));
		List<Dish> menu = new ArrayList<Dish>(Arrays.asList(burger, salad));
		for (int i = 0; i <= 6; i++) {
			orders.add(menu.get(new Random().nextInt(menu.size())));
		}

		customer = new Customer(this, (float) 100f, (float) 50f, customers.getString("img"));
		hud = new Hud(game.batch, customer, this, orders);
        
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
	    if(Gdx.input.isKeyPressed(Input.Keys.W)) {
	        velY = 10.0f ;
	    } else if(Gdx.input.isKeyPressed(Input.Keys.D)) {
	        velX = 10.0f;
	    } else if(Gdx.input.isKeyPressed(Input.Keys.S)) {
	        velY = -10.0f;
	    } else if(Gdx.input.isKeyPressed(Input.Keys.A)) {
	         velX = -10.0f;
	    }
	    if (chefControlled == 1) {
			chefTwo.b2body.setLinearVelocity(new Vector2(0f, 0f));
			chefOne.b2body.setLinearVelocity(new Vector2(velX, velY));
		}
	    else{
			chefOne.b2body.setLinearVelocity(new Vector2(0f, 0f));
			chefTwo.b2body.setLinearVelocity(new Vector2(velX, velY));
		}
		if (Gdx.input.isKeyPressed(Input.Keys.I) && chefOne.itemStack.notEmpty()) {
			inv.setVisibility(!(inv.getVisibility()));
			if (inv.getVisibility()) {
				Gdx.app.log("I", (String) chefOne.itemStack.peek());
				List<String> inv_lst = new ArrayList<String>();
				while (chefOne.itemStack.notEmpty()) {
					inv_lst.add(chefOne.itemStack.pop().toString());
				}
				inv.setInv(inv_lst);
			}
		}
	}

	@Override
	public void render(float delta) {
		update(delta);
		hud.update(delta);
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

		if (inv.getVisibility()) {
			inv.stage.draw();
		}
		
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
	public Customer getCustomer() {
		return customer;
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
