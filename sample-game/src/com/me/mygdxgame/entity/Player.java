package com.me.mygdxgame.entity;

import com.badlogic.gdx.graphics.Texture;
import com.me.mygdxgame.screen.GameScreen;

public class Player extends Entity {

	public Player() {
		super(50, 50, new Texture("data/player.png"), 32, 32);
	}

	@Override
	public void update(GameScreen game, float delta) {
		super.update(game, delta);
	}
	
}