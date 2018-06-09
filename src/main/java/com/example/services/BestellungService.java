package com.example.services;

import com.example.entities.Bestellung;
import com.example.repositories.BestellungRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BestellungService {
    @Autowired
    BestellungRepository bestellungRepository;

    public Bestellung findBestellungByOrdernummer(String ordernummer) {
        return bestellungRepository.findByOrdernummer(ordernummer);
    }
}
