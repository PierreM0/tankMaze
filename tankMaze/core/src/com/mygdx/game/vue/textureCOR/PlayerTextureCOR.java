package com.mygdx.game.vue.textureCOR;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mygdx.game.model.gameelement.GameElement;
import com.mygdx.game.model.gameelement.tank.TankJoueur;
import com.mygdx.game.vue.TextureFactory;

public class PlayerTextureCOR extends TextureCOR {

	public PlayerTextureCOR(TextureCOR cor) {
		super(cor);
	}

	@Override
	Sprite[] getSprite1(GameElement ge) {
		if (ge instanceof TankJoueur) {
			return TextureFactory.getInstance().getJoueur();
		}
		return null;
	}

}
