package com.me.mygdxgame.entity;

import com.badlogic.gdx.graphics.Texture;

public class RemotePlayer extends Entity {
	
	private String name;

	public RemotePlayer(String name) {
		super(0, 0, new Texture("data/enemy.png"), 32, 32);
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
