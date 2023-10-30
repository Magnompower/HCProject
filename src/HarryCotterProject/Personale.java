package HarryCotterProject;

import java.io.*;
import java.util.Scanner;

public class Personale extends Person {
    int kassebeholdning = 0;
    int betalingssum = 0;

    Scanner filescanner = null;
    Scanner scanner = new Scanner(System.in);
    File kalenderFil = new File("src\\HarryCotterProject\\Kalender.txt");
    boolean betalt;

    public Personale(String navn, int id) {
        super(navn, id);
    }

    public void opretAftale() {
        PrintStream ps = null;
        try {
            ps = new PrintStream(new FileOutputStream(kalenderFil, true));
            System.out.println("Skriv kundeID:");
            int kundeID = scanner.nextInt();

            System.out.println("Skriv pris:");
            int pris = scanner.nextInt();
            scanner.nextLine(); //SCANNERBUG

            System.out.println("Skriv dato([DD-MM-ÅÅÅÅ]):");
            String dato = scanner.nextLine();
            System.out.println("Skriv tidspunkt( [TT:MM]):");
            String tidspunkt = scanner.nextLine();

            Aftale aftale = new Aftale(kundeID, pris, dato, tidspunkt, betalt);
            ps.println("KundeID: " + kundeID + " Pris: " + pris + ",- Dato: " + dato + " Klokken: " + tidspunkt);
            System.out.println("Aftalen er gemt og ser sådan ud:");
            System.out.println("KundeID: " + kundeID + " Pris: " + pris + ",- Dato: " + dato + " Klokken: " + tidspunkt);


        } catch (FileNotFoundException e) {
            System.out.println("Filen blev ikke fundet. Prøv igen");
        } catch (Exception e) {
            System.out.println("Der skete en fejl. Kontakt en udvikler.");
        }
        ps.close();
        vaelgMenu();
    }

    public void sletAftale() {
        System.out.println("Vælg hvilken linje du vil slette.");
        System.out.println(kalenderFil);

        int idSlettes = -1;
        int linje = scanner.nextInt();

        StringBuilder indhold = new StringBuilder();
        try {
            PrintWriter tekstTilFil = new PrintWriter(new FileWriter(kalenderFil));

            BufferedReader tekst = new BufferedReader(new FileReader(kalenderFil));
            while (filescanner.hasNextLine()) {
                if (linje != idSlettes) {
//                    tekstTilFil.println(linje);
                }
                linje++;

            }

            filescanner = new Scanner(kalenderFil);
            System.out.println(kalenderFil);
        } catch (Exception e) {
            System.out.println("Fejl opstod. Find en mur.");
        }
        vaelgMenu();
    }

    public void visKalender() {
        System.out.println("Dette er kalenderen:");
        try {
            filescanner = new Scanner(kalenderFil);
            while (filescanner.hasNextLine()) {
                String s = filescanner.nextLine();
                System.out.println(s);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Filen blev ikke fundet. Prøv igen");
        } catch (Exception e) {
            System.out.println("Der opstod en fejl. Kontakt en udvikler.");
        }
        filescanner.close();
    }

    public void modtagBetaling() {
        System.out.println("Vælg aftale:");
        kassebeholdning = kassebeholdning + betalingssum;
        betalt = true;
        vaelgMenu();
    }
}
