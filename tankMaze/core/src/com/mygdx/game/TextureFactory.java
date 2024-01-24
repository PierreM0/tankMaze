package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

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
	
	public Sprite getSol() {
		return new Sprite(texture, 0, 0, IMG_SZ, IMG_SZ);
	}
	
	public Sprite getJoueur1() {
		return new Sprite(texture,0 , IMG_SZ, IMG_SZ, IMG_SZ);
	}
	
	public Sprite getMur1() {
		return new Sprite(texture, IMG_SZ*2, IMG_SZ*3, IMG_SZ, IMG_SZ);
	}
}
