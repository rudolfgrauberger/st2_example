package com.example.factories;

import com.example.entities.Gericht;
import com.example.entities.Speisekarte;

import java.util.Date;
import java.util.Set;

public class SpeisekarteFactory {
   public static Speisekarte createSpeisekarte(String name, Date von, Date bis) {
      return new Speisekarte(name, von, bis);
   }

   public static Speisekarte createSpeisekarte(String name, Date von, Date bis, Set<Gericht> gerichte) {
     Speisekarte speisekarte = new Speisekarte(name, von, bis);
     gerichte.forEach(x -> speisekarte.addGericht(x));
     return speisekarte;
   }
    public void addGericht(Speisekarte speisekarte, Gericht gericht) {
        speisekarte.addGericht(gericht);
        System.out.println("Added Gericht to Speisekarte");
    }
}
