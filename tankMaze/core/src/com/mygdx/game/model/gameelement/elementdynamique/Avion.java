package com.mygdx.game.model.gameelement.elementdynamique;

import com.badlogic.gdx.math.Vector2;

import java.util.Random;

public class Avion extends ElementDynamique {

    private Vector2 dir = new Vector2(0, 0);

    public void move() {
        setX(getX() + dir.x/100);
        setY(getY() + dir.y/100);
    }

    public void genDir() {
        Vector2 P = new Vector2(0, 0);
        Vector2 B = new Vector2(0, 0);

        P.x = (float)Math.random() * 20f;
        P.y = (float)Math.random() * 20f;

        B.x = (float)Math.random() * 20f;
        B.y = (float)Math.random() * 20f;

        setX((float)Math.random() * -20);
        setY((float)Math.random() * -20);
        dir = P.sub(B);
    }
}