package com.mygdx.game.model.gameelement;

import com.badlogic.gdx.math.Rectangle;

/**
 * The GameElement are the Object that compose the game.
 * From the players to the ground.
 */
public abstract class GameElement {

	Rectangle hitbox = new Rectangle();

	public Rectangle getHitbox() {
		return hitbox;
	}

	private float width, height;
	
	public float getWidth() {
		return hitbox.width;
	}
	
	public float getHeight() {
		return hitbox.height;
	}
	
	public void setWidth(float width) {
		this.hitbox.width = width;
	}	
	
	public void setHeight(float height) {
		this.hitbox.height = height;
	}


	public float getX() {
		return hitbox.x;
	}
	public float getY() {
		return hitbox.y;
	}
	public void setX(float x) {
		this.hitbox.x = x;
	}
	public void setY(float y) {
		this.hitbox.y = y;
	}
}
