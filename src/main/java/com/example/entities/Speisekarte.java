package com.example.entities;

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

   private Date gueltig_von;
   private Date guiltig_bis;

   @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
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

   public void removeGericht(Gericht gericht) {
      gerichte.remove(gericht);
      gericht.removeSpeisekarte(this);
   }

   protected Speisekarte() {

   }

   public Speisekarte(Date von, Date bis) {
      this.setGueltig_von(von);
      this.setGuiltig_bis(bis);
   }
}