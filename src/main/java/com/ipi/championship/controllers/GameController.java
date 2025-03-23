package com.ipi.championship.controllers;

import com.ipi.championship.models.*;
import com.ipi.championship.repositories.GameRepository;
import com.ipi.championship.repositories.TeamRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/games")
public class GameController {

    GameRepository gameRepository;
    TeamRepository teamRepository;
    public GameController(GameRepository gameRepository, TeamRepository teamRepository) {
        this.gameRepository = gameRepository;
        this.teamRepository = teamRepository;
    }

    @GetMapping
    public List<Game> getAllGames() {
        return gameRepository.findAll();
    }

    @GetMapping("/{id}")
    public Game getGameById(@PathVariable(name="id", required=false) Game game) {
        if(game == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Game not found");
        }
        return game;
    }

    @GetMapping("/championship/{id}")
    public List<Game> getChampionshipGames(@PathVariable(name="id", required=false) Championship championship) {
        if(championship == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Championship not found");
        }
        return gameRepository.findAllByChampionshipId(championship.getId());
    }

    @GetMapping("/day/{id}")
    public List<Game> getDayGames(@PathVariable(name="id", required=false) Day day) {
        if(day == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Day not found");
        }
        return gameRepository.findAllByDay(day);
    }

    @PostMapping("/day/{id}")
    public ResponseEntity<Game> createGame(
            @RequestBody Game game, @PathVariable(name="id", required=false) Day day
    ) {
        if(day == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Day not found");
        }
        game.setDay(day);
        Game savedGame = gameRepository.save(game);
        return new ResponseEntity<>(savedGame, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Game> updateGame(
            @RequestBody Game game,
            @PathVariable(name="id", required=false) Game gameToUpdate
    )
    {
        if(gameToUpdate == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Game not found");
        }
        game.setId(gameToUpdate.getId());
        game.setDay(gameToUpdate.getDay());
        Game updatedGame = gameRepository.save(game);
        return new ResponseEntity<>(updatedGame, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void deleteGame(@PathVariable(name="id", required=false) Game game) {
        if(game == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Game not found");
        }
        gameRepository.delete(game);
    }

}
