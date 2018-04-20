package com.example.entities;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Konto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    
    @ManyToOne(cascade = CascadeType.ALL)
	private Kunde kunde;
    
    private int kontonummer;
    
    private Date createDate;
    
	//needed for JPA
    protected Konto() {
	
    }
    
    public Konto(int kontonummer, Kunde kunde){
	this.kontonummer = kontonummer;
	this.kunde = kunde;
	this.createDate = new Date();
    }
    
    public int getKontonummer(){
    	return kontonummer;
    }
    
    public Date getCreateDate(){
    	return createDate;
    }
    
    public Kunde getKunde(){
    	return this.kunde;
    }
    
    /*
     * Needed to update the reference on the Many side
     */
    public void setKunde(Kunde kunde) {
        this.kunde = kunde;
        if (!kunde.getKonten().contains(this)) {
        	kunde.getKonten().add(this);
        }
    }
    
    public String toString(){
    	return "Konto: " + kontonummer;
    }
    
    
    
}