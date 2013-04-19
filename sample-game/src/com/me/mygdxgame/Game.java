package com.me.mygdxgame;

import andlabs.lounge.LoungeGameController;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.me.mygdxgame.screen.GameScreen;

public class Game extends LoungeInstance implements ApplicationListener {

	public static LoungeInstance lounge;
	
	public static int width = 1;
	public static int height = 1;
	
	private Screen screen;
	
	public Game(LoungeGameController controller) {
		super(controller);
		lounge = this;
	}
	
	@Override
	public void create() {		
		screen = new GameScreen();
		screen.show();
	}

	@Override
	public void dispose() {
		
	}

	@Override
	public void render() {		
		screen.render(Gdx.graphics.getDeltaTime());
	}

	@Override
	public void resize(int width, int height) {
		Game.width = width;
		Game.height = height;
		if (screen != null) {
			screen.resize(width, height);
		}
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}

	@Override
	public void onCheckIn(String player) {
		if (screen != null) {
			((GameScreen) screen).onCheckIn(player);
		}
	}

	@Override
	public void onCheckOut(String player) {
		if (screen != null) {
			((GameScreen) screen).onCheckOut(player);
		}
	}

	@Override
	public void onAllPlayerCheckedIn() {
		if (screen != null) {
			((GameScreen) screen).onAllPlayerCheckedIn();
		}
	}

	@Override
	public void onGameMessage(LoungeBundle payload) {
		if (screen != null) {
			((GameScreen) screen).onGameMessage(payload);
		}
	}
	
}
