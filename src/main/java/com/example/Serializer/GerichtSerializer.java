package com.example.Serializer;

import com.example.entities.Gericht;
import com.example.entities.Speisekarte;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;

public class GerichtSerializer extends StdSerializer<Gericht> {

    public GerichtSerializer() {
        this(null);
    }

    public GerichtSerializer(Class<Gericht> t) {
        super(t);
    }

    @Override
    public void serialize(Gericht gericht, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeNumberField("id", gericht.getId());
        jsonGenerator.writeStringField("name", gericht.getName());
        jsonGenerator.writeNumberField("preis", gericht.getPreis());

        jsonGenerator.writeObjectFieldStart("anleitung");
        jsonGenerator.writeStringField("anleitung", gericht.getAnleitung() != null ? gericht.getAnleitung().getAnleitung() : null);
        jsonGenerator.writeEndObject();

        jsonGenerator.writeArrayFieldStart("speisekarte");

        gericht.getSpeisekarte().forEach(speisekarte -> { this.addSpeisekarteToGericht(speisekarte, jsonGenerator); });
        jsonGenerator.writeEndArray();
        jsonGenerator.writeEndObject();
    }

    private void addSpeisekarteToGericht(Speisekarte speisekarte, JsonGenerator generator) {
        try{
            generator.writeStartObject();
            generator.writeStringField("name", speisekarte.getName());
            generator.writeObjectField("gueltig_von", speisekarte.getGueltig_von());
            generator.writeObjectField("gueltig_bis", speisekarte.getGueltig_bis());
            generator.writeEndObject();
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
