package com.example.repositories;

import com.example.entities.Gericht;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GerichtRepository extends CrudRepository<Gericht, Long> {
    // Aufgabe 2: finder-Methoden
    Gericht findByName(String name);
    List<Gericht> findAllByName(String name);
    List<Gericht> findByPreisGreaterThan(double preis);
    List<Gericht> findByPreisLessThan(double preis);
    List<Gericht> findByPreisEquals(double preis);
}
