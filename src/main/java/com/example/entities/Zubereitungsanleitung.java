package com.example.entities;

import javax.persistence.*;

@Embeddable
public class Zubereitungsanleitung {

   private String anleitung;

   public String getAnleitung() {
      return anleitung;
   }

   public void setAnleitung(String anleitung) {
      this.anleitung = anleitung;
   }

   protected Zubereitungsanleitung() {

   }
}
