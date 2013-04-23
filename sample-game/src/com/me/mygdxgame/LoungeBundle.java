package com.me.mygdxgame;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class LoungeBundle {
	
	private Map<String, String> payload = new HashMap<String, String>();
	
	public LoungeBundle() {
		
	}

	public void put(String key, String data) {
		payload.put(key, data);
	}
	
	public String get(String key) {
		return payload.get(key);
	}
	
	public Set<Entry<String, String>> entrySet() {
		return payload.entrySet();
	}

}
