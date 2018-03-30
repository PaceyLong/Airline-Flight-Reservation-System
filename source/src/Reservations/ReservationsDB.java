package Reservations;

import Errors.DuplicateReservationException;
import Errors.PassengerNotFoundException;
import Errors.ReservationNotFoundException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

/**
 * @author Joshua Ehling
 * Enforces Singleton Patte+rn
 * Database used to map Passenger Name --> Reservations (Itineraries)
 */
public class ReservationsDB {
    /* attributes */
    private static final String SUCCESSFUL_DELETE_MSG = "delete,successful";
    private static final String SUCCESSFUL_RESERVATION_MSG = "reserve,successful";

    /* Most recent set of matching itineraries from FlightInfo request */
    private ArrayList<Itinerary> currMatchingItineraries;

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
     * Accessor for size of recently queried matching itineraries for FlightInfo
     * @return size of currMatchingItineraries
     */
    public int getCurrMatchingItinerariesSize(){
        return currMatchingItineraries.size();
    }

    /**
     * Called by parser to write reservations to reservations.csv
     */
    public ArrayList<Itinerary> parserGetReservations(String passengerName){
        return reservationsHashMap.get(passengerName);
    }

    public Set<String> parserGetReservationKeySet(){
        return reservationsHashMap.keySet();
    }

    /**
     * Private constructor to enforce Singleton Pattern
     */
    private ReservationsDB(){
        reservationsHashMap = new HashMap<>();
        currMatchingItineraries = new ArrayList<>();
    }

    /**
     * Accessor. Retrieves requested itinerary from currMatchingItineraries
     * @param id - index in arraylist
     * @return Itinerary object
     */
    public Itinerary getCurrItineraryWithID(int id){
        return currMatchingItineraries.get(id);
    }

    /**
     * Mutator. Used to update most recent list of queried matching itineraries from FlightInfo
     * @param returnedItineraries - most recent list of matching itineraries
     */
    public void setCurrMatchingItineraries(ArrayList<Itinerary> returnedItineraries){
        currMatchingItineraries = returnedItineraries;
    }

    /**
     * User reserves a itinerary and adds it to their list of reservations
     * Checks if itinerary is unique (origin, destination) before adding.
     *      If not unique, throws ERROR
     * @param passengerName key value for hashmap
     * @param itinerary - itinerary being reserved
     */
    public void reserveItinerary(Itinerary itinerary, String passengerName){
        // Assure passenger exists within DB
        recordPassenger(passengerName);
        // append itinerary to Passenger's reservations list. ERROR if not unique
        try{
            appendItinerary(passengerName, itinerary);
            // TODO rework to send up properly
//            System.out.println(SUCCESSFUL_RESERVATION_MSG);
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
//            System.out.println(SUCCESSFUL_DELETE_MSG); // TODO Refactor error handling
        } catch (Exception error){
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
    private void verifyReservation(String passengerName, Itinerary itinerary) throws PassengerNotFoundException, ReservationNotFoundException{
        // throw error if Passenger doesn't exist within the database
        if(!reservationsHashMap.containsKey(passengerName)) throw new PassengerNotFoundException();
        // throw error if Passenger's reservation doesn't exist within their current list of reserved itineraries
        if (!reservationsHashMap.get(passengerName).contains(itinerary)) throw new ReservationNotFoundException();
    }

    /**
     * Default printout of all reservations under a given Passenger
     * @param passengerName - passenger
     */
    public String retrieveReservations(String passengerName){
        ArrayList<Itinerary> itineraries = reservationsHashMap.get(passengerName);
        return constructMessage(itineraries);
    }

    /**
     * Overloaded method to catch scenario of provided origin parameter
     * @param passengerName - passenger
     * @param origin - origin airport
     */
    public String retrieveReservations(String passengerName, String origin){
        // instantiate list of itineraries that have the given Origin
        ArrayList<Itinerary> matchingItineraries = new ArrayList<>();
        // compile list of itineraries that contain the desired value
        for (Itinerary itinerary : reservationsHashMap.get(passengerName)){
            if(itinerary.getOrigin().equals(origin)) matchingItineraries.add(itinerary);
        }
        return constructMessage(matchingItineraries);
    }

    /**
     * Overloaded method to catch provided origin and destination parameters
     * @param passengerName - passenger
     * @param origin - origin airport
     * @param destination - destination airport
     */
    public String retrieveReservations(String passengerName, String origin, String destination){
        // instantiate list of itineraries that have given origin and destination
        ArrayList<Itinerary> matchingItineraries = getMatchingItineraries(passengerName, origin, destination);
        // begin crafting message
        return constructMessage(matchingItineraries);
    }

    /**
     * Helper method. Retrieves arraylist of matching itineraries based on input
     * @param passengerName - passenger name
     * @param origin - airport origin
     * @param destination - airport destination
     * @return ArrayList<Itinerary> of matching itineraries
     */
    private ArrayList<Itinerary> getMatchingItineraries(String passengerName, String origin, String destination){
        ArrayList<Itinerary> matchingItineraries = new ArrayList<>();
        // compile list of itineraries that contain desired origin and destination values
        for (Itinerary itinerary : reservationsHashMap.get(passengerName)){
            if(itinerary.getOrigin().equals(origin) && itinerary.getDestination().equals(destination)){
                matchingItineraries.add(itinerary);
            }
        }
        return matchingItineraries;
    }

    /**
     * Accessor for itinerary from list of reservations that matches given input
     * @param passengerName - passenger
     * @param origin - origin airport
     * @param destination - destination airport
     * @return
     */
    public Itinerary getItinerary(String passengerName, String origin, String destination){
        // return first item in matching itineraries list (should be only one)
        return getMatchingItineraries(passengerName, origin, destination).get(0);
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
