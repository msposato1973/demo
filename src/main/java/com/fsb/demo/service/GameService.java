package com.fsb.demo.service;

import java.util.List;

import com.fsb.demo.model.Game;

public interface GameService {

	  Game findByName(String name);

	  Game saveGame(Game game);

	  void updateGame(Game game);

	  boolean deleteGameByName(String name);

	  List<Game> findAllGames();

	  void deleteAllGames();

	  boolean isGameExist(Game game);
	  
	  boolean isActive(Game game);
}
