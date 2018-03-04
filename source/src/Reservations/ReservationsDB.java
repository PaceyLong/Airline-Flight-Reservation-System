package Reservations;

import Errors.DuplicateReservationException;
import Errors.ReservationNotFoundException;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author Joshua Ehling
 * Enforces Singleton Pattern
 * Database used to map Passenger Name --> Reservations (Itineraries)
 */
public class ReservationsDB {
    /* attributes */
    private String successfulDeleteMsg = "delete,successful";
    private String successfulReservationMsg = "reserve,successful";

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
    public void reserveItinerary(String passengerName, Itinerary itinerary){
        // Assure passenger exists within DB
        recordPassenger(passengerName);
        // append itinerary to Passenger's reservations list. ERROR if not unique
        try{
            appendItinerary(passengerName, itinerary);
            System.out.println(successfulReservationMsg);
        } catch (DuplicateReservationException error){
            System.out.println(error.getMessage());
        }
    }

    /**
     * Helper method. Checks if a passenger exists. If the don't, creates entry within database.
     * If they do, does nothing.
     */
    private void recordPassenger(String passengerName){
        // if passenger doesn't already exist within DB, add to DB.
        if(!reservationsHashMap.containsKey(passengerName)){
            reservationsHashMap.put(passengerName, new ArrayList<Itinerary>());
        }
    }

    /**
     * Helper method. Attempts to append provided itinerary to the reservationsDB Hashmap.
     * Checks if reservation is unique. Throws Duplicate Reservation Error when not.
     * @param passengerName - name of passenger to check
     * @param itinerary - itinerary trying to be appended to the reservations list
     * @throws DuplicateReservationException - thrown on non-unique itinerary reservation
     */
    private void appendItinerary(String passengerName, Itinerary itinerary) throws DuplicateReservationException{
        // retrieve ArrayList tied to passenger
        ArrayList<Itinerary> currReservations = reservationsHashMap.get(passengerName);
        // verify unique itinerary. If not, throw an error
        if(currReservations.contains(itinerary)) throw new DuplicateReservationException();
        currReservations.add(itinerary);
    }

    /**
     * Delete requested itinerary from current list of reserved itineraries
     * @param passengerName - name of passenger
     * @param itinerary - itinerary to be deleted
     */
    public void deleteItinerary(String passengerName, Itinerary itinerary){
        try{
            // verify the Passenger & their requested reservation exist
            verifyReservation(passengerName, itinerary);
            // delete reservation
            reservationsHashMap.get(passengerName).remove(itinerary);
            System.out.println(successfulDeleteMsg);
        } catch (ReservationNotFoundException error){
            System.out.println(error.getMessage());
        }
    }

    /**
     * Verifies that the Passenger, as well as their requested reservation, exist.
     * Throws error if either of the two exist
     * @param passengerName - name of passenger
     * @param itinerary - itinary to verify
     * @throws ReservationNotFoundException - reservation doesn't exist
     */
    private void verifyReservation(String passengerName, Itinerary itinerary) throws ReservationNotFoundException{
        // throw error if Passenger doesn't exist within the database
        if(!reservationsHashMap.containsKey(passengerName)) throw new ReservationNotFoundException();
        // throw error if Passenger's reservation doesn't exist within their current list of reserved itineraries
        if (!reservationsHashMap.get(passengerName).contains(itinerary)) throw new ReservationNotFoundException();
    }

    /**
     * Default printout of all reservations under a given Passenger
     * @param passengerName - passenger
     */
    public void retriveReservations(String passengerName){
        ArrayList<Itinerary> itineraries = reservationsHashMap.get(passengerName);
        System.out.println(constructMessage(itineraries));
    }

    /**
     * Overloaded method to catch scenario of provided origin parameter
     * @param passengerName - passenger
     * @param origin - origin airport
     */
    public void retrieveReservations(String passengerName, String origin){
        // instantiate list of itineraries that have the given Origin
        ArrayList<Itinerary> matchingItineraries = new ArrayList<>();
        // compile list of itineraries that contain the desired value
        for (Itinerary itinerary : reservationsHashMap.get(passengerName)){
            if(itinerary.getOrigin().equals(origin)) matchingItineraries.add(itinerary);
        }
        System.out.println(constructMessage(matchingItineraries));
    }

    /**
     * Overloaded method to catch provided origin and destination parameters
     * @param passengerName - passenger
     * @param origin - origin airport
     * @param destination - destination airport
     */
    public void retrieveReservation(String passengerName, String origin, String destination){
        // instantiate list of itineraries that have given origin and destination
        ArrayList<Itinerary> matchingItineraries = new ArrayList<>();
        // compile list of itineraries that contain desired origin and destination values
        for (Itinerary itinerary : reservationsHashMap.get(passengerName)){
            if(itinerary.getOrigin().equals(origin) && itinerary.getDestination().equals(destination)){
                matchingItineraries.add(itinerary);
            }
        }
        // begin crafting message
        System.out.println(constructMessage(matchingItineraries));
    }

    /**
     * Helper method. Constructs printout based on submitted list of Itineraries
     * Iterates through list and compiles toStrings() into final message.
     * @param matchingItineraries - submitted ArrayList of itineraries
     * @return - completed String
     */
    private String constructMessage(ArrayList<Itinerary> matchingItineraries){
        // begin crafting message
        int numReservations = matchingItineraries.size();
        String msg = "info," + numReservations + "\n";
        // print out matching itineraries
        for(int idx = 0; idx < numReservations; idx++){
            msg += idx + "," + matchingItineraries.get(idx).toString() + "\n";
        }
        return msg;
    }
}