package com.example.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

public class GerichtController {
    // BC1, BC4, BC7
    @GetMapping("/gericht")
    public String getGericht(Model model) {
        System.out.println("Get -> /gericht");
        model.addAttribute("id", 10);
        return "/test";
    }
    @PostMapping("/gericht")
    public String postGericht(Model model) {
        System.out.println("Get -> /gericht");
        model.addAttribute("id", 10);
        return "/test";
    }
    // BC6
    @DeleteMapping("/gericht/{gericht}/{speisekarte}")
    public String deleteSpeisekarteFromGericht(Model model) {
        System.out.println("Delete -> /gericht/{gericht}/{speisekarte}");
        model.addAttribute("id", 10);
        return "/test";
    }

    // Nur bei POST {name = …}
}
