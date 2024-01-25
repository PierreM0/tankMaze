package com.mygdx.game.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Json;
import com.mygdx.game.model.gameelement.GameElement;
import com.mygdx.game.model.gameelement.Mur;


/**
 * World is the main model class, it stores all the game data.
 * It auto-load the config from "assets/config/" at instantiation.
 */
public class World {
	private GameElement[][] grid = {{new Mur()}};
	private float[] playerPosition = { 0, 0};
	

	/**
	 * Instantiate a World, load all the needed files.
	 */
	public World() {
		Json json = new Json();

		String text = Gdx.files.local("config/world.json").readString();
		grid = json.fromJson(grid.getClass(), text);

		text = Gdx.files.local("config/player_start.json").readString();
		playerPosition = json.fromJson(playerPosition.getClass(), text);
	}
	
	public GameElement[][] getGrid() {
		return grid;
	}

	public float[] getPlayerPosition() {
		return playerPosition;
	}

	/**
	 * this is a setter for the player position
	 *
	 * @param x new x
	 * @param y new y
	 */
	public void setPlayerPosition(float x, float y) {
		playerPosition[0] = x;
		playerPosition[1] = y;
	}
}
