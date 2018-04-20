package com.example;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class SampleData implements ApplicationListener<ContextRefreshedEvent> {  

    
    @Override
    public void onApplicationEvent(ContextRefreshedEvent arg0) {

       // TODO: Hier kommt der Code der beim Anwendungsstart ausgeführt werden soll
    }
    

    public SampleData() {
	
    }
    

}
