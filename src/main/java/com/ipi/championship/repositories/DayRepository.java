package com.ipi.championship.repositories;

import com.ipi.championship.models.Day;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DayRepository extends CrudRepository<Day, Long> {

    @Override
    public List<Day> findAll();

}
