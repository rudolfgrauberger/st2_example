package com.example.controller;

import com.example.JsonFormat.DateInput;
import com.example.RequestBodys.GerichtRequest;
import com.example.RequestBodys.SpeisekarteRequest;
import com.example.Response.SpeisekarteResponse;
import com.example.entities.Gericht;
import com.example.entities.Speisekarte;
import com.example.service.GerichtService;
import com.example.service.SpeisekarteService;
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
import java.util.Date;
import java.util.List;

@Controller
public class SpeisekarteController {
    @Autowired
    SpeisekarteService speisekarteService;

    @Autowired
    GerichtService gerichtService;

    // BC2, BC5
    // OK
    @GetMapping("/speisekarten")
    @ResponseBody // Objekte + URIs + Unterobjekte
    public List<SpeisekarteResponse> speisekarte() {
        System.out.println("Get -> /speisekarte");
        List<Speisekarte> speisekarten = speisekarteService.getAll();

        List<SpeisekarteResponse> response = new ArrayList<SpeisekarteResponse>();

        HttpHeaders headers = new HttpHeaders();
        SpeisekarteResponse speisekarteResponse;
        URI location;

        for(Speisekarte speisekarte: speisekarten)
        {
            location = ServletUriComponentsBuilder
                    .fromCurrentRequest().path("/{id}")
                    .buildAndExpand(speisekarte.getName()).toUri();

            speisekarteResponse = new SpeisekarteResponse(location, speisekarte);
            response.add(speisekarteResponse);
        }
        return response;
    }

    // OK
    @PostMapping("/speisekarten")
    @ResponseBody // 201 created + URI im Header Location + Objekt mit geänderten/Neuen Werten
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<Speisekarte> postSpeisekarte(@RequestBody SpeisekarteRequest sRequest) {
        System.out.println(String.format("POST -> /speisekarten | Name: %s DatumVon: %s DatumBis %s",
                                                sRequest.getName(),sRequest.getDatumVon(),sRequest.getDatumBis()));
        Speisekarte speisekarte = speisekarteService.createAndSaveSpeisekarte(sRequest.getName(),
                                                                                sRequest.getDatumVon().getDatum(),
                                                                                sRequest.getDatumBis().getDatum());

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(speisekarte.getName()).toUri();

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(location);

        ResponseEntity<Speisekarte> response = new ResponseEntity<Speisekarte>(speisekarte, headers, HttpStatus.CREATED);
        return response;
    }

    // BC3
    // OK
    @PostMapping("/speisekarten/{speisekarte}")
    @ResponseBody // Objekt + URI + Unterobjekte
    public ResponseEntity<Speisekarte> postSpeisekarteAddGericht(@RequestBody GerichtRequest gericht, @PathVariable String speisekarte) {
        System.out.println("Post -> /speisekarten/{speisekarte} | Added: " + gericht.getName());
        speisekarteService.addGericht(gericht, speisekarte);

        Gericht g = gerichtService.getByName(gericht.getName());

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{gericht}")
                .buildAndExpand(g.getId()).toUri();

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(location);

        ResponseEntity<Speisekarte> response = new ResponseEntity<Speisekarte>(speisekarteService.getByName(speisekarte), headers, HttpStatus.CREATED);
        return response;
    }

    // BC6
    // OK
    @DeleteMapping("/speisekarten/{speisekarte}/{gericht}")
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody // location + Objekt
    public SpeisekarteResponse deleteGerichtFromSpeisekarte(@PathVariable int gericht, @PathVariable String speisekarte) {
        System.out.println("Delete -> /speisekarte/{speisekarte}/{gericht}");
        speisekarteService.deleteGerichtFromSpeisekarte(gericht, speisekarte);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().build().toUri();

        return new SpeisekarteResponse(location, speisekarteService.getByName(speisekarte));
    }


}
