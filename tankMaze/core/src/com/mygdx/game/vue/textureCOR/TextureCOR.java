package com.mygdx.game.vue.textureCOR;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mygdx.game.model.gameelement.GameElement;

/**
 * A chain of responsability to load Sprite from instance of {@link GameElement}
 *
 */
public abstract class TextureCOR {
	
	private TextureCOR cor;
	
	public TextureCOR(TextureCOR cor) {
		this.cor = cor;
	}
	
	/**
	 * The main Function of the COR.
	 *
	 * @param ge The instance you search the Sprite for
	 */
	public Sprite[] getSpriteFromGameElement(GameElement ge) {
		Sprite[] res = getSprite1(ge);
		if (res != null) return res;
		else if (cor == null) return null;
		else
			return cor.getSpriteFromGameElement(ge);
	}

	/**
	 * The internal COR function. Return null if nothing was found.
	 *
	 * @param ge 
	 */
	abstract Sprite[] getSprite1(GameElement ge);
}
