package com.me.mygdxgame.entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.me.mygdxgame.screen.GameScreen;

public class Bullet extends Entity {

	public Bullet(int x, int y) {
		super(x, y, new Texture("data/bullet.png"), 8, 8);
		setSize(8, 8);
		setScale(0.25f);
	}
	
	@Override
	public void update(GameScreen game, float delta) {
		Vector2 offset = getVelocity().cpy().mul(200 * delta);
		setX(getX() + offset.x);
		setY(getY() + offset.y);
		setBounds(getX(), getY(), getWidth(), getHeight());
	}

}