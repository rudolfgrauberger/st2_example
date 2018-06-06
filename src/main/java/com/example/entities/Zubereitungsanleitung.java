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

  public Zubereitungsanleitung(String anleitung) {
      this.setAnleitung(anleitung);
   }

   @Override
   public String toString() {
      return anleitung;
   }
}
