package com.example.controller;

import com.example.entities.Bestellung;
import com.example.factories.BestellungFactory;
import com.example.service.BestellungService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;




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
        public String bestellung() {
        System.out.println("Post -> /bestellung");
        Bestellung bestellung = new Bestellung();
        return "\\"+bestellung.getOrdernummer();
    }

    // A3, A5, A6
    @GetMapping("/bestellung/{ordernummer}")
    @ResponseBody
    public Bestellung getBestellungOrdernummer(@PathVariable String ordernummer) {
        Bestellung bestellung = bestellungService.findBestellungByOrdernummer(ordernummer);
        System.out.println("Get -> /bestellung/"+bestellung.getOrdernummer());
        return bestellung;
    }
    /*
    @GetMapping("/bestellung/{ordernummer}")
    public ModelAndView getBestellungOrdernummer(@PathVariable String ordernummer) {
        Bestellung bestellungBean = new Bestellung();
        bestellungBean.setOrdernummer(ordernummer);

        ModelAndView mav = new ModelAndView();
        mav.addObject("bestellungBean", bestellungBean);
        mav.setViewName("bestellungDetail");

        System.out.println("Get -> /bestellung/{ordernummer}");
        return mav;
    }*/
    @DeleteMapping("/bestellung/{ordernummer}")
    public String deleteBestellungOrdernummer(Model model) {
        System.out.println("Delete -> /bestellung/{ordernummer}");
        return "test";
    }
    @PutMapping("/bestellung/{ordernummer}")
    public String putBestellungOrdernummer(@PathVariable int ordernummer, @ModelAttribute Bestellung formBean) {
        // implement a save of all of the form bean information
        System.out.println("Put -> /bestellung/{ordernummer}");
        return "test";
    }

    // A2, A4
    @GetMapping("/bestellung?operation= greaterthan&datum={datum}")
    public String getBestellungOrdernummerGreaterThanDate(Model model) {
        System.out.println("Get -> /bestellung?operation= greaterthan&datum={datum}");
        return "test";
    }
}
