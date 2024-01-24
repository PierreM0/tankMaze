package com.mygdx.game.textureCOR;

import java.util.Vector;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mygdx.game.TextureFactory;
import com.mygdx.game.gameelement.GameElement;
import com.mygdx.game.gameelement.tank.TankJoueur;

public class PlayerTextureCOR extends TextureCOR {

	public PlayerTextureCOR(TextureCOR cor) {
		super(cor);
		// TODO Auto-generated constructor stub
	}

	@Override
	Vector<Sprite> getSprite1(GameElement ge) {
		if (ge instanceof TankJoueur) {
			Vector<Sprite> v = new Vector<Sprite>(); 
			v.add(TextureFactory.getInstance().getJoueur1());
			return v;
		}
		return null;
	}

}
