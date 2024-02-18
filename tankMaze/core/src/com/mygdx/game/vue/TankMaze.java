package com.mygdx.game.vue;

import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mygdx.game.controller.WorldRenderer;

/**
 * TankMaze is the main controller class.
 */
public class TankMaze extends ScreenAdapter {

    WorldRenderer wr;
    ShapeRenderer sr;
    FPSLogger fpsLogger = new FPSLogger();
    OrthographicCamera camera;
    private final SpriteBatch batch;


    public TankMaze() {
        wr = new WorldRenderer();
        batch = new SpriteBatch();
        camera = new OrthographicCamera();
        sr = new ShapeRenderer();
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
        wr.render(batch, deltaTime, sr);
    }


    @Override
    public void dispose() {
        batch.dispose();
    }
}
