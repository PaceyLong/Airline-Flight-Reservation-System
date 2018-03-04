package Reservations;

import src.TTARouteNetwork.Flight;

import java.util.ArrayList;

/**
 * @author Joshua Ehling (Refactor), Peishi (Original)
 * Class used to represent Itineraries. Itineraries contain multiple flights.
 * They summarize the information for a series of flights into the Origin and
 * final destination.
 * Also contain price for entire trip.
 */
public class Itinerary{
    /* attributes */
    private ArrayList<Flight> flightList = new ArrayList<>();
    private int totalPrice;

    /**
     * @author Josh Ehling
     * default constructor
     */
    public Itinerary() { totalPrice = 0; }

    /**
     * Calculates total airfare for the Reservations.
     * create a flight list.
     * increase number of flight in itinerary.
     * @param fl
     */
    public void addFlight(src.TTARouteNetwork.Flight fl){
        flightList.add(fl);
        totalPrice += fl.getAirfare();
    }

    /**
     * totalPrice getter
     * @return
     */
    public int getTotalPrice(){
        return totalPrice;
    }

    /**
     * number of flight in itinerary getter
     * @return
     */
    public int getNumberFlightIn(){
        return flightList.size();
    }

    /**
     * Returns the 3-letter code representing the origin airport of the first flight in the itinerary.
     * @return
     */
    public String getOrigin(){
        return flightList.get(0).getOriginAirport();
    }

    /**
     * Returns the 3-letter code representing the destination airport of the last flight in the itinerary.
     * @return
     */
    public String getDestination(){
        return flightList.get(flightList.size()-1).getDestinationAirport();
    }

    /**
     * @author Joshua Ehling
     * toString(): total price, number of flight in itinerary, flight number, origin, departure time,
     * destination, arrival time.
     * @return
     */
    @Override
    public String toString(){
        String printout = getTotalPrice() + "," + getNumberFlightIn() + ",";
        for(int idx = 0; idx < flightList.size(); idx++){
            printout += flightList.get(idx).toString();
            if (idx + 1 < flightList.size()) printout += ",";
        }
        return printout;
    }

    /**
     * @author Joshua Ehling
     * Enforce unique Itineraries for reservations: Origin, Destination must be unique!
     * @param object - object to be checked for equality
     * @return true if objects are the same, false otherwise
     */
    @Override
    public boolean equals(Object object){
        // test for object type. Fail if not Itinerary
        if (object instanceof Itinerary){
            Itinerary itinerary = (Itinerary) object;
            // verify if origin and destination are unique between itineraries
            return (this.getOrigin().equals(itinerary.getOrigin())) && (this.getDestination().equals(itinerary.getDestination()));
        }
        return false;
    }
}
