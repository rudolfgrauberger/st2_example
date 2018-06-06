package com.example.repositories;

import com.example.entities.Gericht;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface GerichtRepository extends CrudRepository<Gericht, Long> {
    // Aufgabe 2: finder-Methoden
    Gericht findByName(String name);
    List<Gericht> findByPreisGreaterThan(double preis);
    List<Gericht> findByPreisLessThan(double preis);
    List<Gericht> findByPreisEquals(double preis);
}
