package com.judson.hangman.controller.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.judson.hangman.exception.GameOverException;
import com.judson.hangman.exception.WordNotFoundException;

@ControllerAdvice
public class WordControllerAdvice extends ResponseEntityExceptionHandler {

	@ExceptionHandler(WordNotFoundException.class)
	@ResponseStatus(value=HttpStatus.NOT_FOUND)
	public void handleWordNotFoundException() {
		
	}
	
	@ExceptionHandler(GameOverException.class)
	public ResponseEntity<Error> handleGameOverException(GameOverException ex) {
		return ResponseEntity.badRequest().body(new Error(ex.getMessage(),GameOverException.class));
	}
	
	
	
	
	
	static class Error {
		String message;
		
		Class<? extends Exception> type;
		
		public Error(String message, Class<? extends Exception> type) {
			this.message = message;
			this.type = type;
		}
		
		public String getType() {
			return this.type.getSimpleName();
		}
		
		public String getMessage() {
			return message;
		}
	}
}
