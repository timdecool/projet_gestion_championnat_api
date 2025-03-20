package com.ipi.championship.repositories;

import com.ipi.championship.models.Championship;
import com.ipi.championship.models.Team;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeamRepository extends CrudRepository<Team, Long> {
    @Override
    List<Team> findAll();

    List<Team> findAllByChampionship(Championship championship);
}
