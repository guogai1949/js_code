package com.js.worker.code.simplefactory;

public class MouseFactory {

	public Mouse createMouse(int type) {
		switch(type) {
//		case "HP":
		case 1:
			new HPMouse();
//		case "DELL":
		case 2:
			new DELLMouse();
			default:
				return null;
		}
	}
}
