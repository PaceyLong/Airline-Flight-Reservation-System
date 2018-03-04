package Reservations;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author Joshua Ehling
 * Enforces Singleton Pattern
 * Database used to map Passenger Name --> Reservations (Itineraries)
 */
public class ReservationsDB {
    /* attributes */

    /* HashMap to store Passenger Names --> ArrayList of Itineraries */
    private HashMap<String, ArrayList<Itinerary>> reservationsHashMap;

    /* enforce Singleton Pattern */
    private static ReservationsDB instance;

    /* Singleton Pattern Instance Accessor */
    public static ReservationsDB getInstance(){
        if (instance == null) instance = new ReservationsDB();
        return instance;
    }

    /**
     * Private constructor to enforce Singleton Pattern
     */
    private ReservationsDB(){
        reservationsHashMap = new HashMap<>();
    }

    /**
     * User reserves a itinerary and adds it to their list of reservations
     * Checks if itinerary is unique (origin, destination) before adding.
     *      If not unique, throws ERROR
     * @param passengerName key value for hashmap
     * @param itinerary - itinerary being reserved
     */
    public void makeReservation(String passengerName, Itinerary itinerary){
        reservationsHashMap.put(passengerName, itinerary);
    }
}
