package com.me.mygdxgame;

import java.util.HashMap;
import java.util.Map;

public class LoungeBundle {
	
	private Map<String, Object> payload = new HashMap<String, Object>();
	
	public LoungeBundle() {
		
	}

	public void put(String key, Object data) {
		payload.put(key, data);
	}
	
	public Object get(String key) {
		return payload.get(key);
	}

}
