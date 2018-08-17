package com.js.worker.code.callback;

public class Test {
	
	public static void main(String args[]) {
		LiBoss liboss = new LiBoss("li", new Employee());
		
		liboss.giveWoker();
	}

}
