package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class TankMaze extends Game {
	//batch
	private SpriteBatch batch;
	WorldRenderer wr;
	FPSLogger fpsLogger = new FPSLogger();
	OrthographicCamera camera;

	
	@Override
	public void create () {
		wr = new WorldRenderer();
		batch = new SpriteBatch();
		camera = new OrthographicCamera();
	}
	
	@Override
	public void resize(int width, int height) {
		camera.setToOrtho(false, width, height);
		camera.position.set (width / 2, height / 2, 0);
		camera.position.set (width / 2, height / 2, 0);
	}

	@Override
	public void render () {
		super.render();
		fpsLogger.log();
		batch.setProjectionMatrix(camera.combined);
		wr.render(batch);
			
	}

	@Override
	public void dispose () {
		batch.dispose();
	}
	

}
