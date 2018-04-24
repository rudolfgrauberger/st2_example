# Einleitung
Bei diesem Projekt handelt es sich initial um das Beispiel-Projekt, das Hr. Dr. Bente in dem Blog-Post [Aufsetzen von Spring Data JPA, mit Beispielprojekt](http://blogs.gm.fh-koeln.de/bente/2017/05/11/aufsetzen-von-spring-data-jpa-mit-beispielprojekt/) zur Verfügung gestellt hat.

Als erstes wurde ein Kompilefehler nach dem Öffnen mit IntelliJ in der pom.xml korrigiert. 

Anschließend werden in diesem Repository die Coding-Aufgaben für das ST2-Praktikum erledigt und versioniert.

# 'master'-Branch Status
|Build Status|
|:--:|
|[![Build Status](https://travis-ci.com/rudolfgrauberger/st2_example.svg?token=4DtPC3nnfRtf9x87E3hi&branch=master)](https://travis-ci.com/rudolfgrauberger/st2_example)|

# Kompilieren/Starten

1. InstalliJ starten
2. "Checkout from Version Control" und da Git auswählen
3. Bei URL die Projekturl einfügen (https://github.com/rudolfgrauberger/st2_example.git)
4. Bei Directory das Zielverzeichnis auf dem eigenen System auswählen
5. Auf Clone klicken
6. Die Abfrage "Would you like to create an InstelliJ IDEA project ..." mit Nein beantworten
7. Dann in IntelliJ auf File > Open gehen und das gewählte Zielverzeichnis auswählen
8. Projekt kompilieren/starten

# H2 Console öffnen
Die H2 Console (Weboberfläche zur Verwaltung der DB) kann man nach dem Starten des Projekts unter http://localhost:8080/h2-console erreichen. Die Infos, was man in der Login-Maske eingeben kann, bitte dem Blog-Post von Hr. Prof. Dr. Bente entnehmen (http://blogs.gm.fh-koeln.de/bente/2017/05/11/aufsetzen-von-spring-data-jpa-mit-beispielprojekt/)

# Nützliche Links
| Titel | Beschreibung |
| ----- | ------------ |
|[A beginner’s guide to JPA and Hibernate Cascade Types](https://vladmihalcea.com/a-beginners-guide-to-jpa-and-hibernate-cascade-types/) | Erklärt sehr gut die Beziehungen und welche Annotationen wo hin gehören, sowie was das dann in SQL beim Einfügen bedeutet.|
