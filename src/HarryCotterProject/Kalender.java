package HarryCotterProject;

import java.util.ArrayList;

public class Kalender {

    ArrayList<Aftale> kalender;

    public void addAftalteTilKalender(Aftale a) {
        int index = 0;
        while (index < kalender.size() && a.dato.isAfter(kalender.get(index).dato)) index++;
        kalender.add(index, a);
    }
}
