package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Json;
import com.mygdx.game.gameelement.GameElement;
import com.mygdx.game.gameelement.Mur;


public class World {
	private GameElement[][] grid = {{new Mur()}};
	
	public World() {
		FileHandle file = Gdx.files.local("world.json");
		String text = file.readString();
		Json json = new Json();
		grid = json.fromJson(grid.getClass(), text);
	}
	
	public GameElement[][] getGrid() {
		return grid;
	}
}
