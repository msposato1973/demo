package com.fsb.demo.service.impl;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.fsb.demo.error.GameOwnException;
import com.fsb.demo.model.Game;
import com.fsb.demo.service.GameService;
 

@Service
public class GameServiceImpl implements GameService{
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	private Map<String, Game> gameCache = new ConcurrentHashMap<>();
	

	@Override
	public Game findByName(String name) {
		logger.debug("findByName(...)");
	    return gameCache.get(name);
	}

	@Override
	public Game saveGame(Game game) {
		logger.debug("saveGame(...)");
		 
		if (gameCache.containsKey(game.getName())) {
            throw new GameOwnException("A game with the same name already exists.");
        }
        gameCache.put(game.getName(), game);
        return game;
    }

	@Override
	public void updateGame(Game game) {
		logger.debug("updateGame(...)");
		
		if (!gameCache.containsKey(game.getName())) {
            throw new GameOwnException("Game with the given name does not exist.");
        }
		gameCache.put(game.getName(), game);
	}

	@Override
	public boolean deleteGameByName(String name) {
		logger.debug("deleteGameByName(...)");
		if (!gameCache.containsKey(name)) {
            throw new GameOwnException("Game with the given name does not exist.");
        }
		gameCache.remove(name);
		return true;
	}

	@Override
	public List<Game> findAllGames() {
		logger.debug("findAllGames()");
		return gameCache.values().stream().collect(Collectors.toList());
	}

	@Override
	public void deleteAllGames() {
		logger.debug("deleteAllGames()");
		gameCache.clear();
	}

	@Override
	public boolean isGameExist(Game game) {
		logger.debug("isGameExist(..)");
		return findByName(game.getName())!=null;
	}

	@Override
	public boolean isActive(Game game) {
		logger.debug("isActive(...)");
		return findByName(game.getName()).isActive();
	}

	 
}
