package com.mygdx.game.vue;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * A singleton that hold the textures and process them to textureRegion.
 */
public class TextureFactory {
    static public final int IMG_SZ = 84;
    static private TextureFactory instance = null;
    private Texture texture;
    private Texture textureVege;

    private TextureFactory() {
        texture = new Texture(Gdx.files.local("texturemap.png"));
        textureVege = new Texture(Gdx.files.local("vegetation.png"));
    }

    static public TextureFactory getInstance() {
        if (instance != null) {
            return instance;
        } else {
            instance = new TextureFactory();
            return instance;
        }
    }

    public TextureRegion[] getSol() {
        TextureRegion[] textureRegions = {
                new TextureRegion(texture, 0, 0, IMG_SZ, IM_SZ)};
        return textureRegions;
    }

    public TextureRegion[] getVegetation() {
        TextureRegion[] textureRegions = {
                new TextureRegion(textureVege, 0, 0, IMG_SZ, IMG_SZ)
        };
        return textureRegions;
    }

    public TextureRegion[] getJoueur() {
        TextureRegion[] textureRegions = {
                new TextureRegion(texture, IMG_SZ * 1, IMG_SZ * 0, IMG_SZ, IMG_SZ),
                new TextureRegion(texture, IMG_SZ * 2, IMG_SZ * 0, IMG_SZ, IMG_SZ),
                new TextureRegion(texture, IMG_SZ * 3, IMG_SZ * 0, IMG_SZ, IMG_SZ),
                new TextureRegion(texture, IMG_SZ * 4, IMG_SZ * 0, IMG_SZ, IMG_SZ),
                new TextureRegion(texture, IMG_SZ * 5, IMG_SZ * 0, IMG_SZ, IMG_SZ),
                new TextureRegion(texture, IMG_SZ * 6, IMG_SZ * 0, IMG_SZ, IMG_SZ),
                new TextureRegion(texture, IMG_SZ * 7, IMG_SZ * 0, IMG_SZ, IMG_SZ),
                new TextureRegion(texture, IMG_SZ * 0, IMG_SZ * 1, IMG_SZ, IMG_SZ)
        };

        return textureRegions;
    }


    public TextureRegion[] getNPC() {
        TextureRegion[] textureRegions = {
                new TextureRegion(texture, IMG_SZ * 1, IMG_SZ * 1, IMG_SZ, IMG_SZ),
                new TextureRegion(texture, IMG_SZ * 2, IMG_SZ * 1, IMG_SZ, IMG_SZ),
                new TextureRegion(texture, IMG_SZ * 3, IMG_SZ * 1, IMG_SZ, IMG_SZ),
                new TextureRegion(texture, IMG_SZ * 4, IMG_SZ * 1, IMG_SZ, IMG_SZ),
                new TextureRegion(texture, IMG_SZ * 5, IMG_SZ * 1, IMG_SZ, IMG_SZ),
                new TextureRegion(texture, IMG_SZ * 6, IMG_SZ * 1, IMG_SZ, IMG_SZ),
                new TextureRegion(texture, IMG_SZ * 7, IMG_SZ * 1, IMG_SZ, IMG_SZ),
                new TextureRegion(texture, IMG_SZ * 0, IMG_SZ * 2, IMG_SZ, IMG_SZ)
        };

        return textureRegions;
    }

    public TextureRegion[] getMurBrique1x1() {
        TextureRegion[] textureRegions = {
                new TextureRegion(texture, IMG_SZ * 2, IMG_SZ * 3, IMG_SZ, IMG_SZ)
        };
        return textureRegions;
    }

    public TextureRegion[] getMurBrique1x190d() {
        TextureRegion[] textureRegions = {
                new TextureRegion(texture, IMG_SZ * 4, IMG_SZ * 3, IMG_SZ, IMG_SZ)
        };
        return textureRegions;
    }

    public TextureRegion[] getMurBrique2x1_1() {
        TextureRegion[] textureRegions = {
                new TextureRegion(texture, IMG_SZ * 0, IMG_SZ * 3, IMG_SZ, IMG_SZ)
        };
        return textureRegions;
    }

    public TextureRegion[] getMurBrique2x1_2() {
        TextureRegion[] textureRegions = {
                new TextureRegion(texture, IMG_SZ * 1, IMG_SZ * 3, IMG_SZ, IMG_SZ)
        };
        return textureRegions;
    }

    public TextureRegion[] getMurBrique1x2_1() {
        TextureRegion[] textureRegions = {
                new TextureRegion(texture, IMG_SZ * 3, IMG_SZ * 3, IMG_SZ, IMG_SZ)
        };
        return textureRegions;
    }

    public TextureRegion[] getMurBrique1x2_2() {
        TextureRegion[] textureRegions = {
                new TextureRegion(texture, IMG_SZ * 5, IMG_SZ * 3, IMG_SZ, IMG_SZ)
        };
        return textureRegions;
    }

    public TextureRegion[] getMurFer() {
        TextureRegion[] textureRegions = {
                new TextureRegion(texture, IMG_SZ * 6, IMG_SZ * 3, IMG_SZ, IMG_SZ)
        };
        return textureRegions;
    }

    public TextureRegion[] getMurFer90() {
        return new TextureRegion[]{
                new TextureRegion(texture, IMG_SZ * 7, IMG_SZ * 3, IMG_SZ, IMG_SZ)
        };
    }

    public TextureRegion[] getMiniJoueur() {
        return new TextureRegion[]{
                new TextureRegion(texture, IMG_SZ * 6, IMG_SZ * 2, IMG_SZ, IMG_SZ)
        };
    }

    public TextureRegion[] getAigle() {
        return new TextureRegion[]{
                new TextureRegion(texture, IMG_SZ * 7, IMG_SZ * 2, IMG_SZ, IMG_SZ)
        };
    }

    public TextureRegion[] getObus() {
        return new TextureRegion[]{
                new TextureRegion(texture, IMG_SZ * 5, IMG_SZ * 2, IMG_SZ, IMG_SZ)
        };
    }

    public TextureRegion[] getBille() {
        return new TextureRegion[]{
                new TextureRegion(texture, IMG_SZ * 4, IMG_SZ * 2, IMG_SZ, IMG_SZ)
        };
    }

    public TextureRegion[] getExplosion() {
        return new TextureRegion[]{
                new TextureRegion(texture, IMG_SZ * 1, IMG_SZ * 2, IMG_SZ, IMG_SZ),
                new TextureRegion(texture, IMG_SZ * 2, IMG_SZ * 2, IMG_SZ, IMG_SZ),
                new TextureRegion(texture, IMG_SZ * 3, IMG_SZ * 2, IMG_SZ, IMG_SZ)
        };
    }


}
