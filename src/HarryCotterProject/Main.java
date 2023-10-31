package HarryCotterProject;
import java.util.Scanner;
public class Main {

    Ejer ejer = new Ejer("Harry", 1);
    Personale personale = new Personale("Harriet", 2);
    MenuRunner menurunner = new MenuRunner(ejer, personale);

    private int valg = -1;
    private boolean betalt;

    public static void main(String[] args) {
        new Main().run();
    }

    public void run() {
        menurunner.eksekverMenu();


//TODO Hvis man vælger numre uden kode går den til hovedmenu - Det skal den kun ved nr 9.
    }
    // TODO Sikre text tilføjes til fil.
    //TODO Tilføje til fil - aftaleID og betalt: T/F


}
