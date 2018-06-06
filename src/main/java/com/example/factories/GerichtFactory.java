package com.example.factories;

import com.example.entities.Bestellung;
import com.example.entities.Gericht;
import com.example.entities.Speisekarte;
import com.example.entities.Zubereitungsanleitung;

import java.util.HashSet;
import java.util.Set;

public class GerichtFactory {
   public static Gericht createGericht(String name, double preis) {
      return new Gericht(name, preis, null);
   }

   public static Gericht createGericht(String name, double preis, Zubereitungsanleitung anleitung) {
      return new Gericht(name, preis, anleitung);
   }

   public static Gericht createGericht(String name, double preis, String anleitung) {
      return createGericht(name, preis, new Zubereitungsanleitung(anleitung));
   }

   public static Gericht createGericht(String name, double preis, Set<Speisekarte> speisekarten) {
      Gericht gericht = new Gericht(name, preis, null);
      speisekarten.forEach(karte -> gericht.addSpeisekarte(karte));
      return gericht;
   }

   public static Gericht createGericht(String name, double preis, Speisekarte speisekarte) {
      Set<Speisekarte> sk = new HashSet<Speisekarte>();
      sk.add(speisekarte);
      return createGericht(name, preis, sk);
   }

   public static Gericht createGericht(String name, double preis, Zubereitungsanleitung anleitung, Set<Speisekarte> speisekarten) {
      Gericht gericht = new Gericht(name, preis, anleitung);
      speisekarten.forEach(karte -> gericht.addSpeisekarte(karte));
      return gericht;
   }

   public static Gericht createGericht(String name, double preis, String anleitung, Set<Speisekarte> speisekarten) {
      return createGericht(name, preis, new Zubereitungsanleitung(anleitung), speisekarten);
   }

   public static Gericht createGericht(String name, double preis, String anleitung, Speisekarte speisekarte) {
      Set<Speisekarte> sk = new HashSet<Speisekarte>();
      sk.add(speisekarte);
      return createGericht(name, preis, anleitung, sk);
   }
}
