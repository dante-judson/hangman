package com.judson.hangman.config;


import java.io.File;
import java.nio.file.Paths;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import org.springframework.beans.factory.BeanCreationException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ResourceUtils;

import com.judson.hangman.dto.HangmanDTO;

@Configuration
public class XmlConfig {

	@Bean
	public HangmanDTO createHangman() {
		HangmanDTO hangman = new HangmanDTO();
		
		File xmlFile;
		JAXBContext jaxbContext;
		
		try {
			xmlFile = ResourceUtils.getFile(Paths.get("src/main/resources").toAbsolutePath().toString()+"/hangman.xml");
			jaxbContext = JAXBContext.newInstance(HangmanDTO.class);
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
			hangman = (HangmanDTO) unmarshaller.unmarshal(xmlFile);
		} 
		catch (Exception e) {
			throw new BeanCreationException(e.getMessage());
		}
		
		if(hangman.getWordList() == null || hangman.getWordList().isEmpty()) {
			throw new BeanCreationException("Hangman", "Wordlist is null or empty. Please check hangman.xml and relauch the app.");
		}
		
		return hangman;
	}
	
}
