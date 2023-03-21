package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Pojisteni {

    List<Osoba> pojisteni = new ArrayList<>();
    Scanner sc = new Scanner(System.in, "UTF-8");

    public static boolean jeCislo(String str) {
        try {
            Long.parseLong(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private void menu() {

        System.out.println("Vyberte si akci: ");
        System.out.println("1 - Přidat nového pojištěného");
        System.out.println("2 - Vypsat všechny pojištěné");
        System.out.println("3 - Vyhledat pojištěného");
        System.out.println("4 - Konec");
    }


    private boolean overJmenoNeboPrijmeni(String v) {
        try {
            if ((!jeCislo(v)) && (v.length() < 20) && !v.isEmpty()) return true;
            else throw new Exception();
        } catch (Exception e) {
            System.out.println("Špatně zadaný vstup, zadejte znova");
            if (v.length() > 20) System.out.println("délka musí být maximálně 20 znaků");
            return false;
        }
    }

    private boolean overMenu(String vstup) {
        try {
            if (jeCislo(vstup) && ((Integer.parseInt(vstup) >= 1) && (Integer.parseInt(vstup) <= 4))) return false;
            else throw new Exception();

        } catch (Exception e) {
            System.out.println("Špatně zadaný příkaz, zadejte znova");
            return true;

        }
    }

    private void novyPojisteny() {
        String jmeno, prijmeni, telefon, vekst;
        int vek;
        System.out.print("Zadejte jméno pojištěného: ");
        jmeno = sc.nextLine();
        while (!overJmenoNeboPrijmeni(jmeno)) {
            jmeno = sc.nextLine();
        }
        System.out.print("Zadejte příjmení pojištěného: ");
        prijmeni = sc.nextLine();
        while (!overJmenoNeboPrijmeni(jmeno)) {
            jmeno = sc.nextLine();
        }
        System.out.print("Zadejte věk pojištěného: ");
        vekst = sc.nextLine();
        while (!jeCislo(vekst)) {
            System.out.println("Nebylo zadáno číslo, zadejte znova.");
            vekst = sc.nextLine();
        }
        vek = Integer.parseInt(vekst);

        System.out.print("Zadejte telefonní číslo pojištěného: ");
        telefon = sc.nextLine().replace(" ", "").replace("+", "");
        while ((!(telefon.length() > 8 && telefon.length() < 13)) || !(jeCislo(telefon))) {
            System.out.println("Nebylo zadáno telefonní číslo, zadejte znova.");
            System.out.println(telefon);
            System.out.println(telefon.length());
            telefon = sc.nextLine().replace(" ", "").replace("+", "");
        }
        pojisteni.add(new Osoba(jmeno, prijmeni, vek, telefon));
        System.out.println("Data byla uložena.");

    }

    private void vypisPojistene() {
        pojisteni.stream().forEach(System.out::print);
    }

    private void vyhledejPojisteneho() {
        String jmeno, prijmeni;
        System.out.print("Zadejte jméno pojištěného: ");
        jmeno = sc.nextLine().trim().toLowerCase();
        while (!overJmenoNeboPrijmeni(jmeno)) {
            jmeno = sc.nextLine();
        }
        System.out.print("Zadejte příjmení pojištěného: ");
        prijmeni = sc.nextLine().toLowerCase().trim();
        while (!overJmenoNeboPrijmeni(jmeno)) {
            jmeno = sc.nextLine();
        }
        String finalJmeno = jmeno;
        pojisteni.stream().filter(osoba -> osoba.getJmeno().equals(finalJmeno.toLowerCase().trim()) && osoba.getPrijmeni().equals(prijmeni.toLowerCase().trim()))
                .collect(Collectors.toList()).forEach(System.out::println);


    }

    public void zacni() {
        String vstup;
        boolean pokracovat = true;
        System.out.println("_____________________________");
        System.out.println("EVIDENCE POJIŠTĚNÝCH");
        System.out.println("_____________________________");
        menu();
        vstup = sc.nextLine();
        while (overMenu(vstup)) {
            vstup = sc.nextLine();
        }
        do {

            switch (Integer.parseInt(vstup)) {
                case 1:
                    novyPojisteny();
                    break;
                case 2:
                    vypisPojistene();
                    break;
                case 3:
                    vyhledejPojisteneho();
                    break;

            }

            System.out.println("Pokračujte dalším povelem...");
            if (Integer.parseInt(vstup) == 4) pokracovat = false;
            else {
                vstup = sc.nextLine();
                while (overMenu(vstup)) {
                    vstup = sc.nextLine();
                }
            }
        }
        while (pokracovat);
        System.out.println("Program ukončen.");
    }

}
