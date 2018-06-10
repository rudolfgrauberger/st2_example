package com.example.service;

import com.example.entities.Bestellung;
import com.example.repositories.BestellungRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("bestellungService")
public class BestellungService {
    @Autowired
    BestellungRepository bestellungRepository;

    public BestellungService() {

    }

    public Bestellung findBestellungByOrdernummer(String ordernummer) {
        return bestellungRepository.findByOrdernummer(ordernummer);
    }
}
