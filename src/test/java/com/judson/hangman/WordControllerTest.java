package com.judson.hangman;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anySet;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.judson.hangman.dto.WordDTO;
import com.judson.hangman.exception.GameOverException;
import com.judson.hangman.exception.WordNotFoundException;
import com.judson.hangman.service.WordService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class WordControllerTest {
	
	@Autowired
	private MockMvc mockMvc;



    @LocalServerPort
    private int port;

    
    @MockBean
    private WordService wordService;
    
    @Autowired
    private ObjectMapper objectMapper;

    @Test
	public void getRandomWord() throws Exception {
    	when(wordService.getRandWord()).thenReturn(new WordDTO());
    	ResultActions actions = mockMvc.perform(get("/word/random"))
    	.andDo(print())
    	.andExpect(status().isOk());
    	
    	String result = actions.andReturn().getResponse().getContentAsString();
    	objectMapper.readValue(result, WordDTO.class);
	}

    @Test
    public void attemptWord() throws Exception {
    	when(wordService.attempt(anySet(), anyInt())).thenReturn(new WordDTO());
    	ResultActions actions = mockMvc.perform(post("/word/"+anyInt()+"/attempt").content(asJsonString(anySet())).contentType(MediaType.APPLICATION_JSON))
    	.andDo(print())
    	.andExpect(status().isOk());
    	
    	String result = actions.andReturn().getResponse().getContentAsString();
    	objectMapper.readValue(result, WordDTO.class);
    }

    @Test
    public void attemptWordNotFoundException() throws Exception {
    	when(wordService.attempt(anySet(),anyInt())).thenThrow(new WordNotFoundException("NOT_FOUND"));
    	mockMvc.perform(MockMvcRequestBuilders.post("/word/"+anyInt()+"/attempt").content(asJsonString(anySet())).contentType(MediaType.APPLICATION_JSON))
    	.andDo(print())
    	.andExpect(status().isNotFound());
    	
    }

    @Test
    public void attemptGameOverException() throws Exception {
    	when(wordService.attempt(anySet(), anyInt())).thenThrow(new GameOverException("Game Over"));
    	mockMvc.perform(post("/word/"+anyInt()+"/attempt").content(asJsonString(anySet())).contentType(MediaType.APPLICATION_JSON))
    	.andDo(print())
    	.andExpect(status().isBadRequest());
    }
    
    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
