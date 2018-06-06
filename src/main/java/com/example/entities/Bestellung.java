package com.example.entities;

import javax.persistence.*;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Bestellung {
   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   private long id;

   private String ordernummer;
   private Date datum;

   @Enumerated(EnumType.STRING)
   private Bestellstatus bestellstatus;

   @OneToMany(mappedBy = "bestellung", fetch = FetchType.EAGER)
   private Set<BestellungGericht> bestellungGericht = new HashSet<BestellungGericht>();

   public Bestellstatus getBestellstatus() {
      return bestellstatus;
   }

   public void setBestellstatus(Bestellstatus bestellstatus) {
      this.bestellstatus = bestellstatus;
   }

   public String getOrdernummer() {
      return ordernummer;
   }

   public void setOrdernummer(String ordernummer) {
      this.ordernummer = ordernummer;
   }

   public Date getDatum() {
      return datum;
   }

   public void setDatum(Date datum) {
      this.datum = datum;
   }

   public Set<BestellungGericht> getBestellungGericht() {
      return Collections.unmodifiableSet(bestellungGericht);
   }

   public void addPosition(BestellungGericht position) {
      if (!bestellungGericht.contains(position)) {
         bestellungGericht.add(position);
      }
   }

   public Bestellung() {
      this.datum = new Date();
      this.bestellstatus = Bestellstatus.IN_BEARBEITUNG;
   }

   public String toString()
   {
      double summe = bestellungGericht.stream().mapToDouble(x -> x.getGericht().getPreis() * x.getMenge()).sum();
      return String.format("ID: %s | Ordnernummer: %s |  Datum: %s | Gesamtsumme: %f", id, ordernummer, datum, summe);

   }
}
