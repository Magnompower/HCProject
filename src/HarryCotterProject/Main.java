package HarryCotterProject;

public class Main {

    Ejer ejer = new Ejer("Harry", 1);
    Personale personale = new Personale("Harriet", 2);  
    Menumager menumager = new Menumager(ejer, personale);
    private Kalender kalender = new Kalender();



    public static void main(String[] args) {
        new Main().run(); //
    }

    public void run() {
        kalender.opdaterArraylistFraFil();
        menumager.eksekverMenu(kalender);
        kalender.overskrivFilFraArraylist();
    }
}
