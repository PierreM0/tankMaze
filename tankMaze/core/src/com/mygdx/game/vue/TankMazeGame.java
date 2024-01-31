package com.mygdx.game.vue;

import com.badlogic.gdx.Game;
import com.mygdx.game.vue.TankMaze;

public class TankMazeGame extends Game {

	@Override
	public void create() {
		setScreen(new TankMaze());	
	}
}
