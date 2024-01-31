package com.mygdx.game.vue.textureCOR;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mygdx.game.model.gameelement.GameElement;
import com.mygdx.game.model.gameelement.Trappe;
import com.mygdx.game.vue.TextureFactory;

public class TrappeTextureCOR extends TextureCOR {

	public TrappeTextureCOR(TextureCOR cor) {
		super(cor);
	}

	@Override
	Sprite[] getSprite1(GameElement ge) {
		if (ge instanceof Trappe) {
			return TextureFactory.getInstance().getTrappe1x1();
		}
		return null;
	}
}
