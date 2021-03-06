package com.example.entities;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@JsonAutoDetect
@Entity
public class Bestellung {
   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   private long id;

   // ToDo: fix nur die Ordernummer des letzten hinzugefügten steht in der DB, bei den anderen null
   private String ordernummer;
   private Date datum;

   @Enumerated(EnumType.STRING)
   private Bestellstatus bestellstatus;

   @OneToMany(mappedBy = "bestellung", fetch = FetchType.EAGER, orphanRemoval=true)
   private Set<BestellPosition> bestellPosition = new HashSet<BestellPosition>();

   public Bestellstatus getBestellstatus() {
      return bestellstatus;
   }

   public void setBestellstatus(Bestellstatus bestellstatus) {
      this.bestellstatus = bestellstatus;
   }

   public long getId() {
      return id;
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

   @JsonBackReference // um Endlosschleife zu verhindern
   public Set<BestellPosition> getBestellungGericht() {
      return Collections.unmodifiableSet(bestellPosition);
   }

   public void addPosition(BestellPosition position) {
      if (!bestellPosition.contains(position)) {
         bestellPosition.add(position);
      }
   }

   public Bestellung() {
      this.datum = new Date();
      this.bestellstatus = Bestellstatus.IN_BEARBEITUNG;
   }

   public Bestellung(Date datum) {
      this.datum = datum;
      this.bestellstatus = Bestellstatus.IN_BEARBEITUNG;
   }

   public String toString()
   {
      double summe = bestellPosition.stream().mapToDouble(x -> x.getGericht().getPreis() * x.getMenge()).sum();
      return String.format("ID: %s | Ordnernummer: %s |  Datum: %s | Gesamtsumme: %f", id, ordernummer, datum, summe);

   }
}
