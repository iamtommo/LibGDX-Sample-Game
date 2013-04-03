package com.me.mygdxgame.entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Vector2;
import com.me.mygdxgame.screen.GameScreen;

public class Enemy extends Entity {
	
	private Entity target;

	public Enemy(int x, int y) {
		super(x, y, new Texture("data/enemy.png"));
		setSpeed(80f);
		setMaxSpeed(250f);
	}
	
	@Override
	public void update(GameScreen game, float delta) {
		super.update(game, delta);
		if (target != null) {
			Vector2 vec = new Vector2(target.getX(), target.getY()).sub(getX(), getY()).nor();
			setVelocity(vec);
		}
		
		if (Intersector.overlapRectangles(getBounds(), game.getPlayer().getBounds())) {
			game.setGameOver(true);
		}
		
		target = getTarget(game);
	}

	public Entity getTarget(GameScreen game) {
		Entity closest = null;
		if (getSpeed() != getMaxSpeed()) {
			for (Entity e : game.getPowerups()) {
				if (closest == null) {
					closest = e;
				}
				
				if (distanceTo(e) < distanceTo(closest)) {
					closest = e;
				}
			}
		}
		//choose the powerup if its closer than double the distance to the player
		if (closest == null || distanceTo(game.getPlayer()) < distanceTo(closest) * 2) {
			closest = game.getPlayer();
		}
			
		return closest;
	}

	public Entity getTarget() {
		return target;
	}

	public void setTarget(Entity target) {
		this.target = target;
	}
	
}