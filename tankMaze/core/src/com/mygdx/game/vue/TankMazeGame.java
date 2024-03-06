package com.mygdx.game.vue;

import com.badlogic.gdx.Game;
import com.mygdx.game.controller.WorldRenderer;

public class TankMazeGame extends Game {

	WorldRenderer wr = new WorldRenderer();

	@Override
	public void create() {
		TankMaze tankMaze = new TankMaze();
		tankMaze.setWorldRenderer(wr);
		wr.setGame(this);
		setScreen(tankMaze);
	}
}
