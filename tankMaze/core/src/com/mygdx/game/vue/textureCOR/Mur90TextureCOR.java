package com.mygdx.game.vue.textureCOR;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mygdx.game.model.gameelement.GameElement;
import com.mygdx.game.model.gameelement.Mur90;
import com.mygdx.game.vue.TextureFactory;

public class Mur90TextureCOR extends TextureCOR {

	public Mur90TextureCOR(TextureCOR cor) {
		super(cor);
	}

	@Override
	Sprite[] getSprite1(GameElement ge) {
		if (ge instanceof Mur90) {
			return TextureFactory.getInstance().getMur90();
		}
		return null;
	}
}
