package com.me.mygdxgame.entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.me.mygdxgame.Game;
import com.me.mygdxgame.screen.GameScreen;

public class Entity {
	
	private Texture texture;
	private float x;
	private float y;
	private float speed;
	private Vector2 velocity = Vector2.Zero.cpy();
	private Rectangle bounds;
	private int width = 32;
	private int height = 32;
	private float maxSpeed = -1f;
	
	public Entity(int x, int y, Texture texture) {
		this.x = x;
		this.y = y;
		this.texture = texture;
		this.bounds = new Rectangle(x, y, width, height);
	}
	
	public void update(GameScreen game, float delta) {
		Vector2 offset = velocity.cpy().mul(speed * delta);
		final float tmpx = x + offset.x;
		final float tmpy = y + offset.y;
		if (checkValidMove(game, tmpx, tmpy)) {
			x = tmpx;
			y = tmpy;
		}
		bounds.set(x, y, width, height);
	}
	
	public boolean checkValidMove(GameScreen game, float tmpx, float tmpy) {
		if (tmpx < 0 || tmpy < 0 || tmpx + bounds.width > Game.width || tmpy + bounds.height > Game.height) {
			return false;
		}
		
		return true;
	}
	
	public int distanceTo(Entity other) {
		return (int) Math.sqrt(Math.pow(other.x - x, 2) + Math.pow(other.y - y, 2));
	}

	public Texture getTexture() {
		return texture;
	}

	public void setTexture(Texture texture) {
		this.texture = texture;
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public float getSpeed() {
		return speed;
	}

	public void setSpeed(float speed) {
		if (maxSpeed > 0f && speed > maxSpeed) {
			speed = maxSpeed;
		}
		this.speed = speed;
	}

	public Vector2 getVelocity() {
		return velocity;
	}

	public void setVelocity(Vector2 velocity) {
		this.velocity = velocity;
	}

	public Rectangle getBounds() {
		return bounds;
	}

	public void setBounds(Rectangle bounds) {
		this.bounds = bounds;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public float getMaxSpeed() {
		return maxSpeed;
	}

	public void setMaxSpeed(float maxSpeed) {
		this.maxSpeed = maxSpeed;
	}

}
