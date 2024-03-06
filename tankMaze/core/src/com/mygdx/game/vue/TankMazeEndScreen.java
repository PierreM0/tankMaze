package com.mygdx.game.vue;

import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

/**
 * TankMaze is the main controller class.
 */
public class TankMazeEndScreen extends ScreenAdapter {

    SpriteBatch batch;
    FPSLogger fpsLogger = new FPSLogger();
    OrthographicCamera camera;
    ShapeRenderer shapeRenderer = new ShapeRenderer();
    BitmapFont font;

    boolean win;
    float totalTimePlayed;

    public TankMazeEndScreen(boolean win, float totalTimePlayed) {
        camera = new OrthographicCamera();
        font = new BitmapFont();
        batch = new SpriteBatch();
        this.win = win;
        this.totalTimePlayed = totalTimePlayed;
    }

    @Override
    public void resize(int width, int height) {
        camera.setToOrtho(false, width, height);
        camera.position.set(width / 2, height / 2, 0);
    }

    @Override
    public void render(float deltaTime) {
        super.render(deltaTime);
        font.setColor(Color.WHITE);
        fpsLogger.log();
        batch.setProjectionMatrix(camera.combined);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.BLACK);
        shapeRenderer.rect(0, 0, 1000, 1000);
        shapeRenderer.end();
        batch.begin();
        if (!win)
            font.draw(batch, "Pour recommencer, veuillez relancer le jeu", 100, 100);
        else {
            font.draw(batch, "Bravos, vous avez mis " + totalTimePlayed + "seconde pour terminer.", 100, 100);
        }
        batch.end();
    }


    @Override
    public void dispose() {
        batch.dispose();
    }
}
