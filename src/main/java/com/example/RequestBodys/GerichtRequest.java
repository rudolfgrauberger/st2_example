package com.example.RequestBodys;

import com.example.entities.Gericht;
import com.example.entities.Zubereitungsanleitung;
import com.example.repositories.GerichtRepository;
import com.example.service.GerichtService;
import org.springframework.beans.factory.annotation.Autowired;

public class GerichtRequest {

    private String name;
    private int preis;

    public GerichtRequest() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPreis() {
        return preis;
    }

    public void setPreis(int preis) {
        this.preis = preis;
    }

    public Gericht asGerichtEntity() {
        Gericht gericht = new Gericht(name, preis, new Zubereitungsanleitung("Test"));
        return gericht;
    }
}
