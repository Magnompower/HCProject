package HarryCotterProject;

import java.util.Scanner;

public class Menumager {
    Menu menuStart = new Menu("HOVEDMENU:", punkterStartMenu());
    Menu menuEjer = new Menu("EJERMENU:", punkterEjerMenu());
    Menu menuPersonale = new Menu("PERSONALEMENU:", punkterPersonaleMenu());
    Menu menuRevisor = new Menu("REVISORMENU:", punkterRevisorMenu());

    private int personaleType = -1;
    private int valg = -1;
    Ejer ejer = null;
    Personale personale = null;
    Scanner scanner = new Scanner(System.in);

    public Menumager(Ejer ejer, Personale personale) {
        this.ejer = ejer;
        this.personale = personale;
    }

    public void eksekverMenu(Kalender kalender) {
        vaelgFraHovedmenu(kalender);
        do {
            if (personaleType == 1) {
                vaelgFraEjerMenu(kalender);
            } else if (personaleType == 2) {
                vaelgFraPersonaleMenu(kalender);
            } else if (personaleType == 3) {
                vaelgFraRevisorMenu(kalender);
            } else {
                System.out.println("Ugyldigt valg. Prøv igen");
                eksekverMenu(kalender);
            }
        } while (valg != 0);
    }
//TODO GOD METODE HERUNDER???
    public  void afslutFraAftale(Kalender kalender) {
        valg = 0;
        eksekverMenu(kalender);
    }
    public void gaaTilHovedmenuFraAftale(Kalender kalender){
        vaelgFraHovedmenu(kalender);
    }

    public void vaelgFraHovedmenu(Kalender kalender) {
        menuStart.printMenu();
        personaleType = scanner.nextInt();
        if (personaleType == 0) {
            afslutProgram();
        }
    }

    public void vaelgFraEjerMenu(Kalender kalender) {
        menuEjer.printMenu();
        int valg = scanner.nextInt();
        switch (valg) {
            case 0 -> afslutProgram();
            case 1 -> ejer.opretAftale(kalender);
            case 2 -> ejer.sletAftale(kalender);
            case 3 -> ejer.printIntroOgKalender(kalender);
            case 4 -> ejer.modtagBetaling(kalender);
            case 5 -> ejer.visRegnskab();
            case 9 -> visHovedmenu(kalender);
            default -> {
                System.out.println("Ugyldigt valg. Prøv igen.");
            }
        }
    }

    private void vaelgFraPersonaleMenu(Kalender kalender) {
        menuPersonale.printMenu();
        int valg = scanner.nextInt();
        switch (valg) {
            case 0 -> afslutProgram();
            case 1 -> personale.opretAftale(kalender);
            case 2 -> personale.sletAftale(kalender);
            case 3 -> personale.printIntroOgKalender(kalender);
            case 4 -> personale.modtagBetaling(kalender);
            case 9 -> visHovedmenu(kalender);
            default -> {
                System.out.println("Ugyldigt valg. Prøv igen.");
            }
        }
    }

    public void vaelgFraRevisorMenu(Kalender kalender) {
        menuRevisor.printMenu();
        int valg = scanner.nextInt();
        switch (valg) {
            case 0 -> afslutProgram();
            case 1 -> ejer.visRegnskab();
            case 2 -> ejer.vaelgDatoRegnskab();
            case 9 -> visHovedmenu(kalender);
            default -> {
                System.out.println("Ugyldigt valg. Prøv igen.");
            }
        }

    }
//    TODO hvis invalid svar dør programmet.1

    public void afslutProgram() {
        valg = 0;
    }

    public void visHovedmenu(Kalender kalender) {
        vaelgFraHovedmenu(kalender);
    }

    public String[] punkterStartMenu() {
        return new String[]{"1. Ejer.", "2. Personale.", "3. Revisor.", "0. Afslut program"};
    }

    public String[] punkterEjerMenu() {
        return new String[]{"1. Opret aftale.", "2. Slet aftale.", "3. Vis kalender.", "4. Registrer betaling.",
                "5. Vis regnskab.", "9. Vis hovedmenu", "0. Afslut program"};
    }

    public String[] punkterPersonaleMenu() {
        return new String[]{"1. Opret aftale.", "2. Slet aftale.", "3. Vis kalender.", "4. Registrer betaling.",
                "9. Vis hovedmenu", "0. Afslut program"};
    }

    public String[] punkterRevisorMenu() {
        return new String[]{"1. Vis hele regnskabet.", "2. Se regnskab for bestemt dag", "9. Vis hovedmenu",
                "0. Afslut program"};
    }

}