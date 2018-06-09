package com.example.repositories;

import com.example.entities.Bestellung;
import com.example.entities.Gericht;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BestellungRepository extends CrudRepository<Bestellung, Long> {
    // ToDo: implementieren
    Bestellung findByOrdernummer(String ordernummer);
}
