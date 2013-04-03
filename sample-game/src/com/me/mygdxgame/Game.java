package com.me.mygdxgame;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.me.mygdxgame.screen.GameScreen;

public class Game implements ApplicationListener {
	
	public static int width = 1;
	public static int height = 1;
	
	private Screen screen;
	
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
}
