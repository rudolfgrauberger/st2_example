package com.example.controller;

import com.example.JsonFormat.DateInput;
import com.example.Response.BestellungResponse;
import com.example.entities.Bestellung;
import com.example.factories.BestellungFactory;
import com.example.service.BestellungService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Controller("bestellungController")
public class BestellungController {
    private BestellungFactory bestellungFactory = new BestellungFactory();

    @Autowired
    BestellungService bestellungService;

    // A1
    @PostMapping("/bestellungen")
    @ResponseBody // 201 created + URI im Header Location + Objekt mit geänderten/Neuen Werten
    @ResponseStatus(value = HttpStatus.CREATED)
        public ResponseEntity<Bestellung> bestellung(@RequestBody DateInput datum) {
        System.out.println("Post -> /bestellungen | Datum: "+datum.getDatum());
        Bestellung bestellung = new Bestellung(datum.getDatum());
        bestellungService.saveBestellung(bestellung);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(bestellung.getId()).toUri();

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(location);

        ResponseEntity<Bestellung> response = new ResponseEntity<Bestellung>(bestellung, headers, HttpStatus.CREATED);
        return response;
    }
    // OK
    @GetMapping("/bestellungen")
    @ResponseBody // Alle Objekte + URIs
    public List<BestellungResponse> getBestellungen() {
        System.out.println("Get -> /bestellungen");

        List<Bestellung> bestellungen = bestellungService.getAll();
        List<BestellungResponse> response = new ArrayList<BestellungResponse>();

        HttpHeaders headers = new HttpHeaders();
        BestellungResponse bestellungResponse;
        URI location;

        for(Bestellung bestellung: bestellungen)
        {
            location = ServletUriComponentsBuilder
                    .fromCurrentRequest().path("/{id}")
                    .buildAndExpand(bestellung.getId()).toUri();

            bestellungResponse = new BestellungResponse(location, bestellung);
            response.add(bestellungResponse);
        }
        return response;
    }

    // A3, A5, A6
    // OK
    @GetMapping("/bestellungen/{ordernummer}")
    @ResponseBody // Objekt + URI im Header als Location
    public ResponseEntity<Bestellung> getBestellungOrdernummer(@PathVariable String ordernummer) {
        Bestellung bestellung = bestellungService.findBestellungByOrdernummer(ordernummer);
        ResponseEntity<Bestellung> response;

        if(bestellung == null) {
            System.out.println("Get -> /bestellungen | not found");
            response = new ResponseEntity<Bestellung>(bestellung, HttpStatus.NOT_FOUND);
        } else {
            System.out.println("Get -> /bestellungen/" + bestellung.getOrdernummer());

            URI location = ServletUriComponentsBuilder.fromCurrentContextPath().path("/bestellungen/{id}")
                    .buildAndExpand(bestellung.getId()).toUri();

            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(location);
            response = new ResponseEntity<Bestellung>(bestellung, headers, HttpStatus.OK);
        }
        return response;
    }

    // ToDo: Fix Referentielle Integrität -.-
    @DeleteMapping("/bestellungen/{ordernummer}")
    @ResponseBody
    public ResponseEntity<Bestellung> deleteBestellungOrdernummer(@PathVariable String ordernummer) {
        boolean deleted = bestellungService.deleteBestellung(ordernummer);
        System.out.println("Delete -> /bestellungen/"+ordernummer +" | Deleted: "+ deleted);
        if(!deleted) return new ResponseEntity<Bestellung>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<Bestellung>(HttpStatus.OK);
    }

    // OK
    @PutMapping("/bestellungen/{ordernummer}")
    @ResponseBody // // Objekt + URI im Header als Location
    public ResponseEntity<Bestellung> putBestellungOrdernummer(@PathVariable String ordernummer, @RequestBody DateInput datum) {
        boolean changed = bestellungService.changeDate(ordernummer, datum);
        System.out.println("Put -> /bestellungen/{ordernummer} | Changed: " +changed);

        if(!changed) return new ResponseEntity<Bestellung>(HttpStatus.NOT_FOUND);

        Bestellung bestellung = bestellungService.findBestellungByOrdernummer(ordernummer);
        URI location = ServletUriComponentsBuilder.fromCurrentContextPath().path("/bestellungen/{id}")
                .buildAndExpand(bestellung.getId()).toUri();
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(location);

        return new ResponseEntity<Bestellung>(bestellung, headers, HttpStatus.OK);
    }

    // A2, A4
    @GetMapping(value = "/bestellungen", params = { "filter[date_greaterthan]" })
    @ResponseBody
    public List<BestellungResponse> getBestellungOrdernummerGreaterThanDate(@RequestParam("filter[date_greaterthan]") DateInput date) {
        System.out.println("Get -> /bestellung?filter[date_greaterthan]={datum}");
        System.out.println("Date: " + date);
        List<Bestellung> bestellungen = bestellungService.greaterThanDatum(date.getDatum());

        List<BestellungResponse> response = new ArrayList<BestellungResponse>();

        HttpHeaders headers = new HttpHeaders();
        BestellungResponse bestellungResponse;
        URI location;

        for(Bestellung bestellung: bestellungen)
        {
            location = ServletUriComponentsBuilder
                    .fromCurrentContextPath().path("/bestellungen/{id}")
                    .buildAndExpand(bestellung.getId()).toUri();

            bestellungResponse = new BestellungResponse(location, bestellung);
            response.add(bestellungResponse);
        }
        return response;
    }
}
