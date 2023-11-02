package HarryCotterProject;

public class Main {

    Ejer ejer = new Ejer("Harry", 1);
    Personale personale = new Personale("Harriet", 2);
    Menumager menumager = new Menumager(ejer, personale);
    private Kalender kalender = new Kalender();


    private int valg = -1;
    private boolean betalt;

    public static void main(String[] args) {
        new Main().run(); //
    }

    public void run() {
        kalender.opdaterArraylistFraFil();
        menumager.eksekverMenu(kalender);
        kalender.overskrivFilFraArraylist();
    }



    // TODO Sikre text tilføjes til fil.
    //TODO Tilføje til fil - aftaleID og betalt: T/F
//TODO Hvis man vælger numre uden kode går den til hovedmenu - Det skal den kun ved nr 9.


}
