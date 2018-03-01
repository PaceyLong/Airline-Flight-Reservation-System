package source.src.Itinerary;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Passenger {
    private String name;
    List<Reservation> reservation = new ArrayList<>();

    public Passenger(String name) {
        this.name = name;
    }

    public void addReservation(Reservation r){
        reservation.add(r);
    }
}

