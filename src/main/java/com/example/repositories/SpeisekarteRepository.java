package com.example.repositories;

import com.example.entities.Speisekarte;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpeisekarteRepository extends CrudRepository<Speisekarte, Long> {
}
