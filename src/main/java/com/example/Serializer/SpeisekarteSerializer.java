package com.example.Serializer;

import com.example.entities.Gericht;
import com.example.entities.Speisekarte;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;

public class SpeisekarteSerializer extends StdSerializer<Speisekarte> {

    public SpeisekarteSerializer() {
        this(null);
    }

    public SpeisekarteSerializer(Class<Speisekarte> s) {
        super(s);
    }

    @Override
    public void serialize(Speisekarte speisekarte, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("name", speisekarte.getName());
        jsonGenerator.writeObjectField("gueltig_von", speisekarte.getGueltig_von());
        jsonGenerator.writeObjectField("gueltig_bis", speisekarte.getGueltig_bis());
        jsonGenerator.writeArrayFieldStart("gerichte");
        speisekarte.getGerichte().forEach(gericht -> { this.addGerichtToSpeisekarte(gericht, jsonGenerator);});
        jsonGenerator.writeEndArray();
        jsonGenerator.writeEndObject();
    }

    private void addGerichtToSpeisekarte(Gericht gericht, JsonGenerator jsonGenerator) {
        try{
            jsonGenerator.writeStartObject();
            jsonGenerator.writeNumberField("id", gericht.getId());
            jsonGenerator.writeStringField("name", gericht.getName());
            jsonGenerator.writeNumberField("preis", gericht.getPreis());

            jsonGenerator.writeObjectFieldStart("anleitung");
            jsonGenerator.writeStringField("anleitung", gericht.getAnleitung() != null ? gericht.getAnleitung().getAnleitung() : null);
            jsonGenerator.writeEndObject();

            jsonGenerator.writeEndObject();
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
