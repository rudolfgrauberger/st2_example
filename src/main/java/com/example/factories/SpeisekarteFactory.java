package com.example.factories;

import com.example.entities.Gericht;
import com.example.entities.Speisekarte;

import java.util.Date;
import java.util.Set;

public class SpeisekarteFactory {
   public static Speisekarte createSpeisekarte(Date von, Date bis) {
      return new Speisekarte(von, bis);
   }

   public static Speisekarte createSpeisekarte(Date von, Date bis, Set<Gericht> gerichte) {
     Speisekarte speisekarte = new Speisekarte(von, bis);
     gerichte.forEach(x -> speisekarte.addGericht(x));
     return speisekarte;
   }
    public void addGericht(Speisekarte speisekarte, Gericht gericht) {
        speisekarte.addGericht(gericht);
        System.out.println("Added Gericht to Speisekarte");
    }
}
