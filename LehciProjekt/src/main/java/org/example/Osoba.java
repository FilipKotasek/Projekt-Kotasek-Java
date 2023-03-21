package org.example;

import java.io.Console;

public class Osoba {
    private String jmeno,prijmeni,telefon;
    private int vek;
    String fmt="%1$4s %1$4s %2$10d %3$10s%n";
    Console console=System.console();
    public String getJmeno() {
        return jmeno;
    }

    public String getPrijmeni() {
        return prijmeni;
    }

    public Osoba(String jmeno, String prijmeni, int vek, String telefon) {
        this.jmeno = jmeno;
        this.prijmeni = prijmeni;
        this.vek = vek;
        this.telefon = telefon;
    }

    public String toString() {
        String s=String.format("%-17s %-17s %-5d %-17s\n",jmeno,prijmeni,vek,telefon);
       return s;

    }
}
