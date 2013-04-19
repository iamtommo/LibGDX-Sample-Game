package com.me.mygdxgame;

public interface LoungeReceiver {
	
public abstract void onCheckIn(String player);
	
	public void onCheckOut(String player);
	
	public void onAllPlayerCheckedIn();
	
	public void onGameMessage(LoungeBundle payload);

}
