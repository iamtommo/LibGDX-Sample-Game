package com.me.mygdxgame.entity;

import com.badlogic.gdx.graphics.Texture;

public class Player extends Entity {
	
	private String name = null;

	public Player(int x, int y) {
		super(x, y, new Texture("data/player.png"), 32, 32);
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
	
}