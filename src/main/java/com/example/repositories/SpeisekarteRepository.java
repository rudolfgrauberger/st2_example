package com.example.repositories;

import com.example.entities.Speisekarte;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpeisekarteRepository extends CrudRepository<Speisekarte, Long> {
    Speisekarte findByName(String name);
    List<Speisekarte> findAllByName(String name);
}
