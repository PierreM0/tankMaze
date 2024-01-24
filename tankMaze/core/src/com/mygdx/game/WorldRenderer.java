package com.mygdx.game;

import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.gameelement.GameElement;
import com.mygdx.game.gameelement.Mur;
import com.mygdx.game.gameelement.tank.TankJoueur;
import com.mygdx.game.textureCOR.MurTextureCOR;
import com.mygdx.game.textureCOR.PlayerTextureCOR;
import com.mygdx.game.textureCOR.SolTextureCOR;
import com.mygdx.game.textureCOR.TextureCOR;

public class WorldRenderer {

	private TextureFactory tf;
	private World world;

	
	public WorldRenderer() {
		world = new World();
		tf = TextureFactory.getInstance();
	}
	
	
	public void render(SpriteBatch batch) {
		batch.begin();
		GameElement[][] grid = world.getGrid();
		
		TextureCOR cor = new SolTextureCOR(null);
		cor = new MurTextureCOR(cor);
		cor = new PlayerTextureCOR(cor);
		
		
		for (int x = 0; x < grid.length; ++x) {
			for (int y = 0; y < grid[0].length; ++y) {
				Sprite s;
				s = cor.getSpriteFromGameElement(grid[x][y]).get(0);
				s.setPosition(x * TextureFactory.IMG_SZ, y * TextureFactory.IMG_SZ);
				s.draw(batch);
			}
		}
		
		batch.end();
	}
	
	

}
