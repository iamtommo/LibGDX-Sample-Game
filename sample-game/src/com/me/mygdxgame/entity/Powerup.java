package com.me.mygdxgame.entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Intersector;
import com.me.mygdxgame.screen.GameScreen;

public class Powerup extends Entity {

	public Powerup(int x, int y) {
		super(x, y, new Texture("data/boots.png"));
		setWidth(16);
		setHeight(16);
	}
	
	public void update(GameScreen game, float delta) {
		super.update(game, delta);
		if (Intersector.overlapRectangles(getBounds(), game.getPlayer().getBounds())) {
			game.getPlayer().setSpeed(game.getPlayer().getSpeed() * 1.2f);
			game.getPowerups().remove(this);
		} else {
			for (Enemy enemy : game.getEnemies()) {
				if (Intersector.overlapRectangles(getBounds(), enemy.getBounds())) {
					enemy.setTarget(null);
					enemy.setSpeed(enemy.getSpeed() * 1.2f);
					game.getPowerups().remove(this);
					break;
				}
			}
		}
	}

}
