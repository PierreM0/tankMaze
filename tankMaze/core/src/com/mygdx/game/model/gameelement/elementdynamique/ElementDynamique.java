package com.mygdx.game.model.gameelement.elementdynamique;

import com.mygdx.game.controller.Direction;
import com.mygdx.game.model.World;
import com.mygdx.game.model.gameelement.GameElement;

/**
 * The tanks are the playable and non-playable character.
 *
 */
public class ElementDynamique extends GameElement {
	
	private boolean moved = false, canMove = false;
	Direction direction = Direction.HAUT;
	private float vitesse = 0;

	public float getVitesse() {
	    return vitesse;
	}

	public void setVitesse(float vitesse) {
	    this.vitesse = vitesse;
	}

	public Direction getDirection() {
	    return direction;
	}
	public void setDirection(Direction direction) {
	    this.direction = direction;
	}


	public boolean canMove() {
		return canMove;
	}

	public void setCanMove(boolean canMove) {
		this.canMove = canMove;
	}

	public boolean moved() { return moved; }
	public void setMoved(boolean m) { moved = m; } 
}
