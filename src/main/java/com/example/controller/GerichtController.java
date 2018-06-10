package com.example.controller;

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
    @GetMapping("/gericht")
    public List<Gericht> getGericht() {
        System.out.println("Get -> /gericht");
        List<Gericht> gerichte = gerichtService.getAllGerichte();
        // ToDo: fix Circular view path [gericht]: would dispatch back to the current handler URL [/gericht] again.
        return gerichte;
    }
    @PostMapping("/gericht")
    @ResponseBody
    @ResponseStatus(value = HttpStatus.CREATED)
    public Gericht postGericht(@RequestBody String name) {
        System.out.println("POST -> /gericht | Name: "+name +" Preis: "+0);
        Gericht gericht = gerichtService.createAndSaveGericht(name, 0);
        // ToDo: implement request body with preis
        return gericht;
    }
    // BC6
    @DeleteMapping("/gericht/{gericht}/{speisekarte}")
    public String deleteSpeisekarteFromGericht(@PathVariable String gericht, @PathVariable String speisekarte) {
        System.out.println("Delete -> /gericht/{gericht}/{speisekarte}");
        gerichtService.deleteGerichtFromSpeisekarte(gericht, speisekarte);
        // ToDo: Better return value
        // ToDo: Fix Circulation
        return "/gericht";
    }

    // Nur bei POST {name = …}
}
