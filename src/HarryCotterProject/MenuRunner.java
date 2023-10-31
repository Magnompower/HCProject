package HarryCotterProject;

import java.util.Scanner;

public class MenuRunner {
    Menu menuStart = new Menu("Hovedmenu:", punkterStartMenu());
    Menu menuEjer = new Menu("Ejermenu:", punkterEjerMenu());
    Menu menuPersonale = new Menu("Personalemenu:", punkterPersonaleMenu());
    Menu menuRevisor = new Menu("Revisormenu:", punkterRevisorMenu());

    private int personaleType = -1;
    private int valg = -1;
    Ejer ejer = null;
    Personale personale = null;
    Scanner scanner = new Scanner(System.in);

    public MenuRunner(Ejer ejer, Personale personale) {
        this.ejer = ejer;
        this.personale = personale;
    }

    public void eksekverMenu() {
        vaelgFraHovedmenu();
        do {
            if (personaleType == 1) {
                vaelgFraEjerMenu();
            } else if (personaleType == 2) {
                vaelgFraPersonaleMenu();
            } else if (personaleType == 3) {
                vaelgFraRevisorMenu(kalender);
            }
        } while (valg != 0);
    }

    public void vaelgFraHovedmenu() {
        menuStart.printMenu();
        personaleType = scanner.nextInt();
        if (personaleType == 0) {
            afslutProgram();
        }
    }

    public void vaelgFraEjerMenu() {
        menuEjer.printMenu();
        int valg = scanner.nextInt();
        switch (valg) {
            case 0 -> afslutProgram();
            case 1 -> ejer.opretAftale(kalender);
            case 2 -> ejer.sletAftale(kalender);
            case 3 -> ejer.visKalender(kalender);
            case 4 -> ejer.modtagBetaling(kalender);
            case 5 -> ejer.visRegnskab();
            case 9 -> visHovedmenu();
        }
    }

    private void vaelgFraPersonaleMenu() {
        menuPersonale.printMenu();
        int valg = scanner.nextInt();
        switch (valg) {
            case 0 -> afslutProgram();
            case 1 -> personale.opretAftale(kalender);
            case 2 -> personale.sletAftale(kalender);
            case 3 -> personale.visKalender(kalender);
            case 4 -> personale.modtagBetaling(kalender);
            case 9 -> visHovedmenu(kalender);
        }
    }

    public void vaelgFraRevisorMenu() {
        menuRevisor.printMenu();
        int valg = scanner.nextInt();
        switch (valg) {
            case 0 -> afslutProgram();
            case 1 -> ejer.visRegnskab();
            case 2 -> ejer.vaelgDatoRegnskab();
            case 9 -> visHovedmenu();
        }
    }
//    TODO hvis invalid svar dør programmet.1

        public void afslutProgram() {
            valg = 0;
        }

        public void visHovedmenu (Kalender kalender) {
            vaelgFraHovedmenu(kalender);
        }

        public String[] punkterStartMenu () {
            return new String[]{"1. Ejer.", "2. Personale.", "3. Revisor.", "0. Afslut program"};
        }

        public String[] punkterEjerMenu () {
            return new String[]{"1. Opret aftale.", "2. Slet aftale.", "3. Vis kalender.", "4. Registrer betaling.",
                    "5. Vis regnskab.", "9. Vis hovedmenu", "0. Afslut program"};
        }

        public String[] punkterPersonaleMenu () {
            return new String[]{"1. Opret aftale.", "2. Slet aftale.", "3. Vis kalender.", "4. Registrer betaling.",
                    "9. Vis hovedmenu", "0. Afslut program"};
        }

        public String[] punkterRevisorMenu () {
            return new String[]{"1. Vis hele regnskabet.", "2. Se regnskab for bestemt dag", "9. Vis hovedmenu",
                    "0. Afslut program"};
        }

    }



