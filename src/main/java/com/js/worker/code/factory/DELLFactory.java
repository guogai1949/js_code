package com.js.worker.code.factory;

public class DELLFactory implements BrandFactory {

	@Override
	public Mouse createMouse() {
		return new DELLMouse();
	}

	@Override
	public Keybo createKeybo() {
		return new DELLKeybo();
	}

	
}
