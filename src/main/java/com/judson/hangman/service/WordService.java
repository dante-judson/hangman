package com.judson.hangman.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.judson.hangman.dto.HangmanDTO;
import com.judson.hangman.dto.WordDTO;
import com.judson.hangman.exception.GameOverException;
import com.judson.hangman.exception.WordNotFoundException;

@Service
public class WordService {

	@Autowired
	HangmanDTO hangman;
	
	private int getRandomWordIndex() {
		return new Random().nextInt(hangman.getWordList().size() - 1);
	}
	
	public WordDTO getRandWord() {
		int index = getRandomWordIndex();
		WordDTO word = new WordDTO(index, hangman.getWordList().get(index).length());
		
		return word;
	}
	
	public WordDTO attempt(Set<Character> letters, int index) throws GameOverException, WordNotFoundException {
		String wordString ;
		try {
			wordString = hangman.getWordList().get(index);
		} catch (IndexOutOfBoundsException e) {
			throw new WordNotFoundException("No word were found for the given index");
		}
		List<Character> word = new ArrayList<Character>();
		letters = letters.stream().map(l -> Character.toUpperCase(l)).collect(Collectors.toSet());
		
		for(int i = 0; i < wordString.length(); i++) {
			if(letters.contains(wordString.toUpperCase().charAt(i))) {
				word.add(wordString.charAt(i));
			} else {
				word.add(null);
			}
		}
		
		letters.removeAll(word);
		if(letters.size() > 5) {
			throw new GameOverException("Game Over");
		}
		
		WordDTO wordDTO = new WordDTO(index, wordString.length());
		wordDTO.setWord(word);
		wordDTO.setWrongLetters(letters);
		
		return wordDTO;

	}
	
}
