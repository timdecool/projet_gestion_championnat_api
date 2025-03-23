package com.ipi.championship.models;


import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    private Day day;

    @NotNull(message="Team 1 Points must be defined")
    @Min(value = 0, message="Points scored are at least 0")
    private Integer team1point;

    @NotNull(message="Team 2 Points must be defined")
    @Min(value = 0, message="Points scored are at least 0")
    private Integer team2point;

    @ManyToOne
    private Team team1;

    @ManyToOne
    private Team team2;

    public Game(Day day, Integer team1Point, Integer team2Point, Team team1, Team team2) {
        this.day = day;
        this.team1point = team1Point;
        this.team2point = team2Point;
        this.team1 = team1;
        this.team2 = team2;
    }

    public Game() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Day getDay() {
        return day;
    }

    public void setDay(Day day) {
        this.day = day;
    }

    public @NotNull(message = "Team 1 Points must be defined") @Min(value = 0, message = "Points scored are at least 0") Integer getTeam1point() {
        return team1point;
    }

    public void setTeam1point(@NotNull(message = "Team 1 Points must be defined") @Min(value = 0, message = "Points scored are at least 0") Integer team1point) {
        this.team1point = team1point;
    }

    public @NotNull(message = "Team 2 Points must be defined") @Min(value = 0, message = "Points scored are at least 0") Integer getTeam2point() {
        return team2point;
    }

    public void setTeam2point(@NotNull(message = "Team 2 Points must be defined") @Min(value = 0, message = "Points scored are at least 0") Integer team2point) {
        this.team2point = team2point;
    }

    public Team getTeam1() {
        return team1;
    }

    public void setTeam1(Team team1) {
        this.team1 = team1;
    }

    public Team getTeam2() {
        return team2;
    }

    public void setTeam2(Team team2) {
        this.team2 = team2;
    }
}
