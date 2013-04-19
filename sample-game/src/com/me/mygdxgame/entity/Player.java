package com.me.mygdxgame.entity;

import com.badlogic.gdx.graphics.Texture;

public class Player extends Entity {

	public Player(int x, int y) {
		super(x, y, new Texture("data/player.png"), 32, 32);
	}
	
}