package com.example.factories;

import com.example.entities.Bestellung;
import com.example.entities.BestellungGericht;
import com.example.entities.Gericht;

public class BestellungFactory {

   public static BestellungGericht createBestellPosition(Bestellung bestellung, Gericht gericht, int menge) {
      BestellungGericht bg = new BestellungGericht(bestellung, gericht, menge);
      return bg;
   }
}
