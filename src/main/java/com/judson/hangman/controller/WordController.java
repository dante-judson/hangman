package com.judson.hangman.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.judson.hangman.dto.WordDTO;
import com.judson.hangman.exception.GameOverException;
import com.judson.hangman.exception.WordNotFoundException;
import com.judson.hangman.service.WordService;

@Controller
@RequestMapping("word")
public class WordController {

	@Autowired
	private WordService service;
	
	@GetMapping(value = "random")
	public ResponseEntity<WordDTO> getRandoWord() {
		return ResponseEntity.ok(service.getRandWord());
	}
	
	@PostMapping(value="{id}/attempt")
	public ResponseEntity<WordDTO> attempt(@RequestBody Set<Character> letters, @PathVariable int id) throws GameOverException, WordNotFoundException{
		return ResponseEntity.ok(service.attempt(letters, id));
	}
}
