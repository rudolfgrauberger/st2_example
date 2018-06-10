package com.example.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;

@Entity
public class BestellPosition {
   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   private long id;

   private int menge;

   @ManyToOne
   @JsonManagedReference
   private Bestellung bestellung;

   @ManyToOne
   @JsonBackReference
   private Gericht gericht;

   public int getMenge() {
      return menge;
   }

   public void setMenge(int menge) {
      this.menge = menge;
   }

   public Bestellung getBestellung() {
      return bestellung;
   }

   public Gericht getGericht() {
      return gericht;
   }

   protected BestellPosition() {

   }

   public BestellPosition(Bestellung bestellung, Gericht gericht, int menge) {
      this.bestellung = bestellung;
      this.gericht = gericht;
      this.setMenge(menge);

      this.bestellung.addPosition(this);
      this.gericht.addInPosition(this);
   }

   @Override
   public String toString() {
      double price = getGericht().getPreis();
      int count = getMenge();
      return String.format("Position# ID: %s | Gericht: %s | Preis: %f | Menge: %d | Summe: %f",
            id, getGericht().getName(), price, count, price * count);
   }
}
