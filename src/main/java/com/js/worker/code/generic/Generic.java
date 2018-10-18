package com.js.worker.code.generic;

public class Generic<T> implements Generator<T>{
	
	private T key;
	
	public Generic(T key) {
		this.key = key;
	}
	
	@Override
	public T getKey() {
		return key;
	}

	@Override
	public void showKeyValue(Generator<?> generator) {
		System.out.println(generator.getKey());
	}

}
