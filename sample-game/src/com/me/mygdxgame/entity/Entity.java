package com.me.mygdxgame.entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.me.mygdxgame.Game;
import com.me.mygdxgame.screen.GameScreen;

public class Entity extends Sprite {
	
	private Vector2 velocity = new Vector2(0, 0);
	private int width = 32;
	private int height = 32;
	
	public Entity(int x, int y, Texture texture, int width, int height) {
		super(texture);
		setPosition(x, y);
		setBounds(x, y, width, height);
	}
	
	public void update(GameScreen game, float delta) {
		Vector2 offset = velocity.cpy().mul(200 * delta);
		final float tmpx = getX() + offset.x;
		final float tmpy = getY() + offset.y;
		if (checkValidMove(game, tmpx, tmpy)) {
			setX(tmpx);
			setY(tmpy);
		}
		setBounds(getX(), getY(), width, height);
	}
	
	public boolean checkValidMove(GameScreen game, float tmpx, float tmpy) {
		if (tmpx < 0 || tmpy < 0 || tmpx + getWidth() > Game.width || tmpy + getHeight() > Game.height) {
			return false;
		}
		
		return true;
	}
	
	public int distanceTo(Entity other) {
		return (int) Math.sqrt(Math.pow(other.getX() - getX(), 2) + Math.pow(other.getY() - getY(), 2));
	}

	public Vector2 getVelocity() {
		return velocity;
	}

	public void setVelocity(Vector2 velocity) {
		this.velocity = velocity;
	}

}
