package com.me.mygdxgame;

import andlabs.lounge.LoungeGameCallback;
import andlabs.lounge.LoungeGameController;
import andlabs.lounge.Multiplayable;
import andlabs.lounge.lobby.LoungeConstants;
import android.os.Bundle;

import com.me.mygdxgame.LoungeInstance;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;

public class MainActivity extends AndroidApplication implements Multiplayable, LoungeGameCallback {
	
	private LoungeInstance lounge;
	private LoungeGameController controller = new LoungeGameController();
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        controller = new LoungeGameController();
        lounge = new Game(controller);
        
        AndroidApplicationConfiguration cfg = new AndroidApplicationConfiguration();
        cfg.useGL20 = false;
        
        initialize((ApplicationListener) lounge, cfg);
    }

	@Override
	public void onCheckIn(String player) {
		lounge.onCheckIn(player);
	}

	@Override
	public void onAllPlayerCheckedIn() {
		lounge.onAllPlayerCheckedIn();
	}

	@Override
	public void onGameMessage(Bundle msg) {
		LoungeBundle lb = new LoungeBundle();
		for (String key : msg.keySet()) {
			lb.put(key, msg.get(key));
		}
		lounge.onGameMessage(lb);
	}

	@Override
	public void onCheckOut(String player) {
		lounge.onCheckOut(player);
	}
	
	@Override
	protected void onStart() {
		super.onStart();
		controller.bindServiceTo(this);
	}

	@Override
	public void onResume() {
		controller.registerCallback(this);
		controller.checkin(getIntent().getStringExtra(LoungeConstants.EXTRA_MATCH_ID));
		super.onResume();
	}

	@Override
	public void onPause() {
		controller.unregisterCallback(this);
		super.onPause();
	}

	@Override
	public void onStop() {
		controller.unbindServiceFrom(this);
		super.onStop();
	}
    
}