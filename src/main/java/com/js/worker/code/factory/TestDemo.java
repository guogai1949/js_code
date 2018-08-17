package com.js.worker.code.factory;

public class TestDemo {

	public static void main(String[] args) {
		BrandFactory hpFactory = FactoryProducer.getFactory(1);
		Keybo hpKeybo = hpFactory.createKeybo();
		hpKeybo.sayHi();

	}

}
