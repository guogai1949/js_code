package com.js.worker.code.factory;

public class FactoryProducer {

	public static BrandFactory getFactory(int type) {
		switch(type) {
		case 1:
			return new HPFactory();
		case 2:
			return new DELLFactory();
			default:
				return null;
		}

	}
}
