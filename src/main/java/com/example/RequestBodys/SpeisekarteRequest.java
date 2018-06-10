package com.example.RequestBodys;

import com.example.JsonFormat.DateInput;

import java.util.Date;

public class SpeisekarteRequest {
    private String name;
    private DateInput datumVon;
    private DateInput datumBis;

    public SpeisekarteRequest() {
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DateInput getDatumVon() {
        return datumVon;
    }

    public void setDatumVon(DateInput datumVon) {
        this.datumVon = datumVon;
    }

    public DateInput getDatumBis() {
        return datumBis;
    }

    public void setDatumBis(DateInput datumBis) {
        this.datumBis = datumBis;
    }
}
