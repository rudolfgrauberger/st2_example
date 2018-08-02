package com.example.repositories;

import com.example.entities.Bestellung;
import com.example.entities.Gericht;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface BestellungRepository extends CrudRepository<Bestellung, Long> {
    // ToDo: implementieren
    Bestellung findByOrdernummer(String ordernummer);
    List<Bestellung> findByDatumGreaterThan(Date datum);
}
