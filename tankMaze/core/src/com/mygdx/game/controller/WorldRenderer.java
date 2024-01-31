<<<<<<< Updated upstream
=======
package com.mygdx.game.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.model.World;
import com.mygdx.game.model.gameelement.*;
import com.mygdx.game.model.gameelement.elementdynamique.TankJoueur;
import com.mygdx.game.model.gameelement.elementstatique.ElementVide;
import com.mygdx.game.model.gameelement.elementstatique.MurBrique;
import com.mygdx.game.model.gameelement.elementstatique.MurFer;
import com.mygdx.game.model.gameelement.elementstatique.Vegetation;
import com.mygdx.game.vue.TankMaze;
import com.mygdx.game.vue.TextureFactory;

/**
 * The main vue class. Get called by {@link TankMaze}.
 */
public class WorldRenderer {
	
    World world = new World();
    static final double ANIM_HERTZ = 0.25;
    float playerAnimationTimeWait = 0;
    int playerAnimationTexture = 0;

    /**
     * Render the whole world to the screen. Is called eatch frame.
     *
     * @param batch the SpriteBatch from the Game.
     * @param deltaTime time elapsed from last frame.
     */
    public void render(SpriteBatch batch, float deltaTime) {
        checkInputs(deltaTime);

        batch.begin();
        GameElement[][] grid = world.getGrid();

        for (int x = grid.length - 1; x >= 0; --x) {
            for (int y = 0; y < grid[0].length; ++y) {
                TextureRegion tRegion;
                tRegion = getTextureFromGameElement(grid[x][y])[0];
                batch.draw(tRegion, (y * TextureFactory.IMG_SZ)/2F,
                        (grid.length - 1 - x) * TextureFactory.IMG_SZ /2F,
                        grid[x][y].getWidth() / 2F,
                        grid[x][y].getHeight() / 2F);
            }
        }

        TextureRegion[] joueurTextureRegion = TextureFactory.getInstance().getJoueur();
        if (world.getJoueur().moved())
            playerAnimationTimeWait += deltaTime;

        if (playerAnimationTimeWait > ANIM_HERTZ) {
            playerAnimationTimeWait -= ANIM_HERTZ;
            playerAnimationTexture = (playerAnimationTexture + 1) % joueurTextureRegion.length;
        }

        Sprite joueur = new Sprite(joueurTextureRegion[playerAnimationTexture]);

        joueur.setPosition(world.getJoueur().getX(), world.getJoueur().getY());
        joueur.rotate(directionToAngle(world.getJoueur().getDirection()));
        joueur.setScale(0.5F);
        joueur.draw(batch);
        batch.end();
    }



    private float directionToAngle(Direction direction) {
        switch (direction) {
            case HAUT:
                return 0;
            case GAUCHE:
                return 90;
            case BAS:
                return 180;
            case DROITE:
                return 270;
            default:
                throw new RuntimeException("Unreachable");
        }
    }


    /**
     * À chaque frame, on regardes les inputs.
     */
    private void checkInputs(float deltaTime) {
        float modX = 0, modY = 0;
        // go to top
        world.getJoueur().setMoved(false);
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            modY += 84 * deltaTime;
            world.getJoueur().setDirection(Direction.HAUT);
            world.getJoueur().setMoved(true);
        }
        // go to left
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            modX -= 84 * deltaTime;
            world.getJoueur().setDirection(Direction.GAUCHE);
            world.getJoueur().setMoved(true);
        }
        // go to bottom
        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            modY -= 84 * deltaTime;
            world.getJoueur().setDirection(Direction.BAS);
            world.getJoueur().setMoved(true);
        }
        // go to down
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            modX += 84 * deltaTime;
            world.getJoueur().setDirection(Direction.DROITE);
            world.getJoueur().setMoved(true);
        }

        world.getJoueur().setX(world.getJoueur().getX() + modX);
        world.getJoueur().setY(world.getJoueur().getY() + modY);
    }

	
    private TextureRegion[] getTextureFromGameElement(GameElement ge) {
    	if (ge instanceof MurBrique) {
    		return TextureFactory.getInstance().getMurBrique1x1();
    	} else if (ge instanceof MurFer) {
    		return TextureFactory.getInstance().getMurFer();
    	} else if (ge instanceof TankJoueur) {
    		return TextureFactory.getInstance().getJoueur();
    	} else if (ge instanceof Vegetation) {
    		return TextureFactory.getInstance().getVegetation();
    	} else if (ge instanceof ElementVide) {
    		return TextureFactory.getInstance().getSol();
    	} else 
    	return null;
    }
}
>>>>>>> Stashed changes
