package com.mygdx.game.vue.textureCOR;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.model.gameelement.GameElement;
import com.mygdx.game.model.gameelement.elementstatique.ElementVide;
import com.mygdx.game.model.gameelement.elementstatique.Vegetation;
import com.mygdx.game.vue.TextureFactory;

public class VegetationTextureCOR extends TextureCOR {

	public VegetationTextureCOR(TextureCOR cor) {
		super(cor);
	}

	@Override
	TextureRegion[] getTextureRegion1(GameElement ge) {
		if (ge instanceof Vegetation) {
			return TextureFactory.getInstance().getVegetation();
		}
		return null;
	}
}
