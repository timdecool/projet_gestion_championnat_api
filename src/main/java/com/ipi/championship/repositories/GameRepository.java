package com.ipi.championship.repositories;

import com.ipi.championship.models.Day;
import com.ipi.championship.models.Game;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GameRepository extends CrudRepository<Game, Long> {
    @Override
    List<Game> findAll();

    @Query("SELECT g FROM Game g WHERE g.day.championship.id = :championshipId")
    List<Game> findAllByChampionshipId(@Param("championshipId") Long championshipId);

    List<Game> findAllByDay(Day day);
}
