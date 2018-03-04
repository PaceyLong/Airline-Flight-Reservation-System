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
        // Assure passenger exists within DB
        recordPassenger(passengerName);
        // append itinerary to Passenger's reservations list. ERROR if not unique
        appendItinerary(reservationsHashMap.get(passengerName), itinerary);
    }

    /**
     * Checks if a passenger exists. If the don't, creates entry within database.
     * If they do, does nothing.
     */
    private void recordPassenger(String passengerName){
        // if passenger doesn't already exist within DB, add to DB.
        if(!reservationsHashMap.containsKey(passengerName)){
            reservationsHashMap.put(passengerName, new ArrayList<Itinerary>());
        }
    }

    private void appendItinerary(ArrayList<Itinerary> reservations, Itinerary itinerary) throws Error{
        // verify unique itinerary. If not, throw an error
        if(reservations.contains(itinerary)) {
            throw Error;
        }

    }
}
