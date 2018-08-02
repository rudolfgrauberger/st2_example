package com.example;

import com.example.Serializer.SpeisekarteSerializer;
import com.example.entities.Gericht;
import com.example.entities.Speisekarte;
import com.example.entities.Zubereitungsanleitung;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpeisekarteSerializer.class)
public class SpeisekarteSerializerTest {

    private final static String SERIALIZED_SPEISEKARTE = "{\n" +
            "                   \"name\": \"sommer\",\n" +
            "                   \"gueltig_von\": 1525125600000,\n" +
            "                   \"gueltig_bis\": 1538344800000,\n" +
            "                   \"gerichte\": []" +
            "                }";

    private final static String SERIALIZED_SPEISEKARTE_DOENER = "{\n" +
            "            \"name\": \"sommer\",\n" +
            "            \"gueltig_von\": 1525125600000,\n" +
            "            \"gueltig_bis\": 1538344800000,\n" +
            "            \"gerichte\": [\n" +
            "                {\n" +
            "                    \"id\": 0,\n" +
            "                    \"name\": \"Döner\",\n" +
            "                    \"preis\": 4.5,\n" +
            "                    \"anleitung\": {\n" +
            "                        \"anleitung\": \"Einfach machen\"\n" +
            "                    }\n" +
            "                 }\n" +
            "             ]\n" +
            "          }";

    @Autowired
    private StdSerializer serializer;

    @Test
    public void testSpeisekarteSerializerWithNameFromAndTo() throws JsonProcessingException {
        Speisekarte sommer = new Speisekarte("sommer", new GregorianCalendar(2018,Calendar.MAY,1).getTime(), new GregorianCalendar(2018,Calendar.OCTOBER,1).getTime());

        ObjectMapper mapper = new ObjectMapper();

        SimpleModule module = new SimpleModule();
        module.addSerializer(Speisekarte.class, serializer);
        mapper.registerModule(module);

        String serialized = mapper.writeValueAsString(sommer);

        assertThat(serialized).isEqualToIgnoringWhitespace(SERIALIZED_SPEISEKARTE.replace("\n", ""));
    }

    @Test
    public void testSpeisekarteSerializerWithNameFromToAndDoener() throws JsonProcessingException {
        Speisekarte sommer = new Speisekarte("sommer", new GregorianCalendar(2018,Calendar.MAY,1).getTime(), new GregorianCalendar(2018,Calendar.OCTOBER,1).getTime());
        Gericht gericht = new Gericht("Döner", 4.5, new Zubereitungsanleitung("Einfach machen"));
        sommer.addGericht(gericht);

        ObjectMapper mapper = new ObjectMapper();

        SimpleModule module = new SimpleModule();
        module.addSerializer(Speisekarte.class, serializer);
        mapper.registerModule(module);

        String serialized = mapper.writeValueAsString(sommer);

        assertThat(serialized).isEqualToIgnoringWhitespace(SERIALIZED_SPEISEKARTE_DOENER.replace("%n", ""));
    }
}
