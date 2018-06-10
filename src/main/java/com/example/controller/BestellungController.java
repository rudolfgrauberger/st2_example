package com.example.controller;

import com.example.JsonFormat.DateInput;
import com.example.entities.Bestellung;
import com.example.factories.BestellungFactory;
import com.example.service.BestellungService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;


@Controller
public class BestellungController {
    private BestellungFactory bestellungFactory = new BestellungFactory();

    @Autowired
    BestellungService bestellungService;

    /*
        Post {datum =  ...}
        Nur bei PUT {datum =  ...}
    */

    // A1
    @PostMapping("/bestellung")
    @ResponseBody
    @ResponseStatus(value = HttpStatus.CREATED)
        public Bestellung bestellung(@RequestBody DateInput datum) {
        System.out.println("Post -> /bestellung | Datum: "+datum.getDatum());
        Bestellung bestellung = new Bestellung(datum.getDatum());
        bestellungService.saveBestellung(bestellung);
        return bestellung;
    }

    // A3, A5, A6
    @GetMapping("/bestellung/{ordernummer}")
    @ResponseBody
    public Bestellung getBestellungOrdernummer(@PathVariable String ordernummer) {
        Bestellung bestellung = bestellungService.findBestellungByOrdernummer(ordernummer);
        System.out.println("Get -> /bestellung/"+bestellung.getOrdernummer());
        return bestellung;
    }
    @DeleteMapping("/bestellung/{ordernummer}")
    public String deleteBestellungOrdernummer(@PathVariable String ordernummer) {
        System.out.println("Delete -> /bestellung/"+ordernummer);
        bestellungService.deleteBestellung(ordernummer);
        // ToDo: Referenzen mit löschen und return passend zum Ergebnis (gelöscht ja/nein)
        return "/bestellung";
    }
    @PutMapping("/bestellung/{ordernummer}")
    public String putBestellungOrdernummer(@PathVariable String ordernummer, @RequestBody DateInput datum) {
        System.out.println("Put -> /bestellung/{ordernummer}");
        bestellungService.changeDate(ordernummer, datum);
        // ToDo: Right Response
        return "/"+ordernummer;
    }

    // A2, A4 // ToDo: Implement
    @RequestMapping(value="/bestellung?operation= greaterthan&datum={datum}", method = RequestMethod.GET)
    public List<Bestellung> getBestellungOrdernummerGreaterThanDate(@PathVariable Date datum) {
        System.out.println("Get -> /bestellung?operation= greaterthan&datum={datum}");
        List<Bestellung> bestellungList = bestellungService.greaterThanDatum(datum);
        return bestellungList;
    }
}
