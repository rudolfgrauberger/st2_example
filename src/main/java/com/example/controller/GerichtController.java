package com.example.controller;

import com.example.RequestBodys.GerichtRequest;
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
    public @ResponseBody List<Gericht> getGericht() {
        System.out.println("Get -> /gericht");
        List<Gericht> gerichte = gerichtService.getAllGerichte();
        // ToDo: fix Circular view path [gericht]: would dispatch back to the current handler URL [/gericht] again.
        return gerichte;
    }
    // OK
    @PostMapping("/gericht")
    @ResponseBody
    @ResponseStatus(value = HttpStatus.CREATED)
    public Gericht postGericht(@RequestBody GerichtRequest gerichtRequest) {
        System.out.println("POST -> /gericht | Name: "+gerichtRequest.getName() +" Preis: "+gerichtRequest.getPreis());
        Gericht gericht = gerichtService.createAndSaveGericht(gerichtRequest.getName(), gerichtRequest.getPreis());
        return gericht;
    }
    // BC6
    // OK
    @DeleteMapping("/gericht/{gericht}/{speisekarte}")
    public @ResponseBody String deleteSpeisekarteFromGericht(@PathVariable String gericht, @PathVariable String speisekarte) {
        System.out.println("Delete -> /gericht/{gericht}/{speisekarte}");
        gerichtService.deleteGerichtFromSpeisekarte(gericht, speisekarte);
        // ToDo: Better return value
        return "/gericht";
    }
}
