package com.example.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Gericht {
   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   private long id;

   private String name;
   private double preis;

   @Embedded
   private Zubereitungsanleitung anleitung;

   @ManyToMany(mappedBy = "gerichte", fetch = FetchType.EAGER)
   private Set<Speisekarte> speisekarten = new HashSet<Speisekarte>();

   @OneToMany(mappedBy = "gericht")
   private Set<BestellPosition> bestellPosition = new HashSet<BestellPosition>();

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public double getPreis() {
      return preis;
   }

   public void setPreis(double preis) {
      this.preis = preis;
   }

   @JsonManagedReference // um Endlosschleife zu verhindern
   public Set<BestellPosition> getBestellPosition() {
      return Collections.unmodifiableSet(bestellPosition);
   }

   public Set<Speisekarte> getSpeisekarte() {
      // erzwingt Benutzung der addSpeisekarte- und removeSpeisekarte-Methoden
      return Collections.unmodifiableSet(speisekarten);
   }

   public void addSpeisekarte(Speisekarte karte) {
      // um Endlosschleife zu vermeiden
      if (!speisekarten.contains(karte)) {
         speisekarten.add(karte);
         // andere Seite der Beziehung updaten
         karte.addGericht(this);
      }
   }

   public void removeSpeisekarte(Speisekarte karte, boolean infinityBlocker) {
      speisekarten.remove(karte);
      // aktualisiert auch die andere Seite der Beziehung
      if(infinityBlocker) return;

      karte.removeGericht(this, true);
   }

   public void addInPosition(BestellPosition position) {
      bestellPosition.add(position);
   }

   protected Gericht() {

   }

   public Gericht(String name, double preis, Zubereitungsanleitung anleitung) {
      this.setName(name);
      this.setPreis(preis);
      this.anleitung = anleitung;
   }

   public String toString() {
      return String.format("ID: %s | Name: %s | Preis: %s | Zubereitungsanleitung: %s",
                              id, name, preis, anleitung);
   }
}
