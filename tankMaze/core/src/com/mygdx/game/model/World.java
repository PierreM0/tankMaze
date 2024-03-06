package com.mygdx.game.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Json;
import com.mygdx.game.controller.Direction;
import com.mygdx.game.model.gameelement.GameElement;
import com.mygdx.game.model.gameelement.elementdynamique.*;
import com.mygdx.game.model.gameelement.elementmouvementlineaire.Obus;
import com.mygdx.game.model.gameelement.elementstatique.ElementVide;
import com.mygdx.game.model.gameelement.elementstatique.Explosion;
import com.mygdx.game.model.gameelement.elementstatique.Recompenses;
import com.mygdx.game.model.gameelement.elementstatique.Trophee;
import com.mygdx.game.model.gameelement.elementstatique.elementdur.ElementDur;
import com.mygdx.game.model.gameelement.elementstatique.elementdur.MurBrique;
import com.mygdx.game.model.gameelement.elementstatique.elementdur.MurFer;

import java.util.ArrayList;

class Config {
    public float[][] npcStart;
    public int obusMax;
    public float obusSpeed;
    public int plane;
    public int playerWidth;
    public int playerHeight;
    public int playerSpeed;
    public int[] playerPos;
    public int vie;
    public int recompenses;
    public float[][] coordRecompenses;
    public float[] trophee;
}

/**
 * World is the main model class, it stores all the game data.
 * It auto-load the config from "assets/config/" at instantiation.
 */
public class World {

    float intervaleAvion = 0;
    float elapsedPlaneTime = 0;
    ArrayList<Recompenses> recompenses = new ArrayList<Recompenses>();
    float bulletSpeed = 0;
    int bulletMax = 0;
    private GameElement[][] grid = {};
    private TankJoueur joueur = new TankJoueur();
    private Trophee trophee = new Trophee();
    private ArrayList<TankNpc> npcs = new ArrayList<TankNpc>();
    private ArrayList<Obus> obus = new ArrayList<Obus>();
    private ArrayList<Obus> obusNpc = new ArrayList<Obus>();

    private ArrayList<Avion> avions = new ArrayList<Avion>();

    public ArrayList<Avion> getAvions() {
        return avions;
    }
    private ArrayList<Explosion> explosions = new ArrayList<Explosion>();

    public ArrayList<Explosion> getExplosions() {
        return explosions;
    }

    private int vie = 0;

    public int getVie() {
        return vie;
    }

    public void updateExplosions() {
        for (int i = 0; i < explosions.size(); ++i) {
            Explosion e = explosions.get(i);
            if (e.stage > 2)
                explosions.remove(e);
        }
    }

    public ArrayList<Obus> getObusNpc() {
        return obusNpc;
    }

    void remplacerGrid(int i, int j, GameElement remplacant) {
        GameElement ge = grid[i][j];
        remplacant.setY(ge.getY());
        remplacant.setX(ge.getX());
        remplacant.setWidth(ge.getWidth());
        remplacant.setHeight(ge.getHeight());
        grid[i][j] = remplacant;
    }

    private void addExplosion(Obus o) {
        Explosion e = new Explosion();
        e.setX(o.getX());
        e.setY(o.getY());
        explosions.add(e);
    }

    public void updateObusNpc(float deltaTime) {
        for (int i = 0; i < getObusNpc().size(); ++i) {
            Obus o = getObusNpc().get(i);
            o.move(deltaTime);
            for (int j = 0; j < grid.length; ++j) {
                GameElement[] ges = grid[j];
                for (int k = 0; k < ges.length; ++k) {
                    GameElement ge = ges[k];
                    if (ge instanceof ElementDur && ge.getHitbox().overlaps(o.getHitbox())) {
                        addExplosion(o);
                        obusNpc.remove(o);
                    }
                }
            }
            if (joueur.getHitbox().overlaps(o.getHitbox())) {
                vie -= 1;
                respawn(joueur);
                addExplosion(o);
                obusNpc.remove(o);
            }
            if (o.getX() < 0 || 20 < o.getX() || o.getY() < 0 || 20 < o.getX())
                obusNpc.remove(o);
        }
    }

    private void respawn(ElementDynamique ed) {
        int x = 0, y = 0;
        do {
            x = (int)(Math.random() * 20);
            y = (int)(Math.random() * 20);
        } while (!(grid[y][x] instanceof ElementVide));

        ed.setX(x);
        ed.setY(y);
    }

    public void updateObus(float deltaTime)  {
        updateObusNpc(deltaTime);

        for (int i = 0; i < getObus().size(); ++i) {

            Obus o = getObus().get(i);
            o.move(deltaTime);
            for (int j = 0; j < grid.length; ++j) {
                GameElement[] ges = grid[j];
                for (int k = 0; k < ges.length; ++k) {
                    GameElement ge = ges[k];

                    if (ge instanceof MurBrique && ge.getHitbox().overlaps(o.getHitbox())) {
                        remplacerGrid(j, k, new ElementVide());
                        addExplosion(o);
                        obus.remove(o);
                    } else if (ge instanceof MurFer && ge.getHitbox().overlaps(o.getHitbox())) {
                        addExplosion(o);
                        obus.remove(o);
                    }
                }
            }
            for (int j = 0; j < npcs.size(); ++j) {
                if (npcs.get(j).getHitbox().overlaps(o.getHitbox())) {
                    respawn(npcs.get(j));
                    addExplosion(o);
                    obus.remove(o);
                }
            }

        }
    }

    public ArrayList<Recompenses> getRecompenses() {
        return recompenses;
    }

    /**
     * Instantiate a World, load all the needed files.
     */
    public void configureWorld() {
        Json json = new Json();

        String text = Gdx.files.local("config/world.json").readString();
        grid = json.fromJson(grid.getClass(), text);


        for (int i = 0; i < grid.length; ++i) {
            for (int j = 0; j < grid[i].length; ++j) {
                grid[i][j].setX(j);
                grid[i][j].setY(i);
            }
        }

        text = Gdx.files.local("config/config.json").readString();
        Config config = new Config();

        config = json.fromJson(Config.class, text);
        joueur.setX(config.playerPos[0]);
        joueur.setY(config.playerPos[1]);

        joueur.setVitesse(config.playerSpeed);

        joueur.setWidth(config.playerWidth);
        joueur.setHeight(config.playerHeight);

        vie = config.vie;

        bulletMax = config.obusMax;
        bulletSpeed = config.obusSpeed;

        trophee.setX(config.trophee[0]);
        trophee.setY(config.trophee[1]);
        trophee.setHeight(1);
        trophee.setWidth(1);

        for (float[] npcPo : config.npcStart) {
            TankNpc npc;
            if (npcPo[3] == 0f) {
                npc = new TankChasseur1();
            } else if (npcPo[3] == 1f) {
                npc = new TankChasseur2();
            } else {
                throw new RuntimeException("NPC type uninplemented");
            }
            npc.setX(npcPo[0]);
            npc.setY(npcPo[1]);
            npc.setWidth(1);
            npc.setHeight(1);

            if (npcPo[2] == 0f) {
                npc.setDirection(Direction.HAUT);
            } else if (npcPo[2] == 1f) {
                npc.setDirection(Direction.GAUCHE);
            } else if (npcPo[2] == 2f) {
                npc.setDirection(Direction.BAS);
            } else if (npcPo[2] == 3f) {
                npc.setDirection(Direction.DROITE);
            } else {
                throw new RuntimeException("Not a direction");
            }

            npcs.add(npc);
        }

        intervaleAvion = config.plane;

        for (int i = 0; i < config.recompenses; ++i) {
            Recompenses rec = new Recompenses();
            rec.setX(config.coordRecompenses[i][0]);
            rec.setY(config.coordRecompenses[i][1]);
            rec.setWidth(1);
            rec.setHeight(1);
            recompenses.add(rec);
        }

    }

    public ArrayList<TankNpc> getNpcs() {
        return npcs;
    }

    public void spawnNewBulletNpc(float x, float y, Direction direction) {
        Obus bullet = new Obus();
        bullet.setX(x);
        bullet.setSpeed(bulletSpeed);
        bullet.setY(y);
        bullet.setDirection(direction);
        bullet.setWidth(1);
        bullet.setHeight(1);

        if (obusNpc.size() < bulletMax)
            obusNpc.add(bullet);
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
        for (int i = 0; i < obus.size(); ++i) {
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

    void checkCollisionAndMove(float newX, float newY, ElementDynamique ed, Direction dirIfNotMoved) {
        Rectangle hb = new Rectangle(newX, newY, ed.getWidth(), ed.getHeight());

        boolean canMove = true;
        for (GameElement[] ges : getGrid()) {
            for (GameElement ge : ges) {
                if (ge instanceof ElementDur && ge.getHitbox().overlaps(hb)) {
                    canMove = false;
                }
            }
        }

        ed.setCanMove(canMove);
        if (canMove) {
            ed.setX(newX);
            ed.setY(newY);
        } else {
            ed.setDirection(dirIfNotMoved);
        }
    }

    public void updateJoueur(float modX, float modY, Direction direction, boolean moved) {

        modX *= joueur.getVitesse();
        modY *= joueur.getVitesse();

        if (direction != null)
            joueur.setDirection(direction);
        direction = joueur.getDirection();
        joueur.setMoved(moved);

        float newX = getJoueur().getX() + modX;
        float newY = getJoueur().getY() + modY;

        checkCollisionAndMove(newX, newY, joueur, direction);

        for (int i = 0; i < recompenses.size(); ++i) {
            if (recompenses.get(i).getHitbox().overlaps(joueur.getHitbox())) {
                recompenses.remove(recompenses.get(i));
            }
        }
    }

    private static final int NPC_SPEED = 2;
    private static final double EPSILON = 0.1;

    boolean goToWall(ElementDynamique ed) {
        if (ed.getHitbox().getX() % 1 < 0.1) {
            if (Direction.GAUCHE == ed.getDirection()) {
                return grid[Math.round(ed.getX()) - 1][Math.round(ed.getY())] instanceof ElementDur;
            }
            if (Direction.DROITE == ed.getDirection()) {
                return grid[Math.round(ed.getX()) + 1][Math.round(ed.getY())] instanceof ElementDur;
            }
        }
        return false;
    }

    public void checkNpc(float deltaTime) {

        elapsedPlaneTime += deltaTime;
        if (elapsedPlaneTime > intervaleAvion) {
            elapsedPlaneTime = elapsedPlaneTime - intervaleAvion;
             Avion avion = new Avion();
             avion.genDir();
             avions.add(avion);

            for (ElementDynamique npc : npcs) {
                spawnNewBulletNpc(npc.getX(), npc.getY(), npc.getDirection());
            }
        }

        for (Avion a: avions) {
            a.move();
        }

        for (TankNpc npc : npcs) {
            if (npc.dansAlignement(joueur)) {
                spawnNewBulletNpc(npc.getX(), npc.getY(), npc.getDirection());
            }

            Direction dir = null;
            float modX = 0, modY = 0;
            if (npc instanceof TankChasseur1) {
                float comparisionX = joueur.getX() - npc.getX();
                float comparisionY = joueur.getY() - npc.getY();

                if (Math.abs(comparisionX) > EPSILON && npc.canMove()) {
                    if (comparisionX < 0) {
                        modX -= deltaTime;
                        dir = Direction.GAUCHE;
                    } else {
                        modX += deltaTime;
                        dir = Direction.DROITE;
                    }
                } else if (Math.abs(comparisionY) > EPSILON) {
                    if (comparisionY < 0) {
                        modY -= deltaTime;
                        dir = Direction.BAS;
                    } else {
                        modY += deltaTime;
                        dir = Direction.HAUT;
                    }
                }
            }
            else {
                dir = npc.getDirection();
                if (!npc.canMove()) {
                    dir = Direction.values()[(int) (Math.random()*10) % 4];
                }
                switch (npc.getDirection()) {
                    case BAS:
                        modY -= deltaTime;
                        break;
                    case HAUT:
                        modY += deltaTime;
                        break;
                    case DROITE:
                        modX += deltaTime;
                        break;
                    case GAUCHE:
                        modX -= deltaTime;
                        break;
                }
            }
            updateNpc(npc, modX, modY, dir);
        }
    }

    public void updateNpc(TankNpc npc, float modX, float modY, Direction direction) {

        Direction oldNpcDir = npc.getDirection();

        if (direction != null) {
           npc.setDirection(direction);
        }

        modX *= NPC_SPEED;
        modY *= NPC_SPEED;

        float newX = npc.getX() + modX;
        float newY = npc.getY() + modY;

        if (npc instanceof TankChasseur1)
            checkCollisionAndMove(newX, newY, npc, oldNpcDir);
        else
            checkCollisionAndMove(newX, newY, npc, npc.getDirection());
    }

    public Trophee getTrophee() {
        return trophee;
    }
}
