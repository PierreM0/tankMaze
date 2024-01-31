package com.mygdx.game.model.gameelement;

/**
 * The GameElement are the Object that compose the game.
 * From the players to the ground.
 */
public abstract class GameElement {
	private float width, height;
	
	public float getWidth() {
		return width;
	}
	
	public float getHeight() {
		return height;
	}
	
	public void setWidth(float width) {
		this.width = width;
	}	
	
	public void setHeight(float height) {
		this.height = height;
	}
}
