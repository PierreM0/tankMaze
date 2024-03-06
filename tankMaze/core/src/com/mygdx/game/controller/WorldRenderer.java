package com.mygdx.game.controller;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mygdx.game.model.World;
import com.mygdx.game.model.gameelement.GameElement;
import com.mygdx.game.model.gameelement.elementdynamique.Avion;
import com.mygdx.game.model.gameelement.elementdynamique.TankJoueur;
import com.mygdx.game.model.gameelement.elementdynamique.TankNpc;
import com.mygdx.game.model.gameelement.elementmouvementlineaire.Obus;
import com.mygdx.game.model.gameelement.elementstatique.Explosion;
import com.mygdx.game.model.gameelement.elementstatique.Recompenses;
import com.mygdx.game.model.gameelement.elementstatique.elementdur.ElementDur;
import com.mygdx.game.vue.TankMaze;
import com.mygdx.game.vue.TankMazeEndScreen;
import com.mygdx.game.vue.TextureFactory;

/**
 * The main vue class. Get called by {@link TankMaze}.
 */
public class WorldRenderer {

    private boolean showDebug = false;
    Game game = null;

    float totalTimePlayed = 0;
    public void setGame(Game game) {
        this.game = game;
    }

    public final World world = new World();
    private float playerAnimationTimeWait = 0;
    private int playerAnimationTexture = 0;

    private static final float SCALAR = TextureFactory.IMG_SZ / 2F;
    /**
     * Render the whole world to the screen. Is called eatch frame.
     *
     * @param batch the SpriteBatch from the Game.
     * @param deltaTime time elapsed from last frame.
     */
    public void render(SpriteBatch batch, float deltaTime, ShapeRenderer sr, BitmapFont font) {
        totalTimePlayed += deltaTime;

        world.deleteUnreachableObus();
        checkPlayerInputs(deltaTime);


        batch.begin();
        GameElement[][] grid = world.getGrid();

        for (GameElement[] gameElements : grid) {
            for (GameElement gameElement : gameElements) {
                TextureRegion tRegion;
                tRegion = TextureFactory.getTextureFromGameElement(gameElement)[0];
                batch.draw(tRegion,
                        gameElement.getX() * SCALAR,
                        gameElement.getY() * SCALAR,
                        gameElement.getWidth() * SCALAR,
                        gameElement.getHeight() * SCALAR);
            }
        }

        TextureRegion obusTexture = TextureFactory.getInstance().getObus();
        for (Obus o : world.getObus())  {
            Sprite obus = new Sprite(obusTexture);

            obus.rotate(o.getDirection().toAngle());
            obus.setPosition((o.getX()-.5f) * SCALAR, (o.getY()-.5f) * SCALAR);
            obus.setScale(0.5F);
            obus.draw(batch);
        }
        for (Obus o : world.getObusNpc())  {
            Sprite obus = new Sprite(obusTexture);

            obus.rotate(o.getDirection().toAngle());
            obus.setPosition((o.getX()-.5f) * SCALAR, (o.getY()-.5f) * SCALAR);
            obus.setScale(0.5F);
            obus.draw(batch);
        }


        for (Recompenses r : world.getRecompenses())  {
            Sprite rec = new Sprite(TextureFactory.getInstance().getEtoile());

            rec.setPosition((r.getX()-.5f) * SCALAR, (r.getY()-.5f) * SCALAR);
            rec.setScale(0.5F);
            rec.draw(batch);
        }


        TextureRegion[] joueurTextureRegion = TextureFactory.getInstance().getJoueur();
        if (world.getJoueur().moved())
            playerAnimationTimeWait += deltaTime;

        double ANIM_HERTZ = 0.25;
        if (playerAnimationTimeWait > ANIM_HERTZ) {
            playerAnimationTimeWait -= (float) ANIM_HERTZ;
            playerAnimationTexture = (playerAnimationTexture + 1) % joueurTextureRegion.length;
        }
        Sprite joueur = new Sprite(joueurTextureRegion[playerAnimationTexture]);


        joueur.rotate(world.getJoueur().getDirection().toAngle());
        joueur.setPosition((world.getJoueur().getX() -0.5F) * SCALAR,
                (world.getJoueur().getY() -0.5F) * SCALAR);
        joueur.setScale(0.5F);
        joueur.draw(batch);

        TextureRegion[] explosionTextureRegion = TextureFactory.getInstance().getExplosion();
        for (Explosion e: world.getExplosions()) {
            e.waited += deltaTime;
            if (e.waited > ANIM_HERTZ) {
                e.waited -= (float) ANIM_HERTZ;
                e.stage += 1;
            }
            Sprite explosion = new Sprite(explosionTextureRegion[e.stage % 3]);
            explosion.setPosition(SCALAR*(e.getX()-0.5f), SCALAR*(e.getY() -0.5F));
            explosion.setScale(0.5F);
            explosion.draw(batch);
        }

        batch.draw(TextureFactory.getInstance().getAigle(),
               SCALAR * world.getTrophee().getX(),
               SCALAR * world.getTrophee().getY(),
               SCALAR * world.getTrophee().getWidth(),
               SCALAR * world.getTrophee().getHeight()
                );

        for (Avion a: world.getAvions()) {
            Sprite plane = new Sprite(TextureFactory.getInstance().getAvion());
            plane.setPosition((a.getX() - 0.5f) * SCALAR,
                    (a.getY() - 0.5F) * SCALAR);
            plane.setScale(0.5f);
            plane.draw(batch);
        }

        TextureRegion[] texNpcs = TextureFactory.getInstance().getNpc();
        for (TankNpc tankNpc: world.getNpcs()) {
            Sprite npc = new Sprite(texNpcs[0]);
            npc.setScale(0.5F);
            npc.setPosition((tankNpc.getX() -0.5F) * SCALAR,
                    (tankNpc.getY() -0.5F) * SCALAR);
            npc.rotate(tankNpc.getDirection().toAngle());
            npc.draw(batch);
        }

        font.draw(batch, "Vie Restante : "+world.getVie(), 20, 20);
        font.draw(batch, "Recompenses restantes : "+world.getRecompenses().size(), 20, 35);
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
                    sr.rect(ge.getHitbox().x* SCALAR, ge.getHitbox().y* SCALAR, ge.getHitbox().width* SCALAR,
                            ge.getHitbox().height* SCALAR);
                }
            }
        }
        for (Obus obus: world.getObus())  {
            sr.rect(obus.getHitbox().x* SCALAR, obus.getHitbox().y* SCALAR, obus.getHitbox().width* SCALAR,
                    obus.getHitbox().height* SCALAR);
        }
        for (Obus obus: world.getObusNpc())  {
            sr.rect(obus.getHitbox().x* SCALAR, obus.getHitbox().y* SCALAR, obus.getHitbox().width* SCALAR,
                    obus.getHitbox().height* SCALAR);
        }
        TankJoueur joueur = world.getJoueur();
        sr.rect(joueur.getHitbox().x* SCALAR, joueur.getHitbox().y* SCALAR, joueur.getHitbox().width * SCALAR
                , joueur.getHitbox().height * SCALAR);

        for (TankNpc npc: world.getNpcs()) {
            sr.rect(npc.getHitbox().x* SCALAR, npc.getHitbox().y* SCALAR, npc.getHitbox().width* SCALAR,
                    npc.getHitbox().height* SCALAR);
        }
        for (Avion avion: world.getAvions()) {
            sr.rect(avion.getHitbox().x* SCALAR, avion.getHitbox().y* SCALAR, avion.getHitbox().width* SCALAR,
                    avion.getHitbox().height* SCALAR);
        }
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
        world.updateObus(deltaTime);
        world.updateExplosions();
        world.checkNpc(deltaTime);

        if (world.getRecompenses().isEmpty()) {
            if (world.getJoueur().getHitbox().overlaps(world.getTrophee().getHitbox())) {
                game.setScreen(new TankMazeEndScreen(true, totalTimePlayed));
            }
        }

        if (world.getVie() < 0) {
            game.setScreen(new TankMazeEndScreen(false, 0));
        }
    }
}
