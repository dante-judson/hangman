package com.judson.hangman.config;


import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.util.ResourceUtils;

import com.judson.hangman.dto.HangmanDTO;

@Configuration
public class XmlConfig {

	@Autowired
	private ResourceLoader resourceLoader;
	
	@Bean
	public HangmanDTO createHangman() {
		HangmanDTO hangman = new HangmanDTO();
		
		File xmlFile;
		JAXBContext jaxbContext;
		
		try {
			Resource resource = resourceLoader.getResource("classpath:hangman.xml");
//			xmlFile = ResourceUtils.getFile("classpath:hangman.xml");
//			xmlFile = ResourceUtils.get 
			resource.getInputStream();
			xmlFile = resource.getFile();
			jaxbContext = JAXBContext.newInstance(HangmanDTO.class);
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
			hangman = (HangmanDTO) unmarshaller.unmarshal(xmlFile);
		} catch (Exception e) {
			throw new BeanCreationException(e.getMessage());
		}
		
		if(hangman.getWordList() == null || hangman.getWordList().isEmpty()) {
			throw new BeanCreationException("Hangman", "Wordlist is null or empty. Please check hangman.xml and relauch the app.");
		}
		
		return hangman;
	}
	
}
