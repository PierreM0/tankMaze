package com.mygdx.game.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.model.World;
import com.mygdx.game.vue.WorldRenderer;

/**
 * TankMaze is the main controller class.
 */
public class TankMaze extends ScreenAdapter {

	private SpriteBatch batch;
	WorldRenderer wr;
	FPSLogger fpsLogger = new FPSLogger();
	OrthographicCamera camera;
	World world;


	public TankMaze() {
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
	public void render(float deltaTime) {
		super.render(deltaTime);
		fpsLogger.log();
		batch.setProjectionMatrix(camera.combined);
		checkInputs(deltaTime);
		wr.render(batch, world, deltaTime);
	}


	/**
	* À chaque frame, on regardes les inputs.
	*/
	private void checkInputs(float deltaTime) {
		float modX = 0, modY = 0;
		// go to top
		world.getJoueur().setMoved(false);
		if(Gdx.input.isKeyPressed(Keys.W)) {
			modY += 84 * deltaTime;
			world.getJoueur().setDirection(Direction.HAUT);
			world.getJoueur().setMoved(true);
		}
		// go to left 
		if(Gdx.input.isKeyPressed(Keys.A)) {
			modX -= 84 * deltaTime;
			world.getJoueur().setDirection(Direction.GAUCHE);
			world.getJoueur().setMoved(true);
		}
		// go to bottom 
		if(Gdx.input.isKeyPressed(Keys.S)) {
			modY -= 84 * deltaTime;
			world.getJoueur().setDirection(Direction.BAS);
			world.getJoueur().setMoved(true);
		}
		// go to down 
		if(Gdx.input.isKeyPressed(Keys.D)) {
			modX += 84 * deltaTime;
			world.getJoueur().setDirection(Direction.DROITE);
			world.getJoueur().setMoved(true);
		}

		world.getJoueur().setX(world.getJoueur().getX() + modX);
		world.getJoueur().setY(world.getJoueur().getY() + modY);
	}

	@Override
	public void dispose () {
		batch.dispose();
	}
}
