package com.judson.hangman;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.judson.hangman.dto.HangmanDTO;
import com.judson.hangman.exception.GameOverException;
import com.judson.hangman.exception.WordNotFoundException;
import com.judson.hangman.service.WordService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WordServiceTest {

	@InjectMocks
	private WordService service;
	
	@Mock
	private HangmanDTO hangman;
	
	private static List<String> WORD_LIST = Arrays.asList("TEST","JUNIT");

	private static Set<Character> LETTERS = new HashSet<Character>(Arrays.asList('t','E'));

	private static Set<Character> GAME_OVER_LETTERS = new HashSet<Character>(Arrays.asList('a','b','c','d','f','g'));
	
	private static int WORD_INDEX = 0;

	private static int INVALID_WORD_INDEX = -1;
	
	@Test
	public void getRamdonWordTest() {
		Mockito.when(hangman.getWordList()).thenReturn(WORD_LIST);
		service.getRandWord();
	}
	
	@Test
	public void testAttempt() throws GameOverException, WordNotFoundException {
		Mockito.when(hangman.getWordList()).thenReturn(WORD_LIST);
	}

	@Test
	public void testAttemptGameOverException() throws WordNotFoundException {
		Mockito.when(hangman.getWordList()).thenReturn(WORD_LIST);
		try {
			service.attempt(GAME_OVER_LETTERS, WORD_INDEX);
		} catch (GameOverException e) {
			assertThat(e);
		}
	}

	@Test
	public void testAttemptWordNotFoundException() throws GameOverException {
		Mockito.when(hangman.getWordList()).thenReturn(WORD_LIST);
		try {
			service.attempt(LETTERS, INVALID_WORD_INDEX);
		} catch (WordNotFoundException e) {
			assertThat(e);
		}
		
	}
}
