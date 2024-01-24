package com.mygdx.game.textureCOR;

import java.util.Vector;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mygdx.game.TextureFactory;
import com.mygdx.game.gameelement.ElementVide;
import com.mygdx.game.gameelement.GameElement;

public class SolTextureCOR extends TextureCOR {

	public SolTextureCOR(TextureCOR cor) {
		super(cor);
		// TODO Auto-generated constructor stub
	}

	@Override
	Vector<Sprite> getSprite1(GameElement ge) {
		if (ge instanceof ElementVide) {
			Vector<Sprite> v = new Vector<Sprite>(); 
			v.add(TextureFactory.getInstance().getSol());
			return v;
		}
		return null;
	}

}
