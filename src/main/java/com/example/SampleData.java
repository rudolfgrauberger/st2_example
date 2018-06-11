package com.example;

import com.example.entities.Bestellung;
import com.example.entities.BestellPosition;
import com.example.entities.Gericht;
import com.example.entities.Speisekarte;
import com.example.factories.BestellungFactory;
import com.example.factories.GerichtFactory;
import com.example.factories.SpeisekarteFactory;
import com.example.repositories.BestellPositionRepository;
import com.example.repositories.BestellungRepository;
import com.example.repositories.GerichtRepository;
import com.example.repositories.SpeisekarteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

@Component
public class SampleData implements ApplicationListener<ContextRefreshedEvent> {
    @Autowired // Wird von Spring per Dependency Injection automatisch instanziiert
    private SpeisekarteRepository speisekarteRepository;

    @Autowired
    private GerichtRepository gerichtRepository;

    @Autowired
    private BestellungRepository bestellungRepository;

    @Autowired
    private BestellPositionRepository bestellPositionRepository;
    
    @Override
    public void onApplicationEvent(ContextRefreshedEvent arg0) {

       /*Speisekarte vegetarisch = SpeisekarteFactory.createSpeisekarte("vegetarisch",
                                    new GregorianCalendar(2011,Calendar.JANUARY,1).getTime(),
                                    new GregorianCalendar(9999,Calendar.DECEMBER,31).getTime());
       Speisekarte normal = SpeisekarteFactory.createSpeisekarte("normal",
                                    new GregorianCalendar(2011,Calendar.JANUARY,1).getTime(),
                                    new GregorianCalendar(9999,Calendar.DECEMBER,31).getTime());

       Set<Speisekarte> alleSpeisekarten = new HashSet<Speisekarte>();
       alleSpeisekarten.add(vegetarisch);
       alleSpeisekarten.add(normal);

       Gericht gekochtesGemuese = GerichtFactory.createGericht("Gekochtes Gemüse", 8.99, alleSpeisekarten);
       Gericht paniertesSellerieGruenkohl = GerichtFactory.createGericht("Panierter Sellerie gebettet auf Grünkohl", 16.1, "Sehr heiß abkochen", vegetarisch);
       Gericht steakNuessen = GerichtFactory.createGericht("Steak mit Nüssen", 23.99, normal);

       // Bei ManyToMany, muss die Seite die mappedBy hat (also nicht verantwortlich ist), als erstes gespeichert
       // werden, denn hierbei werden die Referenzen noch nicht mitgeschrieben. Erst die Seite bei der das ManyToMany
       // steht, werden beim Speichern tatsächlich die Referenzen geschrieben.
       gerichtRepository.save(gekochtesGemuese);
       gerichtRepository.save(paniertesSellerieGruenkohl);
       gerichtRepository.save(steakNuessen);

       speisekarteRepository.save(vegetarisch);
       speisekarteRepository.save(normal);


       // Ohne die nachfolgende Zeile wird die neue Referenz in der Zwischentabelle nicht gespeichert
       // Das sehe ich aber als großes Problem, da beim Anlegen, immer manuell prüfen muss welche
       // Objekte intern ggf. geändert werden und alle diese Objekte manuell auch speichern muss.
       // Oder mache ich hier etwas anderes grundsätzlich falsch?????
       // gerichtRepository.save(GerichtFactory.createGericht("Blabla", 1.99, normal));
       //speisekarteRepository.save(normal);

        LocalDate datum1 = LocalDate.now().minusYears(2);
        Bestellung bestellung1 = new Bestellung();
        bestellung1.setOrdernummer("O1010");
        bestellung1.setDatum(Date.from(datum1.atStartOfDay(ZoneId.systemDefault()).toInstant()));

        Bestellung bestellung2 = new Bestellung();
        bestellung2.setOrdernummer("Test2");

        Bestellung bestellung3 = new Bestellung();
        bestellung3.setOrdernummer("Test3");

        BestellPosition position1 = BestellungFactory.createBestellPosition(bestellung1, gekochtesGemuese, 1);
        BestellPosition position2 = BestellungFactory.createBestellPosition(bestellung1, steakNuessen, 2);

        bestellungRepository.save(bestellung1);
        bestellungRepository.save(bestellung2);
        bestellungRepository.save(bestellung3);

        bestellPositionRepository.save(position1);
        bestellPositionRepository.save(position2);*/

       // find-Methode
       //testFinds();

       //printAll();
    }

   private void printAll() {
      System.out.println("Alle Bestellungen --- ");
      for(Bestellung b: bestellungRepository.findAll()) {
         System.out.println(b);

         b.getBestellungGericht().forEach(x -> System.out.println(x));
      }

      System.out.println("Alle Gerichte --- ");
      for(Gericht g: gerichtRepository.findAll()) {
         System.out.println(g);
      }

      System.out.println("Alle Speisekarten --- ");
      for(Speisekarte s: speisekarteRepository.findAll()) {
         System.out.println(s);
      }
   }

   private void testFinds() {
      Gericht gericht = gerichtRepository.findByName("Steak mit Nüssen");
      System.out.println("Gericht gefunden: " + gericht.getName());

      for(Gericht g: gerichtRepository.findByPreisGreaterThan(0)) {
         System.out.println("Folgende Gerichte die nicht kostenlos sind gefunden: " + g.getName());
      }

      gericht.getSpeisekarte().forEach(speisekarte -> {
         System.out.println("Folgende Gerichte beinhaltet die Speisekarte mit der Gültigkeit von " + speisekarte.getGueltig_von() + " bis " + speisekarte.getGuiltig_bis() + ":");
         speisekarte.getGerichte().forEach(x -> System.out.println(x));
      });
   }
    

    public SampleData() {
	
    }
    

}
