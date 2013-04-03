package com.me.mygdxgame.entity;

import com.badlogic.gdx.graphics.Texture;

public class Player extends Entity {

	public Player() {
		super(50, 50, new Texture("data/player.png"));
		setSpeed(120f);
		setMaxSpeed(300f);
	}

}