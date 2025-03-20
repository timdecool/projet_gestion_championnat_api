package com.ipi.championship.repositories;

import com.ipi.championship.models.Championship;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChampionshipRepository extends CrudRepository<Championship, Long> {

    @Override
    List<Championship> findAll();

}
