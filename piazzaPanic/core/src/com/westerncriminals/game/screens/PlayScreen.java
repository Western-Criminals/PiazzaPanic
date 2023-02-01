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
import com.westerncriminals.game.sprites.NPC;
import com.westerncriminals.game.scenes.Inventory;
import com.westerncriminals.game.scenes.InventoryChris;

import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class PlayScreen implements Screen{
	
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
    private B2WorldCreator creator;
    
    public Integer mileStoneTime;
    public Integer currentId;
    
    public InventoryChris cInv;
    public Boolean menuState;
    
	
	public PlayScreen(PiazzaPanic game) {
	
		atlas = new TextureAtlas("chefAtlas.txt");
		
		this.game = game;
		mileStoneTime = 0;
		currentId = 0;
		gamecam = new OrthographicCamera();
		gamePort = new FitViewport(PiazzaPanic.V_WIDTH / PiazzaPanic.PPM ,PiazzaPanic.V_HEIGHT /  PiazzaPanic.PPM , gamecam);
		gamePort.apply();
		menuState = false;
		
		chefControlled = 1;

		maploader = new TmxMapLoader();
        map = maploader.load("finalKitchenFr.tmx");
        renderer = new OrthogonalTiledMapRenderer(map, 1f/ PiazzaPanic.PPM);
        gamecam.position.set(gamePort.getWorldWidth() / 2 , gamePort.getWorldHeight() / 2 , 0);
        
        world = new World(new Vector2(0,0), true);
        b2dr = new Box2DDebugRenderer();
       
	
        chefOne = new Chef(this, 55);
        chefTwo = new Chef(this, 250); 
        hud = new Hud(game.batch, customer, this);
        cInv = new InventoryChris(game.batch, this);
        
        creator = new B2WorldCreator(this);
        
        
        
        world.setContactListener(new WorldContactListener());
        inv = new Inventory(this.game, 200, 200, 0, 0, new ArrayList<String>());
    
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
		cInv.update(dt);
		chefOne.update(dt);
		chefTwo.update(dt);
		for(NPC npc : creator.getCustomers()) {
			npc.update(dt);
			if (hud.worldTime > mileStoneTime)
				if(npc.getId() == currentId) {
					npc.b2body.setActive(true);
					mileStoneTime += 5;
					currentId++;
				}
			
		}
		world.step(1/60f, 6, 2);
		gamecam.update();
		renderer.setView(gamecam);
	}
	

	private void handleInput() {
		float velX = 0, velY = 0;
		
		if (Gdx.input.isKeyJustPressed(Input.Keys.Q))
				chefControlled = 3 - chefControlled;
		if (Gdx.input.isKeyJustPressed(Input.Keys.I)){
				if (menuState)
					menuState = false;
				else
					menuState = true;
		}
		if (chefOne.itemStack.notEmpty()) {
			Gdx.app.log("I", (String) chefOne.itemStack.toString());
			Gdx.app.log("Amount", String.format("%03d", chefOne.itemStack.size));
			
		}
		if (Gdx.input.isKeyJustPressed(Input.Keys.R) && chefTwo.itemStack.notEmpty()) {
			Gdx.app.log("I", (String) chefTwo.itemStack.toString());
			Gdx.app.log("Amount", String.format("%03d", chefTwo.itemStack.size));
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
	    /*
	    if (Gdx.input.isKeyJustPressed(Input.Keys.I)da) {
	    	Gdx.app.log("I", (String) chefOne.itemStack.peek());
			List<String> inv_lst = new ArrayList<String>();
			while (chefOne.itemStack.notEmpty()) {
				inv_lst.add(chefOne.itemStack.get(0).toString());
				chefOne.itemStack.pop();
			}
			inv.setInv(inv_lst);
	    }
	    */
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
		for(NPC npc : creator.getCustomers())
			npc.draw(game.batch);
		game.batch.end();

		//if (inv.getVisibility()) {
			//inv.stage.draw();
		//}
		
		game.batch.setProjectionMatrix(hud.stage.getCamera().combined);
		hud.stage.draw();
		game.batch.setProjectionMatrix(cInv.stage.getCamera().combined);
		if (menuState == true)
			cInv.stage.draw();
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
	public Hud getHud() {
		return hud;
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
