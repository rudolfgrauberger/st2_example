package com.example.factories;

import com.example.entities.Bestellung;
import com.example.entities.BestellPosition;
import com.example.entities.Gericht;

public class BestellungFactory {

   public static BestellPosition createBestellPosition(Bestellung bestellung, Gericht gericht, int menge) {
      BestellPosition bg = new BestellPosition(bestellung, gericht, menge);
      return bg;
   }
}
