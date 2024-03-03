package com.mygdx.game.vue;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.model.gameelement.GameElement;
import com.mygdx.game.model.gameelement.elementdynamique.TankJoueur;
import com.mygdx.game.model.gameelement.elementstatique.ElementVide;
import com.mygdx.game.model.gameelement.elementstatique.elementdur.MurBrique;
import com.mygdx.game.model.gameelement.elementstatique.elementdur.MurFer;
import com.mygdx.game.model.gameelement.elementstatique.Vegetation;

/**
 * A singleton that hold the textures and process them to textureRegion.
 */
public class TextureFactory {
    static public final int IMG_SZ = 84;
    static private TextureFactory instance = null;
    private final Texture textureVege;
    private final Texture textureSol;
    private final Texture textureJoueur;
    private final Texture textureNPC;
    private final Texture textureMurBrique1x1;
    private final Texture textureMurBrique1x190d;
    private final Texture textureMurBrique2x1_1;
    private final Texture textureMurBrique2x1_2;
    private final Texture textureMurBrique1x2_1;
    private final Texture textureMurBrique1x2_2;
    private final Texture textureAigle;
    private final Texture textureObus;
    private final Texture textureMiniJoueur;
    private final Texture textureMurFer;
    private final Texture textureMurFer90;
    private final Texture textureBille;
    private final Texture textureAvion;

    TextureRegion[] npcRegions;
    TextureRegion[] playerRegions;
    TextureRegion[] explosionRegion;

    private TextureRegion toTextureRegion(Texture tex) {
        return new TextureRegion(tex, tex.getWidth(), tex.getHeight());
    }

    private TextureFactory() {
        Texture texture = new Texture(Gdx.files.local("texture/texturemap.png"));
        textureJoueur = new Texture(Gdx.files.local("texture/player.png"));
        textureNPC = new Texture(Gdx.files.local("texture/npc.png"));
        textureVege = new Texture(Gdx.files.local("texture/vegetation.png"));
        textureSol = new Texture(Gdx.files.local("texture/sol.png"));
        textureMurBrique1x1 = new Texture(Gdx.files.local("texture/mur-brique.png"));
        textureMurBrique1x190d = new Texture(Gdx.files.local("texture/mur-brique-90.png"));
        textureMurBrique2x1_1 = new Texture(Gdx.files.local("texture/mur-brique-2x1-1.png"));
        textureMurBrique2x1_2 = new Texture(Gdx.files.local("texture/mur-brique-2x1-2.png"));
        textureMurBrique1x2_1 = new Texture(Gdx.files.local("texture/mur-brique-1x2-1.png"));
        textureMurBrique1x2_2 = new Texture(Gdx.files.local("texture/mur-brique-1x2-2.png"));
        textureAigle = new Texture(Gdx.files.local("texture/aigle.png"));
        textureObus = new Texture(Gdx.files.local("texture/obus.png"));
        textureMiniJoueur = new Texture(Gdx.files.local("texture/mini-char.png"));
        textureMurFer = new Texture(Gdx.files.local("texture/mur-fer.png"));
        textureMurFer90 = new Texture(Gdx.files.local("texture/mur-fer-90.png"));
        textureBille = new Texture(Gdx.files.local("texture/bille.png"));
        textureAvion = new Texture(Gdx.files.local("texture/soucoupe-volante-carree.png"));

        npcRegions = new TextureRegion[] {
                new TextureRegion(textureNPC, IMG_SZ * 1, IMG_SZ * 0, IMG_SZ, IMG_SZ),
                new TextureRegion(textureNPC, IMG_SZ * 2, IMG_SZ * 0, IMG_SZ, IMG_SZ),
                new TextureRegion(textureNPC, IMG_SZ * 3, IMG_SZ * 0, IMG_SZ, IMG_SZ),
                new TextureRegion(textureNPC, IMG_SZ * 4, IMG_SZ * 0, IMG_SZ, IMG_SZ),
                new TextureRegion(textureNPC, IMG_SZ * 5, IMG_SZ * 0, IMG_SZ, IMG_SZ),
                new TextureRegion(textureNPC, IMG_SZ * 6, IMG_SZ * 0, IMG_SZ, IMG_SZ),
                new TextureRegion(textureNPC, IMG_SZ * 7, IMG_SZ * 0, IMG_SZ, IMG_SZ),
                new TextureRegion(textureNPC, IMG_SZ * 0, IMG_SZ * 1, IMG_SZ, IMG_SZ)
        };

        playerRegions = new TextureRegion[] {
            new TextureRegion(textureJoueur, IMG_SZ * 1, IMG_SZ * 0, IMG_SZ, IMG_SZ),
                    new TextureRegion(textureJoueur, IMG_SZ * 2, IMG_SZ * 0, IMG_SZ, IMG_SZ),
                    new TextureRegion(textureJoueur, IMG_SZ * 3, IMG_SZ * 0, IMG_SZ, IMG_SZ),
                    new TextureRegion(textureJoueur, IMG_SZ * 4, IMG_SZ * 0, IMG_SZ, IMG_SZ),
                    new TextureRegion(textureJoueur, IMG_SZ * 5, IMG_SZ * 0, IMG_SZ, IMG_SZ),
                    new TextureRegion(textureJoueur, IMG_SZ * 6, IMG_SZ * 0, IMG_SZ, IMG_SZ),
                    new TextureRegion(textureJoueur, IMG_SZ * 7, IMG_SZ * 0, IMG_SZ, IMG_SZ),
                    new TextureRegion(textureJoueur, IMG_SZ * 0, IMG_SZ * 1, IMG_SZ, IMG_SZ)
        };

        explosionRegion = new TextureRegion[] {
                new TextureRegion(texture, IMG_SZ * 1, IMG_SZ * 2, IMG_SZ, IMG_SZ),
                new TextureRegion(texture, IMG_SZ * 2, IMG_SZ * 2, IMG_SZ, IMG_SZ),
                new TextureRegion(texture, IMG_SZ * 3, IMG_SZ * 2, IMG_SZ, IMG_SZ)
        };
    }

    static public TextureFactory getInstance() {
        if (instance != null) {
            return instance;
        } else {
            instance = new TextureFactory();
            return instance;
        }
    }

    public TextureRegion getSol() {
        return toTextureRegion(textureSol);
    }

    public TextureRegion getVegetation() {
        return toTextureRegion(textureVege);
    }

    public TextureRegion[] getJoueur() {
        return playerRegions;
    }


    public TextureRegion[] getNpc() {
        return npcRegions;
    }

    public TextureRegion getMurBrique1x1() {
        return toTextureRegion(textureMurBrique1x1);
    }

    public TextureRegion getMurBrique1x190d() {
        return toTextureRegion(textureMurBrique1x190d);
    }

    public TextureRegion getMurBrique2x1_1() {
        return toTextureRegion(textureMurBrique2x1_1);
    }

    public TextureRegion getMurBrique2x1_2() {
        return toTextureRegion(textureMurBrique2x1_2);
    }

    public TextureRegion getMurBrique1x2_1() {
        return toTextureRegion(textureMurBrique1x2_1);
    }

    public TextureRegion getMurBrique1x2_2() {
        return toTextureRegion(textureMurBrique1x2_2);
    }

    public TextureRegion getMurFer() {
        return toTextureRegion(textureMurFer);
    }

    public TextureRegion getMurFer90() {
        return toTextureRegion(textureMurFer90);
    }

    public TextureRegion getMiniJoueur() {
        return toTextureRegion(textureMiniJoueur);
    }

    public TextureRegion getAvion() {
        return toTextureRegion(textureAvion);
    }

    public TextureRegion getAigle() {
        return toTextureRegion(textureAigle);
    }

    public TextureRegion getObus() {
        return toTextureRegion(textureObus);
    }

    public TextureRegion getBille() {
        return toTextureRegion(textureBille);
    }

    public TextureRegion[] getExplosion() {
        return explosionRegion;
    }

     public static TextureRegion[] getTextureFromGameElement(GameElement ge) {
    	if (ge instanceof MurBrique) 
    		return new TextureRegion[] { TextureFactory.getInstance().getMurBrique1x1() };
    	 else if (ge instanceof MurFer) 
            return new TextureRegion[] { TextureFactory.getInstance().getMurFer() };
    	 else if (ge instanceof TankJoueur) 
            return TextureFactory.getInstance().getJoueur();
    	 else if (ge instanceof Vegetation) 
            return new TextureRegion[] { TextureFactory.getInstance().getVegetation() };
    	 else if (ge instanceof ElementVide) 
            return new TextureRegion[] { TextureFactory.getInstance().getSol() };
    	 else
             throw new RuntimeException("Undefined texture for GameElement: " + ge);
    }


}
