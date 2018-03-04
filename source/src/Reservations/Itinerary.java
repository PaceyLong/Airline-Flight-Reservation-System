package Reservations;

import src.TTARouteNetwork.Flight;

import java.util.ArrayList;
import java.util.List;

public class Itinerary{

    private List<src.TTARouteNetwork.Flight> flightList = new ArrayList<>();
    private int totalPrice = 0;
    private int numberFlightIn = 0;

    public Itinerary(int totalPrice, int numberFlightIn, Flight fl) {
        this.totalPrice = totalPrice;
        this.numberFlightIn = numberFlightIn;
    }

    /**
     * Calculates total airfare for the Reservations.
     * create a flight list.
     * increase number of flight in itinerary.
     * @param fl
     */
    public void addFlight(src.TTARouteNetwork.Flight fl){
        flightList.add(fl);
        totalPrice += fl.getAirfare();
        numberFlightIn += 1;
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
        return numberFlightIn;
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
            if (idx + 1 != flightList.size()) printout += ",";
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
