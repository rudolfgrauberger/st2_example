package com.example.Response;

import com.example.entities.Bestellung;
import com.example.entities.Gericht;

import java.net.URI;

public class GerichtResponse {
    private URI location;
    private Gericht gericht;

    public GerichtResponse(URI location, Gericht gericht) {
        this.location = location;
        this.gericht = gericht;
    }

    public URI getLocation() {
        return location;
    }

    public void setLocation(URI location) {
        this.location = location;
    }

    public Gericht getGericht() {
        return gericht;
    }

    public void setGericht(Gericht gericht) {
        this.gericht = gericht;
    }
}
