package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.example.factories.KundeFactory;
import com.example.repositories.KundeRepository;

@Component
public class SampleData implements ApplicationListener<ContextRefreshedEvent> {  
    
    @Autowired
    private KundeRepository kundeRepository; 
    
    @Override
    public void onApplicationEvent(ContextRefreshedEvent arg0) {

    	kundeRepository.save(new KundeFactory().createKunde("Mustermann", "Max"));
    	System.out.println(kundeRepository.findByName("Mustermann").toString());
    }
    

    public SampleData() {
	
    }
    

}
