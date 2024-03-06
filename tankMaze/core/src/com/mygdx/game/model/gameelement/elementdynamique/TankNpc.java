package com.mygdx.game.model.gameelement.elementdynamique;

import com.badlogic.gdx.math.Rectangle;

public class TankNpc extends ElementDynamique {
    public boolean dansAlignement(ElementDynamique joueur) {
        Rectangle rectangleJoueur = new Rectangle(joueur.getHitbox());
        Rectangle rectangleThis = new Rectangle(this.getHitbox());

        rectangleJoueur.y = 0;
        rectangleThis.y = 0;
        if (rectangleJoueur.overlaps(rectangleThis)) return true;

        rectangleJoueur = new Rectangle(joueur.getHitbox());
        rectangleThis = new Rectangle(this.getHitbox());

        rectangleJoueur.x = 0;
        rectangleThis.x = 0;
        return rectangleJoueur.overlaps(rectangleThis);
    }
}
