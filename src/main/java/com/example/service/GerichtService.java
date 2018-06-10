package com.example.service;

import com.example.entities.Gericht;
import com.example.factories.GerichtFactory;
import com.example.repositories.GerichtRepository;
import com.example.repositories.SpeisekarteRepository;
import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("gerichtService")
public class GerichtService {
    @Autowired
    GerichtRepository gerichtRepository;
    @Autowired
    SpeisekarteRepository speisekarteRepository;

    public GerichtService() {

    }

    public Gericht createAndSaveGericht(String name, int preis) {
        Gericht gericht = GerichtFactory.createGericht(name, preis);
        gerichtRepository.save(gericht);
        return gericht;
    }

    public void saveGericht(Gericht gericht) {
        gerichtRepository.save(gericht);
    }

    public List<Gericht> getAllGerichte() {
        return Lists.newArrayList(gerichtRepository.findAll());
    }

    public void deleteGerichtFromSpeisekarte(String gericht, String speisekarte) {
        Gericht nGericht = gerichtRepository.findByName(gericht);
        nGericht.removeSpeisekarte
                (speisekarteRepository.findByName(speisekarte),false);
        gerichtRepository.save(nGericht);
    }
}
