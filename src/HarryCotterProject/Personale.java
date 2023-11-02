package HarryCotterProject;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Personale extends Person {
    private int kassebeholdning = 0;
    private int betalingssum = 0;
    private Scanner filescanner = null;
    Scanner scanner = new Scanner(System.in);
    int personaleType; // TODO SKAL DISSE BRUGES?
    int valg; //TODO SKAL DISSE BRUGES??

    public Personale(String navn, int id) {
        super(navn, id);
    }


    public void opretAftale(Kalender kalender) {
        System.out.println("Du kan altid taste 9 for at gå til hovedmenu og 0 for at afslutte programmet.\n\n");

        try {
            //vaelgDatoForAftale();
            Aftale aftale = new Aftale(opretKunde(), 0, kalender.indtastDato(), indtastTidspunkt(), "mangler");

            kalender.gemIDokument(aftale);
            kalender.opdaterArraylistFraFil();
            printConfirmation(aftale);


        } catch (Exception e) {
            System.out.println("Noget gik galt. Prøv forfra, ellers du for den bagfra.");
        }

    }

    private void vaelgDatoForAftale(Kalender kalender) {
        System.out.println("Vælg Dato for aftale:");
        kalender.indtastDato();
//        visLedigeTiderIKalender();
    }

    private Kunde opretKunde() {
        String kundeNavn = indtastNavn();
        int kundeTlfNr = indtastTlfNr();
        Kunde kunde = new Kunde(kundeNavn, kundeTlfNr);
        return kunde;
    }


    private String indtastNavn() {
        String kundeNavn = "";
        while (kundeNavn.isEmpty()) {
            System.out.println("Skriv kundens navn:");
            kundeNavn = scanner.nextLine().trim();
            if (kundeNavn.isEmpty()) {
                System.out.println("Feltet må ikke være tomt. Prøv igen.");
            } else if (kundeNavn.equals("9")) {
//                Menumager menumager = new Menumager(Ejer,Personale);
//                Menumager.afslutFraAftale(kalender);
//                menumager.eksekverMenu(); // Skal gå til hovedmenu på 9 Variabel der går frem og tilbage?
            } else if (kundeNavn.equals("0")) {
                retunerValgNull();
//                menumager.afslutProgram(menumager); // Skal aflsutte på 0
            }

        }

        return kundeNavn;
    }

    private int retunerValgNull() {
        valg = 0;
        return valg;
    }

    private int indtastTlfNr() {

        int kundeTlfNr = -1;

        boolean validIndput = false;
        while (!validIndput) {
            System.out.println("Skriv kundens telefonnummer:");
            try {

                kundeTlfNr = Integer.parseInt(scanner.nextLine().trim());
                validIndput = true;
            } catch (NumberFormatException e) {
                System.out.println("Ugyldigt telefonnummer. Prøv igen.");
                /*+ "9 for hovedmenu og 0 for at afslutte");
            }else if (kundeNavn.equals("9")) {
               menumager.eksekverMenu(); // Skal gå til hovedmenu/den menutype man er på 9
            } else if (kundeNavn.equals("0")) {
                menumager.afslutProgram(menumager); // Skal aflsutte på 0
            }*/

            }
        }
        return kundeTlfNr;
    }

    private LocalDate indtastDato() {
        String datoFormat = "^(\\d{4}-\\d{2}-\\d{2})$";
        DateTimeFormatter datoFormaterer = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        while (true) {
            System.out.println("Skriv dato([ÅÅÅÅ-MM-DD]):");
            String dato = scanner.nextLine().trim();

            if (dato.matches(datoFormat)) {
                try {
                    return LocalDate.parse(dato, datoFormaterer);
                } catch (Exception e) {
                    System.out.println("Ugyldig dato. Prøv igen.");
                }
            } else {
                System.out.println("Ugyldigt datoformat. Prøv igen");
            }
        }
    }

    private LocalTime indtastTidspunkt() {
        String tidsformat = "^(\\d{2}:\\d{2})$";
        DateTimeFormatter tidsFormaterer = DateTimeFormatter.ofPattern("HH:mm");

        while (true) {
            System.out.println("Skriv tidspunkt( [TT:MM]):");
            String tidspunkt = scanner.nextLine().trim();

            if (tidspunkt.matches(tidsformat)) {
                try {
                    return LocalTime.parse(tidspunkt, tidsFormaterer).withNano(0);
                } catch (Exception e) {
                    System.out.println("Ugyldigt tidspunkt. Prøv igen.");
                }
            } else {
                System.out.println("Ugyldigt tidsindput. Prøv igen");
            }

        }
    }

    private void printConfirmation(Aftale a) {
        System.out.println("\nDin aftale ser sådan ud:\n");
        System.out.println("Navn: " + a.getKunde().getKundenavn() + " Tlf: " + a.getKunde().getKundeTlfNr() + " Pris: " + a.getPris() + ",- Dato: " + a.getTidspunkt() + " Tidspunkt: " + a.getDato());
    }


    public void sletAftale(Kalender kalender) {
        System.out.println("Indtast det aftalenummer du vil slette.\n");
        visHeleKalenderen(kalender);
        int vaelgAftale = scanner.nextInt();
        int aftaleDerSlettes = vaelgAftale - 1;

        if (aftaleDerSlettes >= 0 && aftaleDerSlettes < kalender.getAftaler().size()) {
            kalender.getAftaler().remove(aftaleDerSlettes);
            System.out.println("Aftale " + vaelgAftale + " er nu slettet.");
            System.out.println("Dette er den nye kalender");
            visHeleKalenderen(kalender);
        } else {
            System.out.println("Der opstod en fejl. Prøv igen.");
        }


    }

    public void printIntroOgKalender(Kalender kalender) {
        System.out.println("Dette er kalenderen:\n");
        visHeleKalenderen(kalender);
    }


    public void visHeleKalenderen(Kalender kalender) {
        kalender.printHelekalender();
//        System.out.println("Tryk 1 hvis du vil se alle tider. Tryk 2 hvis du vil se tider en spicifik dag.");
        //Skal også kunne vise for en bestemt dag.
    }

    public void modtagBetaling(Kalender kalender) {

        String betaling = "betalt";
        int aftaleValg;
        System.out.println("Vælg aftalenummer:");
        visHeleKalenderen(kalender);
        aftaleValg = scanner.nextInt();

        kalender.getAftaler().get(aftaleValg - 1).setPris(indtastPris(kalender));
        kalender.getAftaler().get(aftaleValg - 1).setBetaling(betaling);
        System.out.println("Aftalenummer: " + aftaleValg + ". betalingsstatus: " +
                kalender.getAftaler().get(aftaleValg - 1).getBetaling());
    }

    private int indtastPris(Kalender kalender) { //Skal rykkes til kalender
        int pris = 0;

        boolean validIndput = false;
        while (!validIndput) {
            System.out.println("Skriv pris:");
            try {
                scanner.nextLine();//SCANNERBUG
                pris = Integer.parseInt(scanner.nextLine().trim());
                validIndput = true;
            } catch (NumberFormatException e) {
                System.out.println("Ugyldig pris. Prøv igen.");
            }
        }
        return pris;
    }
}



