package com.example;

import com.example.Serializer.GerichtSerializer;
import com.example.entities.Gericht;
import com.example.entities.Speisekarte;
import com.example.entities.Zubereitungsanleitung;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationConfig;
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
@SpringBootTest(classes = GerichtSerializer.class)
public class GerichtSerializerTest {

    private final static String SERIALIZED_DOENER = " {\n" +
            "            \"id\": 0,\n" +
            "            \"name\": \"Döner\",\n" +
            "            \"preis\": 4.5,\n" +
            "            \"anleitung\": {\n" +
            "                \"anleitung\": \"Einfach machen\"\n" +
            "            },\n" +
            "            \"speisekarte\": [\n" +
            "            ]\n" +
            "        }";

    private final static String SERIALIZED_DOENER_WITH_SUMMER_MENU = " {\n" +
            "            \"id\": 0,\n" +
            "            \"name\": \"Döner\",\n" +
            "            \"preis\": 4.5,\n" +
            "            \"anleitung\": {\n" +
            "                \"anleitung\": \"Einfach machen\"\n" +
            "            },\n" +
            "            \"speisekarte\": [\n" +
            "                {\n" +
            "                   \"name\": \"sommer\",\n" +
            "                   \"gueltig_von\": 1525125600000,\n" +
            "                   \"gueltig_bis\": 1538344800000\n" +
            "                }\n" +
            "             ]\n" +
            "        }";

    @Autowired
    private StdSerializer serializer;

    @Test
    public void testGerichtSerializerWithGerichtNamePriceAndHowTo() throws JsonProcessingException {
        Gericht gericht = new Gericht("Döner", 4.5, new Zubereitungsanleitung("Einfach machen"));

        ObjectMapper mapper = new ObjectMapper();

        SimpleModule module = new SimpleModule();
        module.addSerializer(Gericht.class, serializer);
        mapper.registerModule(module);

        String serialized = mapper.writeValueAsString(gericht);

        assertThat(serialized).isEqualToIgnoringWhitespace(SERIALIZED_DOENER.replace("\n", ""));
    }

    @Test
    public void testGerichtSerializerWithGerichtNamePriceHowToAndMenu() throws JsonProcessingException {

        Speisekarte sommer = new Speisekarte("sommer", new GregorianCalendar(2018,Calendar.MAY,1).getTime(), new GregorianCalendar(2018,Calendar.OCTOBER,1).getTime());
        Gericht gericht = new Gericht("Döner", 4.5, new Zubereitungsanleitung("Einfach machen"));
        gericht.addSpeisekarte(sommer);

        ObjectMapper mapper = new ObjectMapper();

        SimpleModule module = new SimpleModule();
        module.addSerializer(Gericht.class, serializer);
        mapper.registerModule(module);

        String serialized = mapper.writeValueAsString(gericht);

        assertThat(serialized).isEqualToIgnoringWhitespace(SERIALIZED_DOENER_WITH_SUMMER_MENU.replace("\n", ""));
    }
}
