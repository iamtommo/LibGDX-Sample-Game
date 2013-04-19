package com.me.mygdxgame;

import andlabs.lounge.LoungeGameController;

public abstract class LoungeInstance {
	
	private LoungeGameController controller;
	private String matchId;
	private LoungeBundleInterceptor interceptor;
	
	public LoungeInstance(LoungeGameController controller) {
		this.controller = controller;
	}
	
	public abstract void onCheckIn(String player);
	
	public abstract void onCheckOut(String player);
	
	public abstract void onAllPlayerCheckedIn();
	
	public abstract void onGameMessage(LoungeBundle payload);
	
	public void sendGameMessage(LoungeBundle b) {
		interceptor.receive(b);
	}

	public LoungeGameController getController() {
		return controller;
	}

	public String getMatchId() {
		return matchId;
	}

	public void setMatchId(String matchId) {
		this.matchId = matchId;
	}

	public LoungeBundleInterceptor getInterceptor() {
		return interceptor;
	}

	public void setInterceptor(LoungeBundleInterceptor interceptor) {
		this.interceptor = interceptor;
	}

}
