package com.mygdx.game.controller;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.model.World;
import com.mygdx.game.vue.WorldRenderer;

/**
 * TankMaze is the main controller class.
 */
public class TankMaze extends Game {

	private SpriteBatch batch;
	WorldRenderer wr;
	FPSLogger fpsLogger = new FPSLogger();
	OrthographicCamera camera;
	World world;


	@Override
	public void create () {
		wr = new WorldRenderer();
		batch = new SpriteBatch();
		camera = new OrthographicCamera();
		world = new World();
	}
	
	@Override
	public void resize(int width, int height) {
		camera.setToOrtho(false, width, height);
		camera.position.set(width / 2, height / 2, 0);
	}

	@Override
	public void render () {
		super.render();
		fpsLogger.log();
		batch.setProjectionMatrix(camera.combined);
		checkInputs();
		wr.render(batch, world);
	}


	/**
	* À chaque frame, on regardes les inputs.
	*/
	private void checkInputs() {
		if(Gdx.input.isKeyPressed(Keys.W)) {
			// go to top
			// world.setPlayerPosition(x, y)
		}
		if(Gdx.input.isKeyPressed(Keys.A)) {
			// go to left 
		}
		if(Gdx.input.isKeyPressed(Keys.S)) {
			// go to bottom 
		}
		if(Gdx.input.isKeyPressed(Keys.D)) {
			// go to down 
		}
	}

	@Override
	public void dispose () {
		batch.dispose();
	}
}
