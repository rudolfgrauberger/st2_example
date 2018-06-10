package com.example.Response;

import com.example.entities.Gericht;
import com.example.entities.Speisekarte;

import java.net.URI;

public class SpeisekarteResponse {
    private URI location;
    private Speisekarte speisekarte;

    public SpeisekarteResponse(URI location, Speisekarte speisekarte) {
        this.location = location;
        this.speisekarte = speisekarte;
    }

    public URI getLocation() {
        return location;
    }

    public void setLocation(URI location) {
        this.location = location;
    }

    public Speisekarte getSpeisekarte() {
        return speisekarte;
    }

    public void setSpeisekarte(Gericht gericht) {
        this.speisekarte = speisekarte;
    }
}
