package com.example.factories;

import org.springframework.stereotype.Component;

import com.example.entities.Konto;
import com.example.entities.Kunde;

@Component
public class KundeFactory {
	
    public KundeFactory() {}
    
	public Kunde createKunde( String name, String vorname ) {
		
		Kunde kunde1 = new Kunde(name, vorname);
		
		Konto konto1 = new Konto( 1294689, kunde1 );
		
		konto1.setKunde(kunde1); //Updating the JPA Reference
		
		
		return kunde1;
	}
}
