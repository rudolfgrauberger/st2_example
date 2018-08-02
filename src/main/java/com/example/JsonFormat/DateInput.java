package com.example.JsonFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateInput
{
    private static DateFormat format = new SimpleDateFormat("dd-MM-yyyy", Locale.GERMAN);

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy")
    private Date datum;

    public DateInput(String datum) {
        Date date = new Date();

        try {
            date = format.parse(datum);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        this.datum = date;
    }

    public Date getDatum() {
        return datum;
    }
}
