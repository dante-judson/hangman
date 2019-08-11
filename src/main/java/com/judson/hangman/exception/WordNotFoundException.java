package com.judson.hangman.exception;

public class WordNotFoundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6819845210611732005L;

	public WordNotFoundException(String msg) {
		super(msg);
	}
	
}
