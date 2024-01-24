package com.mygdx.game.textureCOR;

import java.util.Vector;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mygdx.game.TextureFactory;
import com.mygdx.game.gameelement.GameElement;
import com.mygdx.game.gameelement.Mur;

public class MurTextureCOR extends TextureCOR {

	public MurTextureCOR(TextureCOR cor) {
		super(cor);
		// TODO Auto-generated constructor stub
	}

	@Override
	Vector<Sprite> getSprite1(GameElement ge) {
		if (ge instanceof Mur) {
			Vector<Sprite> v = new Vector<Sprite>(); 
			v.add(TextureFactory.getInstance().getMur1());
			return v;
		}
		return null;
	}

}
