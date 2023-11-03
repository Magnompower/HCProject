package HarryCotterProject;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Scanner;
import java.util.ArrayList;

public class Personale extends Person {
    private Scanner filescanner = null;
    Scanner scanner = new Scanner(System.in);

    public Personale(String navn, int id) {
        super(navn, id);
    }


    public void opretAftale(Kalender kalender) {
        try {
            Aftale aftale = new Aftale(vaelgDatoForAftale(kalender), indtastTidspunkt(), opretKunde(), 0, "mangler");

            kalender.gemIDokument(aftale);
            kalender.opdaterArraylistFraFil();
            printConfirmation(aftale);

        } catch (Exception e) {
            System.out.println("Noget gik galt. Prøv forfra, ellers du for den bagfra.");
        }
    }

    private LocalDate vaelgDatoForAftale(Kalender kalender) {

        System.out.println("Vælg Dato for aftale:");

        LocalDate valgtDato = kalender.indtastDato();
        ArrayList<Aftale> aftaler = kalender.getAftaler();
        Aftale[] tider = (new Aftale[7]);

        for (Aftale a : aftaler) {
            LocalTime tidspunkt = a.getTidspunkt();
            int time = tidspunkt.getHour();
            if (a.getDato().isEqual(valgtDato)) {
                if (time >= 10 && time <= 17) {
                    tider[time - 10] = a;
                }
            }
        }

        for (int i = 0; i < tider.length; i++) {
            if (tider[i] != null) {
                System.out.println(tider[i].getKunde().getKundenavn() + ": " +
                        tider[i].getTidspunkt() + "-" + tider[i].getTidspunkt().plusHours(1) + ".");
            } else {
                System.out.println("Frit tidsrum");
            }
        }

        return valgtDato;
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
            }
            //TODO Hvis man vælger numre uden kode går den til hovedmenu - Det skal den kun ved nr 9.
            /*else if (kundeNavn.equals("9")) {
                Menumager menumager = new Menumager(Ejer,Personale);
                Menumager.afslutFraAftale(kalender);
                menumager.eksekverMenu(); // Skal gå til hovedmenu på 9 Variabel der går frem og tilbage?
            } else if (kundeNavn.equals("0")) {
                retunerValgNull();
                menumager.afslutProgram(menumager); // Skal aflsutte på 0
            }*/

        }

        return kundeNavn;
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


    private LocalTime indtastTidspunkt() { //Skal rykkes til kalender
        String tidsformat = "^(\\d{2}:\\d{2})$";
        DateTimeFormatter tidsFormaterer = DateTimeFormatter.ofPattern("HH:mm");

// TODO MAN MÅ ALDRIG SKRIVE WHILE(TRUE)
        // RETURN SKAL OGSÅ KOMME UDENFRO TRY
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
        System.out.println("Dato: " + a.getDato() + " Tidspunkt: " + a.getTidspunkt()
                + " Navn: " + a.getKunde().getKundenavn() + " Tlf: " + a.getKunde().getKundeTlfNr() +
                " Pris: " + a.getPris() + ",- Betaling: " + a.getBetaling());
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



