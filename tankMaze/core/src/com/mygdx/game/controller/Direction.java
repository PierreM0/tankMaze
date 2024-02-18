package com.mygdx.game.controller;

public enum Direction {
	HAUT,
	BAS,
	GAUCHE,
	DROITE;

	public float toAngle() {
		switch (this) {
			case HAUT:
				return 0;
			case GAUCHE:
				return 90;
			case BAS:
				return 180;
			case DROITE:
				return 270;
			default:
				throw new RuntimeException("Unreachable");
		}
	}
}
