package com.example.Response;

import com.example.entities.Bestellung;
import com.example.repositories.BestellungRepository;

import java.net.URI;

public class BestellungResponse {
    private URI location;
    private Bestellung bestellung;

    public BestellungResponse(URI location, Bestellung bestellung) {
        this.location = location;
        this.bestellung = bestellung;
    }

    public URI getLocation() {
        return location;
    }

    public void setLocation(URI location) {
        this.location = location;
    }

    public Bestellung getBestellung() {
        return bestellung;
    }

    public void setBestellung(Bestellung bestellung) {
        this.bestellung = bestellung;
    }
}
