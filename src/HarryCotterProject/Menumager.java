package HarryCotterProject;

import java.util.Scanner;

public class Menumager {
    Ejer ejerHarry = new Ejer("Harry", 1);
    Personale personaleHarriet = new Personale("Harriet", 2);

    int personaleType = -1;
    int valg = -1;

    Scanner scanner = new Scanner(System.in);
Menu menuStart = new Menu("Hovedmenu:", punkterStartMenu());
    Menu menuEjer = new Menu("Ejermenu:", punkterEjerMenu());
    Menu menuPersonale = new Menu("Personalemenu:", punkterPersonaleMenu());


    public void vaelgMenu() {
        if (personaleType == 1) {
            vaelgFraEjerMenu();
        } else if (personaleType == 2) {
            vaelgFraPersonaleMenu();
        } else if (personaleType == 3) {
            vaelgFraStartMenu();
        }
    }

    private void vaelgFraPersonaleMenu() {
        menuPersonale.printMenu();
        int valg = scanner.nextInt();
        switch (valg) {
            case 0 -> afslutProgram();
            case 1 -> personaleHarriet.opretAftale();
            case 2 -> personaleHarriet.sletAftale();
            case 3 -> personaleHarriet.visKalender();
            case 4 -> personaleHarriet.modtagBetaling();
            case 9 -> visHovedmenu();
        }
    }

    public void vaelgFraEjerMenu() {
        menuEjer.printMenu();
        int valg = scanner.nextInt();
        switch (valg) {
            case 0 -> afslutProgram();
            case 1 -> ejerHarry.opretAftale();
            case 2 -> ejerHarry.sletAftale();
            case 3 -> ejerHarry.visKalender();
            case 4 -> ejerHarry.modtagBetaling();
            case 5 -> ejerHarry.visRegnskab();
            case 9 -> visHovedmenu();
        }

    }

    private void vaelgFraStartMenu() {
        menuStart.printMenu();
        personaleType = scanner.nextInt();
        switch (personaleType) {
            case 0 -> afslutProgram();
            case 1 -> vaelgFraEjerMenu();
            case 2 -> vaelgFraPersonaleMenu();
            case 3 -> ejerHarry.visRegnskab();
        } // TODO HVAD SKER DER HVIS ANDET END 1,2 eller 3?
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

    public void afslutProgram() {
        valg = 0;
    }

    public void visHovedmenu() {
        vaelgFraStartMenu();
    }
}
