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

    public void addSpeisekarte(SpeisekarteRequest speisekarte, String gericht) {
        Speisekarte nSpeisekarte = speisekarteRepository.findByName(speisekarte.getName());

        // Hinweiß: findAllByName.get(0) weil wir manchmal mehrere Speisekarten mit dem selben Namen haben
        // ist nur als Übergangslösung so implementiert.
        if(nSpeisekarte == null)
            gerichtRepository.findAllByName(gericht).get(0).addSpeisekarte(speisekarte.asEntity());
        else
            gerichtRepository.findAllByName(gericht).get(0).addSpeisekarte(nSpeisekarte);
    }

    public Gericht getByName(String name) {
        return gerichtRepository.findByName(name);
    }
}
