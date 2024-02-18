package com.mygdx.game.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Json;
import com.mygdx.game.controller.Direction;
import com.mygdx.game.model.gameelement.GameElement;
import com.mygdx.game.model.gameelement.elementdynamique.TankJoueur;
import com.mygdx.game.model.gameelement.elementdynamique.TankNpc;
import com.mygdx.game.model.gameelement.elementmouvementlineaire.Obus;
import com.mygdx.game.model.gameelement.elementstatique.elementdur.ElementDur;
import jdk.jfr.internal.test.WhiteBox;

import java.util.ArrayList;


/**
 * World is the main model class, it stores all the game data.
 * It auto-load the config from "assets/config/" at instantiation.
 */
public class World {

    float bulletSpeed = 0;
    int bulletMax = 0;
    private GameElement[][] grid = {};
    private TankJoueur joueur = new TankJoueur();

    private ArrayList<TankNpc> npcs = new ArrayList<TankNpc>();
    private ArrayList<Obus> obus = new ArrayList<Obus>();

    /**
     * Instantiate a World, load all the needed files.
     */
    public World() {
        Json json = new Json();

        String text = Gdx.files.local("config/world.json").readString();
        grid = json.fromJson(grid.getClass(), text);


        for (int i = 0; i < grid.length; ++i) {
            for (int j = 0; j < grid[i].length; ++j) {
                grid[i][j].setX(j);
                grid[i][j].setY(i);
            }
        }

        text = Gdx.files.local("config/player_start.json").readString();
        float[] playerPosition = {};
        playerPosition = json.fromJson(playerPosition.getClass(), text);
        joueur.setX(playerPosition[0]);
        joueur.setY(playerPosition[1]);

        text = Gdx.files.local("config/player_speed.json").readString();
        float playerSpeed = 0;
        playerSpeed = json.fromJson(float.class, text);
        joueur.setVitesse(playerSpeed);

        text = Gdx.files.local("config/player-width-height.json").readString();
        float[] playerWidthHeight = {};
        playerWidthHeight = json.fromJson(playerWidthHeight.getClass(), text);
        joueur.setWidth(playerWidthHeight[0]);
        joueur.setHeight(playerWidthHeight[1]);


        text = Gdx.files.local("config/obus-max.json").readString();
        bulletMax = json.fromJson(int.class, text);

        text = Gdx.files.local("config/obus-speed.json").readString();
        bulletSpeed = json.fromJson(float.class, text);


        text = Gdx.files.local("config/npc_start.json").readString();

        float[][] npcPos = {};
        npcPos = json.fromJson(npcPos.getClass(), text);
        for (float[] npcPo : npcPos) {
            TankNpc npc = new TankNpc();
            npc.setX(npcPo[0]);
            npc.setY(npcPo[1]);
            npcs.add(npc);
        }
    }

    public void spawnNewBullet(float x, float y, Direction direction) {
        Obus bullet = new Obus();
        bullet.setX(x);
        bullet.setSpeed(bulletSpeed);
        bullet.setY(y);
        bullet.setDirection(direction);
        bullet.setWidth(1);
        bullet.setHeight(1);
        if (obus.size() < bulletMax)
            obus.add(bullet);
    }

    public ArrayList<Obus> getObus() {
        return obus;
    }

    public void deleteUnreachableObus() {
        for(int i = 0; i < obus.size(); ++i) {
            if (obus.get(i).getX() < -.5 || obus.get(i).getY() < -.5 || obus.get(i).getX() > 20 || obus.get(i).getY() > 20)
                obus.remove(obus.get(i));
        }
    }

    public GameElement[][] getGrid() {
        return grid;
    }

    public TankJoueur getJoueur() {
        return joueur;
    }

    public void updateJoueur(float modX, float modY, Direction direction, boolean moved) {

        modX *= joueur.getVitesse();
        modY *= joueur.getVitesse();

        if (direction != null)
            joueur.setDirection(direction);
        joueur.setMoved(moved);

        float newX = getJoueur().getX() + modX;
        float newY = getJoueur().getY() + modY;

        Rectangle newPlayerHitBox = new Rectangle(newX, newY, joueur.getWidth(), joueur.getHeight());

        Rectangle wallPos;

        boolean canMove = true;
        for(GameElement[] ges : getGrid()) {
            for (GameElement ge: ges) {
                if (ge instanceof ElementDur && ge.getHitbox().overlaps(newPlayerHitBox)) {
                    canMove = false;
                    wallPos = ge.getHitbox();
                }
            }
        }

        if (canMove) {
            getJoueur().setX(newX);
            getJoueur().setY(newY);
        }
    }
}
