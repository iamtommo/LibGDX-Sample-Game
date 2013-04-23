package com.me.mygdxgame.screen;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.me.mygdxgame.Game;
import com.me.mygdxgame.LoungeBundle;
import com.me.mygdxgame.LoungeReceiver;
import com.me.mygdxgame.entity.Bullet;
import com.me.mygdxgame.entity.Player;
import com.me.mygdxgame.entity.RemotePlayer;

public class GameScreen implements Screen, InputProcessor, LoungeReceiver {
	
	private Random random = new Random();
	private Player player;
	private List<Bullet> bullets = new ArrayList<Bullet>();
	private List<RemotePlayer> remotePlayers = new ArrayList<RemotePlayer>();
	private SpriteBatch batch = new SpriteBatch();
	private long lastDispatchTime = -1;
	
	@Override
	public void show() {
		Gdx.input.setInputProcessor(this);
		int x = random.nextInt(100 + random.nextInt(100));
		int y = random.nextInt(100 + random.nextInt(100));
		player = new Player(x, y);
		sendPlayerUpdate();
	}
	
	private void update(float delta) {
		if (Gdx.input.isKeyPressed(Keys.W) || Gdx.input.isKeyPressed(Keys.UP)) {
			player.setY(player.getY() + 200 * delta);
		}
		if (Gdx.input.isKeyPressed(Keys.A) || Gdx.input.isKeyPressed(Keys.LEFT)) {
			player.setX(player.getX() - 200 * delta);
		}
		if (Gdx.input.isKeyPressed(Keys.S) || Gdx.input.isKeyPressed(Keys.DOWN)) {
			player.setY(player.getY() - 200 * delta);
		}
		if (Gdx.input.isKeyPressed(Keys.D) || Gdx.input.isKeyPressed(Keys.RIGHT)) {
			player.setX(player.getX() + 200 * delta);
		}
		player.update(this, delta);
		for (RemotePlayer p : remotePlayers) {
			p.update(this, delta);
		}
		for (Bullet b : bullets) {
			b.update(this, delta);
		}
		
		if (System.currentTimeMillis() - lastDispatchTime >= 1000) {
			sendPlayerUpdate();
		}
	}

	@Override
	public void render(float delta) {
		update(delta);

		Gdx.gl.glClearColor(0f, 0f, 0f, 0f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		batch.begin();
		player.draw(batch);
		for (RemotePlayer p : remotePlayers) {
			p.draw(batch);
		}
		for (Bullet b : bullets) {
			b.draw(batch);
		}
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
		y = Game.height - y;
		Vector2 vec = new Vector2(x, y).sub(player.getX(), player.getY()).nor();
		Bullet bullet = new Bullet((int) player.getX(), (int) player.getY());
		bullet.setVelocity(vec);
		bullet.setRotation(vec.angle() - 180);
		bullets.add(bullet);
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
		player.setRotation(vec.angle() - 180);
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

	public List<Bullet> getBullets() {
		return bullets;
	}

	public List<RemotePlayer> getRemotePlayers() {
		return remotePlayers;
	}

	@Override
	public void onCheckIn(String player) {
		Gdx.app.log("", player + " checked in");
		remotePlayers.add(new RemotePlayer(player));
	}

	@Override
	public void onCheckOut(String player) {
		Gdx.app.log("", player + " checked out");
	}

	@Override
	public void onAllPlayerCheckedIn() {
		Gdx.app.log("", "all checked in");
	}

	@Override
	public void onGameMessage(LoungeBundle payload) {
		Gdx.app.log("", "message received");
		if (payload.get("player") != null) {
			int x = Integer.parseInt(payload.get("x"));
			int y = Integer.parseInt(payload.get("y"));
			int degrees = Integer.parseInt(payload.get("rotation"));
			for (RemotePlayer p : remotePlayers) {
				if (p.getName().equals(payload.get("player"))) {
					p.setX(x);
					p.setY(y);
					p.setRotation(degrees);
				}
			}
		}
	}
	
	public void sendPlayerUpdate() {
		LoungeBundle b = new LoungeBundle();
		b.put("player", "myplayer");
		b.put("x", Integer.toString((int) player.getX()));
		b.put("y", Integer.toString((int) player.getY()));
		b.put("rotation", Integer.toString((int) player.getRotation()));
		Game.lounge.sendGameMessage(b);
	}

}
