package com.example.entities;

import javax.persistence.*;

@Entity
public class BestellungGericht {
   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   private long id;

   private int menge;

   @ManyToOne(cascade = CascadeType.ALL)
   private Bestellung bestellung;

   @ManyToOne(cascade = CascadeType.ALL)
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

   protected BestellungGericht() {

   }

   public BestellungGericht(Bestellung bestellung, Gericht gericht, int menge) {
      this.bestellung = bestellung;
      this.gericht = gericht;
      this.setMenge(menge);
   }
}
