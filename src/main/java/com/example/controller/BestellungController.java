package com.example.controller;

import com.example.JsonFormat.DateInput;
import com.example.entities.Bestellung;
import com.example.factories.BestellungFactory;
import com.example.service.BestellungService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
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
    @PostMapping("/bestellung")
    @ResponseBody
    @ResponseStatus(value = HttpStatus.CREATED)
        public Bestellung bestellung(@RequestBody DateInput datum) {
        System.out.println("Post -> /bestellung | Datum: "+datum.getDatum());
        Bestellung bestellung = new Bestellung(datum.getDatum());
        bestellungService.saveBestellung(bestellung);
        return bestellung;
    }
    // OK
    @GetMapping("/bestellung")
    @ResponseBody
    public List<Bestellung> getBestellungen() {
        System.out.println("Get -> /bestellung");
        return bestellungService.getAll();
    }

    // A3, A5, A6
    // OK
    @GetMapping("/bestellung/{ordernummer}")
    @ResponseBody
    public Bestellung getBestellungOrdernummer(@PathVariable String ordernummer) {
        Bestellung bestellung = bestellungService.findBestellungByOrdernummer(ordernummer);

        if(bestellung == null) {
            System.out.println("Get -> /bestellung | not found");
        } else {
            System.out.println("Get -> /bestellung/" + bestellung.getOrdernummer());
        }

        return bestellung;
    }


    @DeleteMapping("/bestellung/{ordernummer}")
    // OK
    public @ResponseBody String deleteBestellungOrdernummer(@PathVariable String ordernummer) {
        boolean deleted = bestellungService.deleteBestellung(ordernummer);
        System.out.println("Delete -> /bestellung/"+ordernummer +" | Deleted: "+ deleted);
        return "/bestellung";
    }

    // OK
    @PutMapping("/bestellung/{ordernummer}")
    public String putBestellungOrdernummer(@PathVariable String ordernummer, @RequestBody DateInput datum) {
        boolean changed = bestellungService.changeDate(ordernummer, datum);
        System.out.println("Put -> /bestellung/{ordernummer} | Changed: " +changed);
        // ToDo: Right Response
        return "/"+ordernummer;
    }

    // A2, A4
    @GetMapping(value = "/bestellung", params = { "operation", "datum" })
    @ResponseBody
    public List<Bestellung> getBestellungOrdernummerGreaterThanDate(@RequestParam("operation") String operation, @RequestParam("datum") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date date) {
        System.out.println("Get -> /bestellung?operation=greatherthan&datum={datum}");

        System.out.println("Operation: " + operation);
        System.out.println("Datum: " + date);

        List<Bestellung> bestellungList = bestellungService.greaterThanDatum(date);
        return bestellungList;
    }
}
