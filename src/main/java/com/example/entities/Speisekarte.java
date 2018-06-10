package com.example.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Speisekarte {

   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   private long id;

   private String name;
   private Date gueltig_von;
   private Date guiltig_bis;

   @ManyToMany(fetch = FetchType.EAGER)
   private Set<Gericht> gerichte = new HashSet<Gericht>();

   public Date getGueltig_von() {
      return gueltig_von;
   }

   public void setGueltig_von(Date gueltig_von) {
      this.gueltig_von = gueltig_von;
   }

   public Date getGuiltig_bis() {
      return guiltig_bis;
   }

   public void setGuiltig_bis(Date guiltig_bis) {
      this.guiltig_bis = guiltig_bis;
   }

   @JsonManagedReference
   public Set<Gericht> getGerichte() {
      // erzwingt Benutzung der addGericht- und removeGericht-Methoden
      return Collections.unmodifiableSet(gerichte);
   }

   public void addGericht(Gericht gericht) {
      // um Endlosschleife zu vermeiden
      if (!gerichte.contains(gericht)) {
         gerichte.add(gericht);
         // andere Seite der Beziehung aktualisieren
         gericht.addSpeisekarte(this);
      }
   }

   public void removeGericht(Gericht gericht, boolean infinityBlocker) {
      gerichte.remove(gericht);

      if(infinityBlocker) return;

      gericht.removeSpeisekarte(this, true);
   }

   protected Speisekarte() {

   }

   public Speisekarte(String name, Date von, Date bis) {
      this.setGueltig_von(von);
      this.setGuiltig_bis(bis);
      this.name = name;
   }

   @Override
   public String toString() {
      return String.format("Name: %s | ID: %s | Von: %s | Bis: %s",
            name, id, gueltig_von, guiltig_bis);
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }
}
