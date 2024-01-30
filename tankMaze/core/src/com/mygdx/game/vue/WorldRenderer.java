package com.mygdx.game.vue;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.model.World;
import com.mygdx.game.model.gameelement.GameElement;
import com.mygdx.game.vue.textureCOR.MurTextureCOR;
import com.mygdx.game.vue.textureCOR.PlayerTextureCOR;
import com.mygdx.game.vue.textureCOR.SolTextureCOR;
import com.mygdx.game.vue.textureCOR.TextureCOR;

/**
 *  The main vue class. Get called by {@link com.mygdx.game.controller.TankMaze}.
 *
 */
public class WorldRenderer {

	int frameCounter = 0;
	
	
	/**
	 * Render the whole world to the screen. Is called eatch frame.
	 *
	 * @param batch the SpriteBatch from the Game.
	 * @param world the current world.
	 */
	public void render(SpriteBatch batch, World world) {
		batch.begin();
		GameElement[][] grid = world.getGrid();
		float[] playerPosition = world.getPlayerPosition();

		TextureCOR cor = new SolTextureCOR(null);
		cor = new MurTextureCOR(cor);
		cor = new PlayerTextureCOR(cor);
		

		for (int x = 0; x < grid.length; ++x) {
			for (int y = 0; y < grid[0].length; ++y) {
				Sprite s;
				s = cor.getSpriteFromGameElement(grid[x][y])[0];
				s.setPosition(x * TextureFactory.IMG_SZ, y * TextureFactory.IMG_SZ);
				s.draw(batch);
			}
		}

		Sprite[] joueurSprites = TextureFactory.getInstance().getJoueur();
		Sprite joueur = joueurSprites[0 /* frameCounter % joueurSprites.length */];
		joueur.setPosition(playerPosition[0], playerPosition[1]);
		joueur.draw(batch);

		batch.end();
		frameCounter += 1;
	}
	
	

}
