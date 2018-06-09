package com.example.repositories;

import com.example.entities.BestellPosition;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BestellPositionRepository extends CrudRepository<BestellPosition, Long> {
   // ToDo: implementieren
}
