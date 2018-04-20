package com.example.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Kunde {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    
    @OneToMany(mappedBy = "kunde", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Konto> konten = new HashSet<>();
    
    private String name;
    
    private String vorname;
    
	//needed for JPA
    protected Kunde() {
	
    }
    
    public Kunde(String name, String vorname){
    	this.setName(name);
    	this.setVorname(vorname);
    }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getVorname() {
		return vorname;
	}

	public void setVorname(String vorname) {
		this.vorname = vorname;
	}
	
	public void addKonto(Konto konto) {
        this.konten.add(konto);
        if (konto.getKunde() != this) {
        	konto.setKunde(this);
        }
    }
	
	public Set<Konto> getKonten(){
		return this.konten;
	}
	
	public String toString() {
    	String retVal = "Kunde: " + name + " " + vorname + "\n"; 
    	for (Konto konto : konten) {
    	    retVal += konto.toString();
    	}
    	return retVal;
    }

}
