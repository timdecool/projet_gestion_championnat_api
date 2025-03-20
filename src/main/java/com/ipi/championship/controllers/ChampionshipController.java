package com.ipi.championship.controllers;

import com.ipi.championship.models.Championship;
import com.ipi.championship.repositories.ChampionshipRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/championship")
public class ChampionshipController {

    ChampionshipRepository championshipRepository;
    public ChampionshipController(ChampionshipRepository championshipRepository) {
        this.championshipRepository = championshipRepository;
    }

    @GetMapping
    public List<Championship> getAllChampionships() {
        return championshipRepository.findAll();
    }

    @GetMapping("/{id}")
    public Championship getChampionshipById(@PathVariable(name="id", required = false) Championship championship) {
        if(championship == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Championship not found");
        }
        return championship;
    }

    @PostMapping
    public ResponseEntity<Championship> createChampionship(@Valid @RequestBody Championship championship) {
        Championship savedChampionship = championshipRepository.save(championship);
        return new ResponseEntity<>(savedChampionship, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Championship> updateChampionship(
            @Valid @RequestBody Championship championship,
            @PathVariable(name="id", required=false) Championship championshipToUpdate
    )
    {
        if(championshipToUpdate == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Championship not found");
        }
        championship.setId(championshipToUpdate.getId());
        Championship savedChampionship = championshipRepository.save(championship);
        return new ResponseEntity<>(savedChampionship, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void deleteChampionship(@PathVariable(name="id", required = false) Championship championship) {
        if(championship == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Championship not found");
        }
        championshipRepository.delete(championship);
    }


}
