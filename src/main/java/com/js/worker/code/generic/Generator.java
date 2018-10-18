package com.js.worker.code.generic;

public interface Generator<T> {
	
	public void showKeyValue(Generator<?> generator);
	
	public T getKey();

}
