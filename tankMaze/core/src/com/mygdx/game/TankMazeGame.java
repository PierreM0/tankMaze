package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.mygdx.game.controller.TankMaze;

public class TankMazeGame extends Game {

	@Override
	public void create() {
		setScreen(new TankMaze());	
	}
}
