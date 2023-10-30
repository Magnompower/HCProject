package HarryCotterProject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

//TODO sæt metoder i egne klasser frem for MAIN
public class Main {
    Scanner scanner = new Scanner(System.in);
    Scanner filescanner = null;

    Menu menuStart = new Menu("Hovedmenu:", punkterStartMenu());
    Menu menuEjer = new Menu("Ejermenu:", punkterEjerMenu());
    Menu menuPersonale = new Menu("Personalemenu:", punkterPersonaleMenu());

    File kalenderFil = new File("src\\HarryCotterProject\\Kalender.txt");
    File regnskabFil = new File("src\\HarryCotterProject\\Regnskab.txt");

    int valg = -1;
    int medarbejderType = -1;
    double kassebeholdning = 0;
    double betalingssum = 0;
    boolean betalt;

    public static void main(String[] args) {
        new Main().run();
    }

    private void run() {
        do {
            vaelgFraStartMenu();
        } while (!(valg == 0));
// TODO Hvis man vælger numre uden kode går den til hovedmenu - Det skal den kun ved nr 9.
    }

    private String[] punkterStartMenu() {
        return new String[]{"1. Ejer.", "2. Personale.", "3. Revisor.", "0. Afslut program"};
    }

    private String[] punkterEjerMenu() {
        return new String[]{"1. Opret aftale.", "2. Slet aftale.", "3. Vis kalender.", "4. Registrer betaling.",
                "5. Vis regnskab.", "9. Vis hovedmenu", "0. Afslut program"};
    }

    private String[] punkterPersonaleMenu() {
        return new String[]{"1. Opret aftale.", "2. Slet aftale.", "3. Vis kalender.", "4. Registrer betaling.",
                "9. Vis hovedmenu", "0. Afslut program",};
    }

    private void vaelgFraStartMenu() {
        menuStart.printMenu();
        medarbejderType = scanner.nextInt();
        switch (medarbejderType) {
            case 0 -> afslutProgram();
            case 1 -> vaelgFraEjerMenu();
            case 2 -> vaelgFraPersonaleMenu();
            case 3 -> visRegnskab();
        } // TODO HVAD SKER DER HVIS ANDET END 1,2 eller 3?
    }


    public void vaelgFraEjerMenu() {
        menuEjer.printMenu();
        int valg = scanner.nextInt();
        switch (valg) {
            case 0 -> afslutProgram();
            case 1 -> opretAftale();
            case 2 -> sletAftale();
            case 3 -> visKalender();
            case 4 -> modtagBetaling();
            case 5 -> visRegnskab();
            case 9 -> visHovedmenu();
        }

    }

    private void vaelgFraPersonaleMenu() {
        menuPersonale.printMenu();
        int valg = scanner.nextInt();
        switch (valg) {
            case 0 -> afslutProgram();
            case 1 -> opretAftale();
            case 2 -> sletAftale();
            case 3 -> visKalender();
            case 4 -> modtagBetaling();
            case 9 -> visHovedmenu();
        }
    }

    private void visHovedmenu() {
        vaelgFraStartMenu();
    }

    // TODO Sikre text tilføjes til fil.
    //TODO Oprette objekter i stedet for
    //TODO Tilføje til fil - aftaleID og betalt: T/F
    private void opretAftale() {
        PrintStream ps = null;
        try {
            ps = new PrintStream(new FileOutputStream(kalenderFil, true));
            System.out.println("Skriv kundeID:");
            int kundeID = scanner.nextInt();

            System.out.println("Skriv pris:");
            int pris = scanner.nextInt();
            scanner.nextLine(); //SCANNERBUG

            System.out.println("Skriv dato og tidspunkt([DD-MM-ÅÅÅÅ] [TT:MM]):");
            String tidspunkt = scanner.nextLine();

            //TODO Vi skal have seperet tidspunktet fra datoen, så det ikke står samlet fx 2 variabler.

            Aftale aftale = new Aftale(kundeID, pris, tidspunkt, betalt);
            ps.println("KundeID: " + kundeID + " Pris: " + pris + ",- Dato: " + tidspunkt);
            System.out.println("Aftalen er gemt og ser sådan ud:");
            System.out.println("KundeID: " + kundeID + " Pris: " + pris + ",- Dato: " + tidspunkt);


        } catch (FileNotFoundException e) {
            System.out.println("Filen blev ikke fundet. Prøv igen");
        } catch (Exception e) {
            System.out.println("Der skete en fejl. Kontakt en udvikler.");
        }
        ps.close();
        vaelgMenu();
    }

    private void vaelgMenu() {
        if (medarbejderType == 1) {
            vaelgFraEjerMenu();
        } else if (medarbejderType == 2) {
            vaelgFraPersonaleMenu();
        } else if (medarbejderType == 3) {
            vaelgFraStartMenu();
        }
    }

    private void sletAftale() {
        try {
            Scanner fileScanner = new Scanner(kalenderFil);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        vaelgMenu();
    }

    private void visKalender() {
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
        vaelgMenu();
    }

    private void modtagBetaling() {
        System.out.println("Vælg aftale:");
        kassebeholdning = kassebeholdning + betalingssum;
        betalt = true;
        vaelgMenu();
    }

    private void visRegnskab() {
        System.out.println("Dette er kassebeholdningen:");
        System.out.println(kassebeholdning + "\n");
        System.out.println("Dette er regnskabet:");
        try {
            filescanner = new Scanner(regnskabFil);
            while (filescanner.hasNextLine()) {
                String ss = filescanner.nextLine();
                System.out.println(ss);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Filen blev ikke fundet. Prøv igen");
        } catch (Exception e) {
            System.out.println("Der opstod en fejl. Kontakt en udvikler.");
        }
        filescanner.close();
        vaelgMenu();
    }

    private void afslutProgram() {
        valg = 0;
    }
}
