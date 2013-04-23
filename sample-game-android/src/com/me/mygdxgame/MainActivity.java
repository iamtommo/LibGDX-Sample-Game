package com.me.mygdxgame;

import java.util.Map;

import andlabs.lounge.LoungeGameCallback;
import andlabs.lounge.LoungeGameController;
import andlabs.lounge.Multiplayable;
import andlabs.lounge.lobby.LoungeConstants;
import android.os.Bundle;
import android.util.Log;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;

public class MainActivity extends AndroidApplication implements Multiplayable, LoungeGameCallback, LoungeBundleInterceptor {
	
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
		Log.i("TOMMO", "checked in: " + player); 
		lounge.onCheckIn(player);
	}

	@Override
	public void onAllPlayerCheckedIn() {
		Log.i("TOMMO", "all checked in!"); 
		lounge.onAllPlayerCheckedIn();
	}

	@Override
	public void onGameMessage(Bundle msg) {
		LoungeBundle lb = new LoungeBundle();
		for (String key : msg.keySet()) {
			lb.put(key, msg.getString(key));
		}
		Log.i("TOMMO", "received msg"); 
		lounge.onGameMessage(lb);
	}

	@Override
	public void onCheckOut(String player) {
		Log.i("TOMMO", "checked out: " + player); 
		lounge.onCheckOut(player);
	}
	
	@Override
	protected void onStart() {
		super.onStart();
		controller.bindServiceTo(this);
		lounge.setMatchId(getIntent().getStringExtra(LoungeConstants.EXTRA_MATCH_ID));
		lounge.setInterceptor(this);
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

	@Override
	public void receive(LoungeBundle bundle) {
		Bundle b = new Bundle();
		for (Map.Entry<String, String> entry : bundle.entrySet()) {
			b.putString(entry.getKey(), entry.getValue());
		}
		controller.sendGameMove(lounge.getMatchId(), b);
	}
    
}