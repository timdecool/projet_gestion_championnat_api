package com.ipi.championship.models;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.List;

@Entity
public class Day {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "number is missing")
    private String number;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    private Championship championship;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "day")
    private List<Game> games;

    public Day(String number, Championship championship) {
        this.number = number;
        this.championship = championship;
    }

    public Day() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Championship getChampionship() {
        return championship;
    }

    public void setChampionship(Championship championship) {
        this.championship = championship;
    }

    public List<Game> getGames() {
        return games;
    }

    public void setGames(List<Game> games) {
        this.games = games;
    }
}
