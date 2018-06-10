package com.example.service;

import com.example.RequestBodys.GerichtRequest;
import com.example.entities.Gericht;
import com.example.entities.Speisekarte;
import com.example.factories.GerichtFactory;
import com.example.factories.SpeisekarteFactory;
import com.example.repositories.GerichtRepository;
import com.example.repositories.SpeisekarteRepository;
import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("speisekarteService")
public class SpeisekarteService {
    @Autowired
    GerichtRepository gerichtRepository;
    @Autowired
    SpeisekarteRepository speisekarteRepository;

    public SpeisekarteService() {

    }

    public Speisekarte createAndSaveSpeisekarte(String name, Date datumVon, Date datumBis) {
        Speisekarte speisekarte = SpeisekarteFactory.createSpeisekarte(name, datumVon, datumBis);
        speisekarteRepository.save(speisekarte);
        return speisekarte;
    }

    public void saveSpeisekarte(Speisekarte speisekarte) {
        speisekarteRepository.save(speisekarte);
    }

    public List<Speisekarte> getAll() {
        return Lists.newArrayList(speisekarteRepository.findAll());
    }

    public void deleteGerichtFromSpeisekarte(String gericht, String speisekarte) {
        speisekarteRepository.findByName(speisekarte).removeGericht
                (gerichtRepository.findByName(gericht), false);
    }

    public void addGericht(GerichtRequest gericht, String speisekarte) {
        Gericht nGericht = gerichtRepository.findByName(gericht.getName());

        if(nGericht == null)
            speisekarteRepository.findByName(speisekarte).addGericht(gericht.asGerichtEntity());
        else
            speisekarteRepository.findByName(speisekarte).addGericht(nGericht);
    }

    public void addGericht(String gericht, String speisekarte) {
        speisekarteRepository.findByName(speisekarte).addGericht(gerichtRepository.findByName(gericht));
    }




}
