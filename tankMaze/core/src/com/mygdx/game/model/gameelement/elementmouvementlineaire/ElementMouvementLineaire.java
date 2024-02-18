package com.mygdx.game.model.gameelement.elementmouvementlineaire;

import com.mygdx.game.controller.Direction;
import com.mygdx.game.model.gameelement.GameElement;

public class ElementMouvementLineaire extends GameElement {

    private Direction direction;
    private float speed;

    public Direction getDirection() {
        return direction;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }
}
