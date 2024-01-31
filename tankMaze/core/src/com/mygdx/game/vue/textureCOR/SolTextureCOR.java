package com.mygdx.game.vue.textureCOR;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.model.gameelement.elementstatique.ElementVide;
import com.mygdx.game.model.gameelement.GameElement;
import com.mygdx.game.vue.TextureFactory;

public class SolTextureCOR extends TextureCOR {

	public SolTextureCOR(TextureCOR cor) {
		super(cor);
	}

	@Override
	TextureRegion[] getTextureRegion1(GameElement ge) {
		if (ge instanceof ElementVide) {
			return TextureFactory.getInstance().getSol();
		}
		return null;
	}
}
