package com.mygdx.game.vue.textureCOR;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.model.gameelement.GameElement;
import com.mygdx.game.model.gameelement.elementstatique.MurFer;
import com.mygdx.game.vue.TextureFactory;

public class MurFerTextureCOR extends TextureCOR {

	public MurFerTextureCOR(TextureCOR cor) {
		super(cor);
	}

	@Override
	TextureRegion[] getTextureRegion1(GameElement ge) {
		if (ge instanceof MurFer) {
			return TextureFactory.getInstance().getMurFer();
		}
		return null;
	}
}
