package com.mygdx.game.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Json;
import com.mygdx.game.model.gameelement.GameElement;
import com.mygdx.game.model.gameelement.elementdynamique.TankJoueur;
import com.mygdx.game.model.gameelement.elementdynamique.TankNpc;
import com.mygdx.game.model.gameelement.elementstatique.MurFer;

import java.util.ArrayList;


/**
 * World is the main model class, it stores all the game data.
 * It auto-load the config from "assets/config/" at instantiation.
 */
public class World {
    private GameElement[][] grid = {};
    private TankJoueur joueur = new TankJoueur();

    private ArrayList<TankNpc> npcs = new ArrayList<TankNpc>();


    /**
     * Instantiate a World, load all the needed files.
     */
    public World() {
        Json json = new Json();

        String text = Gdx.files.local("config/world.json").readString();
        grid = json.fromJson(grid.getClass(), text);

        text = Gdx.files.local("config/player_start.json").readString();
        float[] playerPosition = {};
        playerPosition = json.fromJson(playerPosition.getClass(), text);
        joueur.setX(playerPosition[0]);
        joueur.setY(playerPosition[1]);

        text = Gdx.files.local("config/player_speed.json").readString();
        float playerSpeed = 0;
        playerSpeed = json.fromJson(float.class, text);
        joueur.setVitesse(playerSpeed);

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

    public GameElement[][] getGrid() {
        return grid;
    }

    public TankJoueur getJoueur() {
        return joueur;
    }
}
