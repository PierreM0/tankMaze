package com.mygdx.game.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.model.World;
import com.mygdx.game.model.gameelement.*;
import com.mygdx.game.model.gameelement.elementdynamique.TankJoueur;
import com.mygdx.game.model.gameelement.elementmouvementlineaire.Obus;
import com.mygdx.game.model.gameelement.elementstatique.elementdur.ElementDur;
import com.mygdx.game.vue.TankMaze;
import com.mygdx.game.vue.TextureFactory;

/**
 * The main vue class. Get called by {@link TankMaze}.
 */
public class WorldRenderer {

    private boolean showDebug = false;
	
    private World world = new World();
    private double ANIM_HERTZ = 0.25;
    private float playerAnimationTimeWait = 0;
    private int playerAnimationTexture = 0;

    private static float SCALER = TextureFactory.IMG_SZ / 2F;
    /**
     * Render the whole world to the screen. Is called eatch frame.
     *
     * @param batch the SpriteBatch from the Game.
     * @param deltaTime time elapsed from last frame.
     */
    public void render(SpriteBatch batch, float deltaTime, ShapeRenderer sr) {

        world.deleteUnreachableObus();
        checkPlayerInputs(deltaTime);


        batch.begin();
        GameElement[][] grid = world.getGrid();

        for (int x = 0; x < grid.length; ++x) {
            for (int y = 0; y < grid[x].length; ++y) {
                TextureRegion tRegion;
                tRegion = TextureFactory.getTextureFromGameElement(grid[x][y])[0];
                batch.draw(tRegion,
                        grid[x][y].getX() * SCALER,
                        grid[x][y].getY() * SCALER,
                        grid[x][y].getWidth()  * SCALER,
                        grid[x][y].getHeight() * SCALER);
            }
        }

        TextureRegion obusTexture = TextureFactory.getInstance().getObus();
        for (int i = 0; i < world.getObus().size(); ++i) {

            Obus o = world.getObus().get(i);
            o.move(deltaTime);

            Sprite obus = new Sprite(obusTexture);

            obus.rotate(o.getDirection().toAngle());
            obus.setPosition((o.getX()-.5f) * SCALER , (o.getY()-.5f) * SCALER);
            obus.setScale(0.5F);
            obus.draw(batch);
        }

        TextureRegion[] joueurTextureRegion = TextureFactory.getInstance().getJoueur();
        if (world.getJoueur().moved())
            playerAnimationTimeWait += deltaTime;

        if (playerAnimationTimeWait > ANIM_HERTZ) {
            playerAnimationTimeWait -= ANIM_HERTZ;
            playerAnimationTexture = (playerAnimationTexture + 1) % joueurTextureRegion.length;
        }

        Sprite joueur = new Sprite(joueurTextureRegion[playerAnimationTexture]);

        joueur.rotate(world.getJoueur().getDirection().toAngle());
        joueur.setPosition((world.getJoueur().getX() -0.5F) * SCALER,
                (world.getJoueur().getY() -0.5F) * SCALER);
        joueur.setScale(0.5F);
        joueur.draw(batch);

        batch.end();

        sr.begin(ShapeRenderer.ShapeType.Line);
        if (showDebug)
            showElementDurAndPlayerHitBox(sr);
        sr.end();
    }

    private void showElementDurAndPlayerHitBox(ShapeRenderer sr) {
        for(GameElement[] ges : world.getGrid()) {
            for (GameElement ge: ges) {
                if (ge instanceof ElementDur) {
                    sr.rect(ge.getHitbox().x*SCALER, ge.getHitbox().y*SCALER, ge.getHitbox().width*SCALER,
                            ge.getHitbox().height*SCALER);
                }
            }
        }
        for (Obus obus: world.getObus())  {
            sr.rect(obus.getHitbox().x*SCALER, obus.getHitbox().y*SCALER, obus.getHitbox().width*SCALER,
                    obus.getHitbox().height*SCALER);
        }
        TankJoueur joueur = world.getJoueur();
        sr.rect(joueur.getHitbox().x*SCALER, joueur.getHitbox().y*SCALER, joueur.getHitbox().width *SCALER
                , joueur.getHitbox().height * SCALER);
    }


    /**
     * À chaque frame, on regardes les inputs.
     */
    private void checkPlayerInputs(float deltaTime) {

        float modX = 0, modY = 0;
        Direction direction = null;
        boolean moved = false;

        // go to top
        TankJoueur joueur = world.getJoueur();
        joueur.setMoved(false);

        if (Gdx.input.isKeyJustPressed(Input.Keys.D)) {
            showDebug = !showDebug;
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            world.spawnNewBullet(joueur.getX(), joueur.getY(), joueur.getDirection());
        }
        // go up
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            modY += deltaTime;
            direction = Direction.HAUT;
            moved = true;
        }
        // go to left
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            modX -=  deltaTime;
            direction = Direction.GAUCHE;
            moved = true;
        }
        // go to bottom
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            modY -= deltaTime;
            direction = Direction.BAS;
            moved = true;
        }
        // go down
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            modX += deltaTime;
            direction = Direction.DROITE;
            moved = true;
        }
        world.updateJoueur(modX, modY, direction, moved);
    }
}
