package com.mygdx.game.vue.textureCOR;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.model.gameelement.GameElement;
import com.mygdx.game.model.gameelement.elementstatique.MurFer90;
import com.mygdx.game.vue.TextureFactory;

public class MurFer90TextureCOR extends TextureCOR {

	public MurFer90TextureCOR(TextureCOR cor) {
		super(cor);
	}

	@Override
	TextureRegion[] getTextureRegion1(GameElement ge) {
		if (ge instanceof MurFer90) {
			return TextureFactory.getInstance().getMurFer90();
		}
		return null;
	}
}
