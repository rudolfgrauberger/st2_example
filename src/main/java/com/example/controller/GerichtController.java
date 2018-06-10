package com.example.controller;

import com.example.RequestBodys.GerichtRequest;
import com.example.RequestBodys.SpeisekarteRequest;
import com.example.entities.Gericht;
import com.example.service.GerichtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class GerichtController {
    @Autowired
    GerichtService gerichtService;

    // BC1, BC4, BC7
    // OK
    @GetMapping("/gerichte")
    public @ResponseBody List<Gericht> getGericht() {
        System.out.println("Get -> /gerichte");
        List<Gericht> gerichte = gerichtService.getAllGerichte();
        return gerichte;
    }
    // OK
    @PostMapping("/gerichte")
    @ResponseBody
    @ResponseStatus(value = HttpStatus.CREATED)
    public Gericht postGericht(@RequestBody GerichtRequest gerichtRequest) {
        System.out.println("POST -> /gerichte | Name: "+gerichtRequest.getName() +" Preis: "+gerichtRequest.getPreis());
        Gericht gericht = gerichtService.createAndSaveGericht(gerichtRequest.getName(), gerichtRequest.getPreis());
        return gericht;
    }

    // BC3
    @PostMapping("/gerichte/{gericht}")
    public @ResponseBody String postGerichtWithObject(@RequestBody SpeisekarteRequest sR, @PathVariable String gericht) {
        System.out.println("Post -> /speisekarten/{speisekarte} | Added: " + sR.getName());
        gerichtService.addSpeisekarte(sR, gericht);
        return "/speisekarten";
    }
    // BC6
    // OK
    @DeleteMapping("/gerichte/{gericht}/{speisekarte}")
    public @ResponseBody String deleteSpeisekarteFromGericht(@PathVariable String gericht, @PathVariable String speisekarte) {
        System.out.println("Delete -> /gerichte/{gericht}/{speisekarte}");
        gerichtService.deleteGerichtFromSpeisekarte(gericht, speisekarte);
        // ToDo: Better return value
        return "/gerichte";
    }
}
