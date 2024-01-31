package com.mygdx.game.vue.textureCOR;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.model.gameelement.GameElement;

/**
 * A chain of responsability to load TextureRegion from instance of {@link GameElement}
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
	 * @param ge The instance you search the TextureRegion for
	 */
	public TextureRegion[] getTextureRegionFromGameElement(GameElement ge) {
		TextureRegion[] res = getTextureRegion1(ge);
		if (res != null) return res;
		else if (cor == null)
			throw new RuntimeException("No sprite found");
		else
			return cor.getTextureRegionFromGameElement(ge);
	}

	/**
	 * The internal COR function. Return null if nothing was found.
	 *
	 * @param ge 
	 */
	abstract TextureRegion[] getTextureRegion1(GameElement ge);

}
