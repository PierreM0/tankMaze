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
		if (x > 0 && x <= 19)
			this.hitbox.x = x;
	}
	public void setY(float y) {
		if (y > 0 && y <= 19)
			this.hitbox.y = y;
	}
}
