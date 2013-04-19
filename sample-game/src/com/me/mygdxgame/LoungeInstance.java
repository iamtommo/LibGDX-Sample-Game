package com.me.mygdxgame;

import andlabs.lounge.LoungeGameController;

public abstract class LoungeInstance {
	
	private LoungeGameController controller;
	
	public LoungeInstance(LoungeGameController controller) {
		this.controller = controller;
	}
	
	public abstract void onCheckIn(String player);
	
	public abstract void onCheckOut(String player);
	
	public abstract void onAllPlayerCheckedIn();
	
	public abstract void onGameMessage(LoungeBundle payload);

	public LoungeGameController getController() {
		return controller;
	}

}
