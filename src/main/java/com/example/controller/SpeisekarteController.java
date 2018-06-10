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
    @ResponseBody // Objekt + location
    @ResponseStatus(value = HttpStatus.CREATED)
    public SpeisekarteResponse postSpeisekarte(@RequestBody SpeisekarteRequest sRequest) {
        System.out.println(String.format("POST -> /speisekarten | Name: %s DatumVon: %s DatumBis %s",
                                                sRequest.getName(),sRequest.getDatumVon(),sRequest.getDatumBis()));
        Speisekarte speisekarte = speisekarteService.createAndSaveSpeisekarte(sRequest.getName(),
                                                                                sRequest.getDatumVon().getDatum(),
                                                                                sRequest.getDatumBis().getDatum());

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(speisekarte.getName()).toUri();

        SpeisekarteResponse speisekarteResponse = new SpeisekarteResponse(location, speisekarte);
        return new SpeisekarteResponse(location, speisekarte);
    }

    // BC3?
    // OK
    @PostMapping("/speisekarten/{speisekarte}")
    @ResponseBody // Objekt + URI + Unterobjekte
    public SpeisekarteResponse postSpeisekarteAddGericht(@RequestBody GerichtRequest gericht, @PathVariable String speisekarte) {
        System.out.println("Post -> /speisekarten/{speisekarte} | Added: " + gericht.getName());
        speisekarteService.addGericht(gericht, speisekarte);

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/speisekarte/{id}")
                .buildAndExpand(speisekarte).toUri();

        return new SpeisekarteResponse(location, speisekarteService.getByName(speisekarte));
    }

    // BC6
    // OK
    @DeleteMapping("/speisekarten/{speisekarte}/{gericht}")
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody // location + Objekt
    public SpeisekarteResponse deleteGerichtFromSpeisekarte(@PathVariable String gericht, @PathVariable String speisekarte) {
        System.out.println("Delete -> /speisekarte/{speisekarte}/{gericht}");
        speisekarteService.deleteGerichtFromSpeisekarte(gericht, speisekarte);

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/speisekarte/{id}")
                .buildAndExpand(speisekarte).toUri();

        return new SpeisekarteResponse(location, speisekarteService.getByName(speisekarte));
    }


}
