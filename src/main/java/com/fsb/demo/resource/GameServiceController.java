package com.fsb.demo.resource;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.fsb.demo.error.CostumErrorType;
import com.fsb.demo.error.GameOwnException;
import com.fsb.demo.model.Game;
import com.fsb.demo.service.GameService;
import com.fsb.demo.utility.WebInfo;
 
@CrossOrigin
@RestController
@RequestMapping(WebInfo.ROOT)
public class GameServiceController {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private final GameService gameService;

    @Autowired
    public GameServiceController(GameService gameService) {
        this.gameService = gameService;
    }
	
	
    /***
     * 
     * @return
     */
	@RequestMapping(value = WebInfo.ALLGAME, method = RequestMethod.GET)
	public ResponseEntity<List<Game>> listAllGames() {
	    List<Game> games = gameService.findAllGames();
	    if(games.isEmpty()) {
	        return new ResponseEntity(HttpStatus.NO_CONTENT);
	    }
	    return new ResponseEntity<List<Game>>(games, HttpStatus.OK);
	}
	
	
	/***
	 * 
	 * @param name
	 * @return
	 */
	@RequestMapping(value = WebInfo.FINDByNAME, method = RequestMethod.GET)
	public ResponseEntity<?> getGame(@PathVariable("name") String name) {
	    logger.info("Fetching Game with name {}", name);
	    Game game = gameService.findByName(name);
	    if(game == null) {
	        logger.error("Game with name {} not found.", name);
	        return new ResponseEntity(new CostumErrorType("Game with name " + name + " not found"), HttpStatus.NOT_FOUND);
	    }
	    return new ResponseEntity<Game>(game, HttpStatus.OK);
	}
	
	/***
	 * 
	 * @param game
	 * @param ucBuilder
	 * @return
	 */
	@RequestMapping(value= WebInfo.GAME, method = RequestMethod.POST)
	public ResponseEntity<?> createGame(@RequestBody Game game, UriComponentsBuilder ucBuilder) 
			throws GameOwnException{
	    logger.info("Creating Game: {}", game);

	    if(gameService.isGameExist(game)) {
	        logger.error("Unable to create. A Game with name {} already exists.", game.getName());
	        return new ResponseEntity(new CostumErrorType("Unable to create. A Game with name " + game.getName() + "already exists."), HttpStatus.CONFLICT);    
	    }
	    gameService.saveGame(game);

	    HttpHeaders headers = new HttpHeaders();
	    headers.setLocation(ucBuilder.path("/api/game/{name}").buildAndExpand(game.getName()).toUri());
	    return new ResponseEntity<String>(headers, HttpStatus.CREATED);
	    
	}
	 
	/***
	 * 
	 * @param game
	 * @return
	 */
	@RequestMapping(value = WebInfo.UPNAME, method = RequestMethod.PUT)
    public ResponseEntity<List<Game>> updateGame(@RequestBody Game game) throws  GameOwnException{
         gameService.updateGame(game);
         
         List<Game> games = gameService.findAllGames();
         
         if(games.isEmpty()) {
 	        return new ResponseEntity(HttpStatus.NO_CONTENT);
 	     }
         
 	     return new ResponseEntity<List<Game>>(games, HttpStatus.OK);
    }
	
	/***
	 * 
	 * @param name
	 */
	@DeleteMapping(WebInfo.DELNAME)
	public boolean deleteGame(@PathVariable String name) throws  GameOwnException{
		  return gameService.deleteGameByName(name);
    }
	
	/***
	 * 
	 */
	@DeleteMapping(WebInfo.DELALL)
    public void deleteAllGames() {
        gameService.deleteAllGames();
    }
}
