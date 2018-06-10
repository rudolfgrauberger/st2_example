package com.example.service;

import com.example.JsonFormat.DateInput;
import com.example.entities.Bestellung;
import com.example.repositories.BestellungRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("bestellungService")
public class BestellungService {
    @Autowired
    BestellungRepository bestellungRepository;

    public BestellungService() {

    }

    public Bestellung findBestellungByOrdernummer(String ordernummer) {
        return bestellungRepository.findByOrdernummer(ordernummer);
    }

    public void saveBestellung(Bestellung bestellung) {
        bestellungRepository.save(bestellung);
    }

    public void deleteBestellung(String ordernummer) {
        Bestellung bestellung = bestellungRepository.findByOrdernummer(ordernummer);
        bestellungRepository.delete(bestellung);
    }

    public void changeDate(String ordernummer, DateInput datum) {
        Bestellung bestellung = bestellungRepository.findByOrdernummer(ordernummer);
        bestellung.setDatum(datum.getDatum());
        bestellungRepository.save(bestellung);
    }

    public List<Bestellung> greaterThanDatum(Date datum) {
        return bestellungRepository.findByDatumGreaterThan(datum);
    }
}
