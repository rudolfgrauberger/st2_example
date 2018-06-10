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

    // A1
    // OK
    @PostMapping("/bestellungen")
    @ResponseBody
    @ResponseStatus(value = HttpStatus.CREATED)
        public Bestellung bestellung(@RequestBody DateInput datum) {
        System.out.println("Post -> /bestellungen | Datum: "+datum.getDatum());
        Bestellung bestellung = new Bestellung(datum.getDatum());
        bestellungService.saveBestellung(bestellung);
        return bestellung;
    }
    // OK
    @GetMapping("/bestellungen")
    @ResponseBody
    public List<Bestellung> getBestellungen() {
        System.out.println("Get -> /bestellungen");
        return bestellungService.getAll();
    }

    // A3, A5, A6
    // OK
    @GetMapping("/bestellungen/{ordernummer}")
    @ResponseBody
    public Bestellung getBestellungOrdernummer(@PathVariable String ordernummer) {
        Bestellung bestellung = bestellungService.findBestellungByOrdernummer(ordernummer);

        if(bestellung == null) {
            System.out.println("Get -> /bestellungen | not found");
        } else {
            System.out.println("Get -> /bestellungen/" + bestellung.getOrdernummer());
        }

        return bestellung;
    }


    @DeleteMapping("/bestellungen/{ordernummer}")
    // OK
    public @ResponseBody String deleteBestellungOrdernummer(@PathVariable String ordernummer) {
        boolean deleted = bestellungService.deleteBestellung(ordernummer);
        System.out.println("Delete -> /bestellungen/"+ordernummer +" | Deleted: "+ deleted);
        return "/bestellungen";
    }

    // OK
    @PutMapping("/bestellungen/{ordernummer}")
    public String putBestellungOrdernummer(@PathVariable String ordernummer, @RequestBody DateInput datum) {
        boolean changed = bestellungService.changeDate(ordernummer, datum);
        System.out.println("Put -> /bestellungen/{ordernummer} | Changed: " +changed);
        // ToDo: Right Response
        return "/"+ordernummer;
    }

    // A2, A4 // ToDo: wird nicht aufgerufen, statt dessen /bestellung
    @RequestMapping(value="/bestellungen?operation=greaterthan&datum={datum}", method = RequestMethod.GET)
    public List<Bestellung> getBestellungOrdernummerGreaterThanDate(@PathVariable Date datum) {
        System.out.println("Get -> /bestellungen?operation= greaterthan&datum={datum}");
        List<Bestellung> bestellungList = bestellungService.greaterThanDatum(datum);
        return bestellungList;
    }
}
