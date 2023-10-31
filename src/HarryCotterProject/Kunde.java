package HarryCotterProject;

public class Kunde {
    private String kundeNavn;
    private int kundeTlfNr;

    public Kunde(String kundeNavn, int kundeTlfNr) {
        this.kundeNavn = kundeNavn;
        this.kundeTlfNr = kundeTlfNr;
    }


    public String getKundenavn(){
        return kundeNavn;
    }

    public int getKundeTlfNr(){
        return kundeTlfNr;
    }

}

