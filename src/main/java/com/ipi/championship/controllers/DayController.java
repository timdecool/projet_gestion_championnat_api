package com.ipi.championship.controllers;

import com.ipi.championship.models.Championship;
import com.ipi.championship.models.Day;
import com.ipi.championship.repositories.DayRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/days")
public class DayController {

    DayRepository dayRepository;
    public DayController(DayRepository dayRepository) {
        this.dayRepository = dayRepository;
    }

    @GetMapping
    public List<Day> getDays() {
        return dayRepository.findAll();
    }

    @GetMapping("/{id}")
    public Day getDayById(@PathVariable(name="id", required=true) Day day) {
        if(day == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Day not found.");
        }
        return day;
    }

    @GetMapping("/championship/{id}")
    public List<Day> getDaysByChampionship(@PathVariable(name="id", required=false) Championship championship) {
        if(championship == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Championship not found.");
        }
        return championship.getDays();
    }

    @PostMapping("/championship/{id}")
    public ResponseEntity<Day> createDay(
            @PathVariable(name="id", required=false) Championship championship,
            @RequestBody Day day
    )
    {
        if(championship == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Championship not found.");
        }
        day.setChampionship(championship);
        Day savedDay = dayRepository.save(day);
        return new ResponseEntity<>(savedDay, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Day> updateDay(
            @PathVariable(name="id", required=false) Day dayToUpdate,
            @RequestBody Day day

    )
    {
        if(dayToUpdate == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Day not found.");
        }
        day.setId(dayToUpdate.getId());
        Day savedDay = dayRepository.save(day);
        return new ResponseEntity<>(savedDay, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void deleteDay(@PathVariable(name="id", required=false) Day day) {
        if(day == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Day not found.");
        }
        dayRepository.delete(day);
    }

}
