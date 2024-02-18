package com.mygdx.game.model.gameelement.elementmouvementlineaire;

public class Obus extends ElementMouvementLineaire {
    @Override
    public void setX(float x) {
        getHitbox().x = x;
    }

    @Override
    public void setY(float y) {
        getHitbox().y = y;
    }

    public void move(float deltatime) {
        switch (this.getDirection()) {
            case DROITE:
                this.setX(this.getX() + 0.1F * this.getSpeed() * deltatime);
                break;
            case HAUT:
                this.setY(this.getY() + 0.1F * this.getSpeed() * deltatime);
                break;
            case GAUCHE:
                this.setX(this.getX() - 0.1F * this.getSpeed() * deltatime);
                break;
            case BAS:
                this.setY(this.getY() - 0.1F * this.getSpeed() * deltatime);
                break;
            default:
                throw new RuntimeException("Unreachable");
        }
    }
}
