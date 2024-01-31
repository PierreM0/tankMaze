package com.mygdx.game.vue;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.controller.Direction;
import com.mygdx.game.model.World;
import com.mygdx.game.model.gameelement.GameElement;
import com.mygdx.game.vue.textureCOR.*;

/**
 *  The main vue class. Get called by {@link com.mygdx.game.controller.TankMaze}.
 *
 */
public class WorldRenderer {

	float playerAnimationTimeWait = 0;
	int playerAnimationTexture = 0;
	
	static final double ANIM_HERTZ = 0.25;
	
	/**
	 * Render the whole world to the screen. Is called eatch frame.
	 *
	 * @param batch the SpriteBatch from the Game.
	 * @param world the current world.
	 */
	public void render(SpriteBatch batch, World world, float deltaTime) {
		batch.begin();
		GameElement[][] grid = world.getGrid();

		TextureCOR cor = new SolTextureCOR(null);
		cor = new MurTextureCOR(cor);
		cor = new PlayerTextureCOR(cor);
		cor = new TrappeTextureCOR(cor);
		cor = new Mur90TextureCOR(cor);


		for (int x = grid.length -1; x >= 0; --x) {
			for (int y = 0; y < grid[0].length; ++y) {
				Sprite s;
				s = cor.getSpriteFromGameElement(grid[x][y])[0];
				s.setPosition(y * TextureFactory.IMG_SZ, (grid.length -1 - x) * TextureFactory.IMG_SZ);
				s.draw(batch);
			}
		}

		Sprite[] joueurSprites = TextureFactory.getInstance().getJoueur();
		if (world.getJoueur().moved())
			playerAnimationTimeWait += deltaTime;

		if (playerAnimationTimeWait > ANIM_HERTZ) {
			playerAnimationTimeWait -= ANIM_HERTZ;
			playerAnimationTexture = (playerAnimationTexture + 1) % joueurSprites.length;
		}

		Sprite joueur = joueurSprites[playerAnimationTexture];

		joueur.setPosition(world.getJoueur().getX(), world.getJoueur().getY());
		joueur.rotate(directionToAngle(world.getJoueur().getDirection()));
		joueur.draw(batch);

		batch.end();
	}
	
	private float directionToAngle(Direction direction) {
		switch (direction){
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
	

}
