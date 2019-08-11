package com.judson.hangman.dto;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

public class WordDTO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8653267579345349031L;

	private Integer id;
	
	private Integer length;
	
	private Set<Character> wrongLetters;
	
	private List<Character> word;
	
	public WordDTO() {

	}
	
	public WordDTO(Integer id, Integer length) {
		super();
		this.id = id;
		this.length = length;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getLength() {
		return length;
	}

	public void setLength(Integer length) {
		this.length = length;
	}

	public Set<Character> getWrongLetters() {
		return wrongLetters;
	}

	public void setWrongLetters(Set<Character> wrongLetters) {
		this.wrongLetters = wrongLetters;
	}

	public List<Character> getWord() {
		return word;
	}

	public void setWord(List<Character> word) {
		this.word = word;
	}
	
}
