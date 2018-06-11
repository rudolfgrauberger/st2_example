package com.example.service;

import com.example.RequestBodys.GerichtRequest;
import com.example.RequestBodys.SpeisekarteRequest;
import com.example.entities.Gericht;
import com.example.entities.Speisekarte;
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

    public Gericht createAndSaveGericht(String name, double preis) {
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

    public void deleteGerichtFromSpeisekarte(int gerichtId, String speisekarte) {
        Gericht nGericht = gerichtRepository.findById(gerichtId);
        Speisekarte nSpeisekarte = speisekarteRepository.findByName(speisekarte);
        nGericht.removeSpeisekarte(nSpeisekarte,false);

        gerichtRepository.save(nGericht);
    }

    public void addSpeisekarte(SpeisekarteRequest speisekarte, int gerichtId) {
        Speisekarte nSpeisekarte = speisekarteRepository.findByName(speisekarte.getName());

        Gericht lGerich = gerichtRepository.findById(gerichtId);

        if(nSpeisekarte == null)
            lGerich.addSpeisekarte(speisekarte.asEntity());
        else
            lGerich.addSpeisekarte(nSpeisekarte);

        gerichtRepository.save(lGerich);
    }

    public Gericht getByName(String name) {
        return gerichtRepository.findByName(name);
    }

    public Gericht getById(int id) {
        return gerichtRepository.findById(id);
    }
}
