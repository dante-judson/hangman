package com.judson.hangman.dto;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "hangman")
public class HangmanDTO {
		
	private List<String> wordList;

	@XmlElementWrapper
	@XmlElement(name = "word")
	public List<String> getWordList() {
		return wordList;
	}

	public void setWordList(List<String> wordList) {
		this.wordList = wordList;
	}
	
}
