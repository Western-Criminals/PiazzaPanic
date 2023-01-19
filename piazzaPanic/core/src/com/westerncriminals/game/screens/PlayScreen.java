package com.westerncriminals.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.westerncriminals.game.PiazzaPanic;
import com.westerncriminals.game.scenes.Hud;

import sprites.Chef;

public class PlayScreen implements Screen{
	private PiazzaPanic game;
	private OrthographicCamera gamecam;
	private Viewport gamePort;
	private Hud hud;
	private String hello;
	private Chef chefOne;
	
	
	private TmxMapLoader maploader;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;
    
    private World world;
    private Box2DDebugRenderer b2dr;
	
	public PlayScreen(PiazzaPanic game) {
		this.game = game;
		gamecam = new OrthographicCamera();
		gamePort = new FitViewport(PiazzaPanic.V_WIDTH /2 ,PiazzaPanic.V_HEIGHT /2  , gamecam);
		//gamePort.setToOrtho(false, Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2)
		gamePort.apply();
		hud = new Hud(game.batch);
		
		
		maploader = new TmxMapLoader();
        map = maploader.load("finalKitchen.tmx");
        renderer = new OrthogonalTiledMapRenderer(map);
        gamecam.position.set(gamePort.getWorldWidth() / 2 , gamePort.getWorldHeight() / 2 , 0);
        
        world = new World(new Vector2(0,0), true);
        b2dr = new Box2DDebugRenderer();
        chefOne = new Chef(world);
        
        BodyDef bdef = new BodyDef();
        PolygonShape shape = new PolygonShape();
        FixtureDef fdef = new FixtureDef();
        Body body;
        
        for(MapObject object: map.getLayers().get(2).getObjects().getByType(RectangleMapObject.class)){
        	Rectangle rect = ((RectangleMapObject) object).getRectangle();
        	
        	bdef.type = BodyDef.BodyType.StaticBody;
        	bdef.position.set(rect.getX() + rect.getWidth() /2, rect.getY() + rect.getHeight() /2);
        	
        	body = world.createBody(bdef);
        	
        	shape.setAsBox(rect.getWidth()/ 2, rect.getHeight()/ 2);
        	fdef.shape = shape;
        	body.createFixture(fdef);
        }
        
        for(MapObject object: map.getLayers().get(3).getObjects().getByType(RectangleMapObject.class)){
        	Rectangle rect = ((RectangleMapObject) object).getRectangle();
        	
        	bdef.type = BodyDef.BodyType.StaticBody;
        	bdef.position.set(rect.getX() + rect.getWidth() /2, rect.getY() + rect.getHeight() /2);
        	
        	body = world.createBody(bdef);
        	
        	shape.setAsBox(rect.getWidth()/ 2, rect.getHeight()/ 2);
        	fdef.shape = shape;
        	body.createFixture(fdef);
        }
        
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}
	
	public void update(float dt) {
		handleInput();
		
		world.step(1/60f, 6, 2);
		
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
		
		 b2dr.render(world, gamecam.combined);
		
		game.batch.setProjectionMatrix(hud.stage.getCamera().combined);
		hud.stage.draw();
	}

	@Override
	public void resize(int width, int height) {
		gamePort.update(width, height);
		gamecam.setToOrtho(false,PiazzaPanic.V_WIDTH  ,PiazzaPanic.V_HEIGHT );
		
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
