package com.mygdx.game.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Json;
import com.mygdx.game.model.gameelement.GameElement;
import com.mygdx.game.model.gameelement.Mur;
import com.mygdx.game.model.gameelement.tank.TankJoueur;


/**
 * World is the main model class, it stores all the game data.
 * It auto-load the config from "assets/config/" at instantiation.
 */
public class World {
	private GameElement[][] grid = {{new Mur()}};
	private TankJoueur joueur = new TankJoueur();
	

	/**
	 * Instantiate a World, load all the needed files.
	 */
	public World() {
		Json json = new Json();

		String text = Gdx.files.local("config/world.json").readString();
		grid = json.fromJson(grid.getClass(), text);

		text = Gdx.files.local("config/player_start.json").readString();
		float[] playerPosition = {};
		playerPosition = json.fromJson(playerPosition.getClass(), text);
		joueur.setX(playerPosition[0]);
		joueur.setY(playerPosition[1]);
	}
	
	public GameElement[][] getGrid() {
		return grid;
	}

	public TankJoueur getJoueur() {
		return joueur;
	}
}
