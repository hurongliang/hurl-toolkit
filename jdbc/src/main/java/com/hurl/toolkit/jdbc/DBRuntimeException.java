package com.hurl.toolkit.jdbc;

public class DBRuntimeException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2480152947804136025L;
	public DBRuntimeException(String message){
		super(message);
	}
	public DBRuntimeException(String message,Throwable t){
		super(message,t);
	}
}
