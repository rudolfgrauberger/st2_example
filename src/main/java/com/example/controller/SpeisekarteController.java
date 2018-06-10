package com.example.controller;

import com.example.JsonFormat.DateInput;
import com.example.RequestBodys.GerichtRequest;
import com.example.RequestBodys.SpeisekarteRequest;
import com.example.entities.Gericht;
import com.example.entities.Speisekarte;
import com.example.service.GerichtService;
import com.example.service.SpeisekarteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Controller
public class SpeisekarteController {
    @Autowired
    SpeisekarteService speisekarteService;

    // BC2, BC5
    // OK
    @GetMapping("/speisekarten")
    public @ResponseBody  List<Speisekarte> speisekarte() {
        System.out.println("Get -> /gericht");
        List<Speisekarte> speisekarten = speisekarteService.getAll();
        return speisekarten;
    }

    // OK
    @PostMapping("/speisekarten")
    @ResponseBody
    @ResponseStatus(value = HttpStatus.CREATED)
    public Speisekarte postSpeisekarte(@RequestBody SpeisekarteRequest sRequest) {
        System.out.println(String.format("POST -> /speisekarten | Name: %s DatumVon: %s DatumBis %s",
                                                sRequest.getName(),sRequest.getDatumVon(),sRequest.getDatumBis()));
        Speisekarte Speisekarte = speisekarteService.createAndSaveSpeisekarte(sRequest.getName(),
                                                                                sRequest.getDatumVon().getDatum(),
                                                                                sRequest.getDatumBis().getDatum());
        return Speisekarte;
    }

    // BC3?
    // OK
    @PostMapping("/speisekarten/{speisekarte}")
    public @ResponseBody String postSpeisekarteWithObject(@RequestBody GerichtRequest gericht, @PathVariable String speisekarte) {
        System.out.println("Post -> /speisekarten/{speisekarte} | Added: " + gericht.getName());
        speisekarteService.addGericht(gericht, speisekarte);
        return "/speisekarten";
    }

    // BC6
    // OK
    @DeleteMapping("/speisekarten/{speisekarte}/{gericht}")
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody String deleteGerichtFromSpeisekarte(@PathVariable String gericht, @PathVariable String speisekarte) {
        System.out.println("Delete -> /gerichte/{gericht}/{speisekarte}");
        speisekarteService.deleteGerichtFromSpeisekarte(gericht, speisekarte);
        // ToDo: Better return value
        return "/speisekarten";
    }


}
