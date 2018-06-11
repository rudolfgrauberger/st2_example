package com.example.service;

import com.example.JsonFormat.DateInput;
import com.example.entities.Bestellung;
import com.example.repositories.BestellungRepository;
import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("bestellungService")
public class BestellungService {
    @Autowired
    BestellungRepository bestellungRepository;

    public List<Bestellung> getAll() {
        return Lists.newArrayList(bestellungRepository.findAll());
    }

    public Bestellung findBestellungByOrdernummer(String ordernummer) {
        return bestellungRepository.findByOrdernummer(ordernummer);
    }

    public void saveBestellung(Bestellung bestellung) {
        bestellungRepository.save(bestellung);
    }

    public void deleteBestellung(String ordernummer) {
        Bestellung bestellung = bestellungRepository.findByOrdernummer(ordernummer);

        if(bestellung != null)
            bestellungRepository.delete(bestellung);
    }

    public boolean changeDate(String ordernummer, DateInput datum) {
        Bestellung bestellung = bestellungRepository.findByOrdernummer(ordernummer);

        if(bestellung != null) {
            bestellung.setDatum(datum.getDatum());
            bestellungRepository.save(bestellung);
            return true;
        } else {
            return false;
        }
    }

    public List<Bestellung> greaterThanDatum(Date datum) {
        return bestellungRepository.findByDatumGreaterThan(datum);
    }
}
