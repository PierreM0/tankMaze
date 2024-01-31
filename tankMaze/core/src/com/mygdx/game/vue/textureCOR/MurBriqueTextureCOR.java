package com.mygdx.game.vue.textureCOR;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.model.gameelement.GameElement;
import com.mygdx.game.model.gameelement.elementstatique.MurBrique;
import com.mygdx.game.vue.TextureFactory;

public class MurBriqueTextureCOR extends TextureCOR {

	public MurBriqueTextureCOR(TextureCOR cor) {
		super(cor);
	}

	@Override
	TextureRegion[] getTextureRegion1(GameElement ge) {
		if (ge instanceof MurBrique) {
			return TextureFactory.getInstance().getMurBrique1x1();
		}
		return null;
	}
}
