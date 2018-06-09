package com.example.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

public class SpeisekarteController {

    // BC2, BC5
    @GetMapping("/speisekarte")
    public String getSpeisekarte(Model model) {
        System.out.println("Get -> /speisekarte");
        model.addAttribute("id", 10);
        return "/test";
    }
    @PostMapping("/speisekarte")
    public String postSpeisekarte(Model model) {
        System.out.println("Post -> /speisekarte");
        model.addAttribute("id", 10);
        return "/test";
    }

    // BC3?
    @PostMapping("/speisekarte/{speisekarte}")
    public String postSpeisekarteWithObject(Model model) {
        System.out.println("Post -> /speisekarte/{speisekarte}");
        model.addAttribute("id", 10);
        return "/test";
    }

    // BC6
    @DeleteMapping("/speisekarte/{speisekarte}/{gericht}")
    public String deleteGerichtFromSpeisekarte(Model model) {
        System.out.println("Post -> /speisekarte/{speisekarte}/{gericht}");
        model.addAttribute("id", 10);
        return "/test";
    }


}
