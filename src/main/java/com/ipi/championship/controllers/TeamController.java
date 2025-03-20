package com.ipi.championship.controllers;

import com.ipi.championship.models.Championship;
import com.ipi.championship.models.Team;
import com.ipi.championship.repositories.TeamRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/teams")
public class TeamController {

    TeamRepository teamRepository;
    public TeamController(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    @GetMapping
    public List<Team> getAllTeams() {
        return teamRepository.findAll();
    }

    @GetMapping("/championship/{id}")
    public List<Team> getTeamsByChampionship(@PathVariable(name="id", required=false) Championship championship) {
        if(championship == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Championship not found");
        }

        return teamRepository.findAllByChampionship(championship);
    }

    @GetMapping("/{id}")
    public Team getTeamById(@PathVariable(name="id", required=true) Team team) {
        if(team == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Team not found");
        }
        return team;
    }

    @PostMapping
    public ResponseEntity<Team> createTeam(@RequestBody Team team) {
        Team savedTeam = teamRepository.save(team);
        return new ResponseEntity<>(savedTeam, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Team> updateTeam(
            @RequestBody Team team,
            @PathVariable(name="id", required=false) Team teamToUpdate
    )
    {
        if(teamToUpdate == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Team not found");
        }
        team.setId(teamToUpdate.getId());
        Team updatedTeam = teamRepository.save(team);
        return new ResponseEntity<>(updatedTeam, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void deleteTeam(@PathVariable(name="id", required=true) Team team) {
        if(team == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Team not found");
        }

        teamRepository.delete(team);
    }

}
