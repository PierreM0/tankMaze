package com.mygdx.game.vue.textureCOR;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mygdx.game.model.gameelement.GameElement;
import com.mygdx.game.model.gameelement.Mur;
import com.mygdx.game.vue.TextureFactory;

public class MurTextureCOR extends TextureCOR {

	public MurTextureCOR(TextureCOR cor) {
		super(cor);
	}

	@Override
	Sprite[] getSprite1(GameElement ge) {
		if (ge instanceof Mur) {
			return TextureFactory.getInstance().getMur1x1();
		}
		return null;
	}
}
