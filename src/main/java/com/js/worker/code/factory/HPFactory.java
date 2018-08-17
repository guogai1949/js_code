package com.js.worker.code.factory;

public class HPFactory implements BrandFactory {

	@Override
	public Mouse createMouse() {
		return new HPMouse();
	}

	@Override
	public Keybo createKeybo() {
		return new HPKeybo();
	}

}
