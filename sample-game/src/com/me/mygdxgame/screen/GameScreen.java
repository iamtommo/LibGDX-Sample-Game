package com.me.mygdxgame.screen;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.me.mygdxgame.Game;
import com.me.mygdxgame.entity.Enemy;
import com.me.mygdxgame.entity.Player;
import com.me.mygdxgame.entity.Powerup;

public class GameScreen implements Screen, InputProcessor {
	
	private Player player = new Player();
	private List<Enemy> enemies = new ArrayList<Enemy>();
	private SpriteBatch batch = new SpriteBatch();
	private List<Powerup> powerups = new ArrayList<Powerup>();
	private Random rand = new Random();
	private long nextSpawnTime = System.currentTimeMillis();
	private boolean gameOver = false;
	private BitmapFont font = new BitmapFont();
	private long runtime = 0;
	private long startTime = System.currentTimeMillis();
	private Texture gameOverTexture = new Texture("data/game_over.png");
	
	@Override
	public void show() {
		Gdx.input.setInputProcessor(this);
	}
	
	private void update(float delta) {
		runtime = (System.currentTimeMillis() - startTime) / 1000;
		player.update(this, delta);
		
		for (int i = 0; i < enemies.size(); i++) {
			if (enemies.get(i) != null) {
				enemies.get(i).update(this, delta);
			}
		}
		
		for (int i = 0; i < powerups.size(); i++) {
			if (powerups.get(i) != null) {
				powerups.get(i).update(this, delta);
			}
		}
		
		if (rand.nextInt(75) == 5) {
			powerups.add(new Powerup(rand.nextInt(Game.width), rand.nextInt(Game.height)));
		}
		
		if (System.currentTimeMillis() - nextSpawnTime >= 0) {
			enemies.add(new Enemy(rand.nextInt(Game.width - 32), rand.nextInt(Game.height - 32)));
			nextSpawnTime = System.currentTimeMillis() + 5000;
		}
	}

	@Override
	public void render(float delta) {
		if (!gameOver) {
			update(delta);
		}

		Gdx.gl.glClearColor(1f, 1f, 1f, 1f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		batch.begin();
		for (Powerup p : powerups) {
			batch.draw(p.getTexture(), p.getX(), p.getY());
		}
		batch.draw(player.getTexture(), player.getX(), player.getY());
		for (Enemy e : enemies) {
			batch.draw(e.getTexture(), e.getX(), e.getY());
		}
		if (gameOver) {
			batch.draw(gameOverTexture, Game.width / 2 - gameOverTexture.getWidth() / 2, Game.height / 2 - gameOverTexture.getHeight() / 2);
		}
		font.draw(batch, "Runtime: " + runtime, 25, 25);
		font.draw(batch, "Speed: " + (player.getSpeed() / player.getMaxSpeed()) + "x", 25, 45);
		batch.end();
	}

	@Override
	public void resize(int width, int height) {
		
	}

	@Override
	public void hide() {
		
	}

	@Override
	public void pause() {
		
	}

	@Override
	public void resume() {
		
	}

	@Override
	public void dispose() {
		
	}

	@Override
	public boolean keyDown(int keycode) {
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		return false;
	}

	@Override
	public boolean touchDown(int x, int y, int pointer, int button) {
		Vector2 vec = new Vector2(x, y).sub(player.getX(), player.getY()).nor();
		player.setVelocity(vec);
		return true;
	}

	@Override
	public boolean touchUp(int x, int y, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchDragged(int x, int y, int pointer) {
		y = Game.height - y;
		Vector2 vec = new Vector2(x, y).sub(player.getX(), player.getY()).nor();
		player.setVelocity(vec);
		return true;
	}

	@Override
	public boolean touchMoved(int x, int y) {
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		return false;
	}

	public Player getPlayer() {
		return player;
	}

	public List<Enemy> getEnemies() {
		return enemies;
	}
	
	public List<Powerup> getPowerups() {
		return powerups;
	}

	public boolean isGameOver() {
		return gameOver;
	}

	public void setGameOver(boolean gameOver) {
		this.gameOver = gameOver;
	}

}
