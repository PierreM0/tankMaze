package com.mygdx.game.vue.textureCOR;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.model.gameelement.GameElement;
import com.mygdx.game.model.gameelement.elementdynamique.TankJoueur;
import com.mygdx.game.vue.TextureFactory;

public class PlayerTextureCOR extends TextureCOR {

	public PlayerTextureCOR(TextureCOR cor) {
		super(cor);
	}

	@Override
	TextureRegion[] getTextureRegion1(GameElement ge) {
		if (ge instanceof TankJoueur) {
			return TextureFactory.getInstance().getJoueur();
		}
		return null;
	}

}
