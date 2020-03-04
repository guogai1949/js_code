package com.js.worker.code.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ConnectionProxy implements InvocationHandler {
	
	
	private Connection connection;
	

	public ConnectionProxy(Connection connection) {
		super();
		this.connection = connection;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		if(Connection.class.isAssignableFrom(proxy.getClass()) && method.getName().equals("close")) {
			return null;
		} else {
			return method.invoke(connection, args);
		}
	}
	
	public Connection getConnectionProxy() {
		return (Connection) Proxy.newProxyInstance(getClass().getClassLoader(), new Class[] {Connection.class}, this);
		
	}

}
