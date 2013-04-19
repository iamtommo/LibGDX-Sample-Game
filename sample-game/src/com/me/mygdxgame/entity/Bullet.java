package com.me.mygdxgame.entity;

import com.badlogic.gdx.graphics.Texture;

public class Bullet extends Entity {

	public Bullet(int x, int y) {
		super(x, y, new Texture("data/bullet.png"), 8, 8);
		setSize(8, 8);
		setScale(0.25f);
	}

}