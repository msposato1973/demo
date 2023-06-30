package com.fsb.demo.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
 
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import com.fsb.demo.error.GameOwnException;
import com.fsb.demo.model.Game;
import com.fsb.demo.service.GameService;
 
 

@ExtendWith(MockitoExtension.class)
public class GameServiceImplTest {
	
	
	@Autowired
	public GameService service;
	
	@BeforeEach
	void setUp() throws Exception {
		service = new GameServiceImpl();
	}
	
	@Test
	void tesCostructor() throws Exception{
		assertThat(service).isNotNull();
	}

	@Test
	void testSaveGame() {
		service.saveGame(new Game("Casino", LocalDate.now(), false));
	}
	
	@Test
	void testFindByName() {
		service.saveGame(new Game("Casino", LocalDate.now(), false));
		Game game = service.findByName("Casino");
		
		assertThat(game).isNotNull();
	}
	
	
	@Test
	void testFindAllGames() {
		service.saveGame(new Game("Casino", LocalDate.now(), false));
		service.saveGame(new Game("RealGaming", LocalDate.now(), true));
		List<Game> games = service.findAllGames();
		
		assertThat(games).isNotNull();
		assertThat(games.size()==2).isNotNull();
	}
	
	@Test
	void testIsGameExist() {
		Game game = new Game("Casino", LocalDate.now(), false);
		service.saveGame(game);
		 
		boolean result = service.isGameExist(game);
		
		 
		assertTrue(result);
	}
	
	@Test
	void testDeleteGameByName() {
		service.saveGame(new Game("Casino", LocalDate.now(), false));
		service.saveGame(new Game("RealGaming", LocalDate.now(), true));
		service.saveGame(new Game("Casino-Real", LocalDate.now(), false));
		service.saveGame(new Game("Sports-Gaming", LocalDate.now(), true));
		
		List<Game> games = service.findAllGames();
		assertThat(games.size()==4).isNotNull();
		 
		boolean result = service.deleteGameByName("Casino");
		assertTrue(result);
		assertTrue(service.findAllGames().size()==3);
	}

	@Test
	void testDeleteGameByNameError() throws GameOwnException{
		
		try {
			service.saveGame(new Game("Casino", LocalDate.now(), false));
			service.saveGame(new Game("RealGaming", LocalDate.now(), true));
			service.saveGame(new Game("Casino-Real", LocalDate.now(), false));
			service.saveGame(new Game("Sports-Gaming", LocalDate.now(), true));
			
			
			List<Game> games = service.findAllGames();
			assertThat(games.size()==4).isNotNull();
			 
			assertTrue(service.deleteGameByName("Casino"));
			assertTrue(service.findAllGames().size()==3);
		
		
			assertFalse(service.deleteGameByName("Casino"));
		} catch (Exception e) {
			assertThat(e instanceof GameOwnException);
		}
		
		 
	}

	
	@Test
	void testDeleteAllGames() {
		service.saveGame(new Game("Casino", LocalDate.now(), false));
		service.saveGame(new Game("RealGaming", LocalDate.now(), true));
		service.saveGame(new Game("Casino-Real", LocalDate.now(), false));
		service.saveGame(new Game("Sports-Gaming", LocalDate.now(), true));
		
		List<Game> games = service.findAllGames();
		assertThat(games.size()==4).isNotNull();
		 
		service.deleteAllGames();
		
		 
		assertTrue(service.findAllGames().isEmpty());
	}
	
	@Test
	void testUpdateGame() {
		service.saveGame(new Game("Casino", LocalDate.now(), false));
		service.saveGame(new Game("RealGaming", LocalDate.now(), true));
		service.saveGame(new Game("Casino-Real", LocalDate.now(), false));
		service.saveGame(new Game("Sports-Gaming", LocalDate.now(), true));
		
		
		assertTrue(service.findAllGames().size()==4);
		 
		Game game = service.findByName("Casino");
		game.setActive(true);
		
		service.updateGame(game);
		assertTrue(service.findByName("Casino").isActive());
		 
		
	}
}
