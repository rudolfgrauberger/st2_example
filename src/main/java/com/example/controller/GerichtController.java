package com.example.controller;

import com.example.RequestBodys.GerichtRequest;
import com.example.RequestBodys.SpeisekarteRequest;
import com.example.Response.GerichtResponse;
import com.example.entities.Gericht;
import com.example.service.GerichtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@Controller
public class GerichtController {
    @Autowired
    GerichtService gerichtService;

    // BC1, BC4, BC7
    // OK
    @GetMapping("/gerichte")
    @ResponseBody // Objekte + URIs + Unterobjekte
    public List<GerichtResponse> getGericht() {
        System.out.println("Get -> /gerichte");
        List<Gericht> gerichte = gerichtService.getAllGerichte();

        List<GerichtResponse> response = new ArrayList<GerichtResponse>();

        HttpHeaders headers = new HttpHeaders();
        GerichtResponse gerichtResponse;
        URI location;

        for(Gericht gericht: gerichte)
        {
            location = ServletUriComponentsBuilder
                    .fromCurrentRequest().path("/{id}")
                    .buildAndExpand(gericht.getId()).toUri();

            gerichtResponse = new GerichtResponse(location, gericht);
            response.add(gerichtResponse);
        }

        return response;
    }
    // OK
    @PostMapping("/gerichte")
    @ResponseBody // 201 created + URI im Header Location + Objekt mit geänderten/Neuen Werten
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<Gericht> postGericht(@RequestBody GerichtRequest gerichtRequest) {
        System.out.println("POST -> /gerichte | Name: "+gerichtRequest.getName() +" Preis: "+gerichtRequest.getPreis());
        Gericht gericht = gerichtService.createAndSaveGericht(gerichtRequest.getName(), gerichtRequest.getPreis());

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(gericht.getId()).toUri();

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(location);

        ResponseEntity<Gericht> response = new ResponseEntity<Gericht>(gericht, headers, HttpStatus.CREATED);
        return response;
    }

    // BC3
    @PostMapping("/gerichte/{gericht}")
    public @ResponseBody GerichtResponse postGerichtWithObject(@RequestBody SpeisekarteRequest sR, @PathVariable int gericht) {
        System.out.println("Post -> /speisekarten/{speisekarte} | Added: " + sR.getName());
        gerichtService.addSpeisekarte(sR, gericht);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(gericht).toUri();

        return new GerichtResponse(location, gerichtService.getById(gericht));
    }
    // BC6
    @DeleteMapping("/gerichte/{gericht}/{speisekarte}")
    @ResponseBody
    public GerichtResponse deleteSpeisekarteFromGericht(@PathVariable int gericht, @PathVariable String speisekarte) {
        System.out.println("Delete -> /gerichte/{gericht}/{speisekarte}");
        gerichtService.deleteGerichtFromSpeisekarte(gericht, speisekarte);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().build().toUri();

        return new GerichtResponse(location, gerichtService.getById(gericht));
    }
}
