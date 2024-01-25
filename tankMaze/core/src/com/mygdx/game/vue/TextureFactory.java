package com.mygdx.game.vue;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 * A singleton that hold the textures and process them to sprite.
 *
 */
public class TextureFactory {
	static public final int IMG_SZ = 84;
	static private TextureFactory instance = null;
	private Texture texture;
	
	private TextureFactory() {
		texture = new Texture(Gdx.files.local("texturemap.png"));
	}
	
	static public TextureFactory getInstance() {
		if (instance != null) {
			return instance;
		} else {
			instance = new TextureFactory();
			return instance;
		}
	}
	
	public Sprite[] getSol() {
		Sprite[] sprites = {
		new Sprite(texture, 0, 0, IMG_SZ, IMG_SZ)};
		return sprites;
	}
	
	public Sprite[] getJoueur() {
		Sprite[] sprites = {
			new Sprite(texture, IMG_SZ*1 , IMG_SZ*0, IMG_SZ, IMG_SZ),
			new Sprite(texture, IMG_SZ*2 , IMG_SZ*0, IMG_SZ, IMG_SZ),
			new Sprite(texture, IMG_SZ*3 , IMG_SZ*0, IMG_SZ, IMG_SZ),
			new Sprite(texture, IMG_SZ*4 , IMG_SZ*0, IMG_SZ, IMG_SZ),
			new Sprite(texture, IMG_SZ*5 , IMG_SZ*0, IMG_SZ, IMG_SZ),
			new Sprite(texture, IMG_SZ*6 , IMG_SZ*0, IMG_SZ, IMG_SZ),
			new Sprite(texture, IMG_SZ*7 , IMG_SZ*0, IMG_SZ, IMG_SZ),
			new Sprite(texture, IMG_SZ*0 , IMG_SZ*1, IMG_SZ, IMG_SZ)
		};
	
		return sprites ;
	}


	public Sprite[] getNPC() {
		Sprite[] sprites = {
			new Sprite(texture, IMG_SZ*1,IMG_SZ*1 ,  IMG_SZ, IMG_SZ),
			new Sprite(texture, IMG_SZ*2,IMG_SZ*1 ,  IMG_SZ, IMG_SZ),
			new Sprite(texture, IMG_SZ*3,IMG_SZ*1 ,  IMG_SZ, IMG_SZ),
			new Sprite(texture, IMG_SZ*4,IMG_SZ*1 ,  IMG_SZ, IMG_SZ),
			new Sprite(texture, IMG_SZ*5,IMG_SZ*1 ,  IMG_SZ, IMG_SZ),
			new Sprite(texture, IMG_SZ*6,IMG_SZ*1 ,  IMG_SZ, IMG_SZ),
			new Sprite(texture, IMG_SZ*7,IMG_SZ*1 ,  IMG_SZ, IMG_SZ),
			new Sprite(texture, IMG_SZ*0,IMG_SZ*2 ,  IMG_SZ, IMG_SZ)
		};
	
		return sprites ;
	}

	public Sprite[] getMur1x1() {
		Sprite[] sprites = {
			new Sprite(texture, IMG_SZ*2, IMG_SZ*3, IMG_SZ, IMG_SZ)
		};
		return sprites;
	}

	public Sprite[] getMur1x190d() {
		Sprite[] sprites = {
			new Sprite(texture, IMG_SZ*4, IMG_SZ*3, IMG_SZ, IMG_SZ)
		};
		return sprites;
	}

	public Sprite[] getMur2x1_1() {
		Sprite[] sprites = {
			new Sprite(texture, IMG_SZ*0, IMG_SZ*3, IMG_SZ, IMG_SZ)
		};
		return sprites;
	}

	public Sprite[] getMur2x1_2() {
		Sprite[] sprites = {
			new Sprite(texture, IMG_SZ*1, IMG_SZ*3, IMG_SZ, IMG_SZ)
		};
		return sprites;
	}

	public Sprite[] getMur1x2_1() {
		Sprite[] sprites = {
			new Sprite(texture, IMG_SZ*3, IMG_SZ*3, IMG_SZ, IMG_SZ)
		};
		return sprites;
	}

	public Sprite[] getMur1x2_2() {
		Sprite[] sprites = {
			new Sprite(texture, IMG_SZ*5, IMG_SZ*3, IMG_SZ, IMG_SZ)
		};
		return sprites;
	}

	public Sprite[] getBox() {
		Sprite[] sprites = {
			new Sprite(texture, IMG_SZ*6, IMG_SZ*3, IMG_SZ, IMG_SZ)
		};
		return sprites;
	}

	public Sprite[] getBox90() {
		Sprite[] sprites = {
			new Sprite(texture, IMG_SZ*7, IMG_SZ*3, IMG_SZ, IMG_SZ)
		};
		return sprites;
	}

	public Sprite[] getMiniJoueur() {
		Sprite[] sprites = {
			new Sprite(texture, IMG_SZ*6, IMG_SZ*2, IMG_SZ, IMG_SZ)
		};
		return sprites;
	}
	
	public Sprite[] getAigle() {
		Sprite[] sprites = {
			new Sprite(texture, IMG_SZ*7, IMG_SZ*2, IMG_SZ, IMG_SZ)
		};
		return sprites;
	}
	
	public Sprite[] getObus() {
		Sprite[] sprites = {
			new Sprite(texture, IMG_SZ*5, IMG_SZ*2, IMG_SZ, IMG_SZ)
		};
		return sprites;
	}

	public Sprite[] getBille() {
		Sprite[] sprites = {
			new Sprite(texture, IMG_SZ*4, IMG_SZ*2, IMG_SZ, IMG_SZ)
		};
		return sprites;
	}

	public Sprite[] getExplosion() {
		Sprite[] sprites = {
			new Sprite(texture, IMG_SZ*1, IMG_SZ*2, IMG_SZ, IMG_SZ),
			new Sprite(texture, IMG_SZ*2, IMG_SZ*2, IMG_SZ, IMG_SZ),
			new Sprite(texture, IMG_SZ*3, IMG_SZ*2, IMG_SZ, IMG_SZ)
		};
		return sprites;
	}


}
