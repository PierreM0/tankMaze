package com.mygdx.game.textureCOR;

import java.util.Vector;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mygdx.game.gameelement.GameElement;

public abstract class TextureCOR {
	
	private TextureCOR cor;
	
	public TextureCOR(TextureCOR cor) {
		this.cor = cor;
	}
	
	public Vector<Sprite> getSpriteFromGameElement(GameElement ge) {
		Vector<Sprite> res = getSprite1(ge);
		if (res != null) return res;
		else if (cor == null) return null;
		else
			return cor.getSpriteFromGameElement(ge);
	}

	abstract Vector<Sprite> getSprite1(GameElement ge);
}
