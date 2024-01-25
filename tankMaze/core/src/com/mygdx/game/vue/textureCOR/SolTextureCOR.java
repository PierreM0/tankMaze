package com.mygdx.game.vue.textureCOR;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mygdx.game.model.gameelement.ElementVide;
import com.mygdx.game.model.gameelement.GameElement;
import com.mygdx.game.vue.TextureFactory;

public class SolTextureCOR extends TextureCOR {

	public SolTextureCOR(TextureCOR cor) {
		super(cor);
	}

	@Override
	Sprite[] getSprite1(GameElement ge) {
		if (ge instanceof ElementVide) {
			return TextureFactory.getInstance().getSol();
		}
		return null;
	}
}
