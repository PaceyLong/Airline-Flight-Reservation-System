package Reservations;

import java.util.HashMap;
import java.util.Objects;

public class Reservation {
    private String passengerName;
    private Itinerary itinerary;
    private String origin;
    private String destination;
    private int numberOfItinerary = 0;
    HashMap<String, Itinerary> reservationList = new HashMap<String, Itinerary>();

    /**
     * reservation constructor
     *
     * @param passengerName passenger name
     * @param itinerary itinerary
     * @param origin origin airport
     * @param destination destination airport
     */
    public Reservation(String passengerName, Itinerary itinerary, String origin, String destination) {
        this.passengerName = passengerName;
        this.itinerary = itinerary;
        this.origin = origin;
        this.destination = destination;
    }

    /**
     * Retrieves the passenger's name who created the reservation.
     * @return passenger name
     */
    public String getPassengerName() {
        return passengerName;
    }

    /**
     * Retrieves the itinerary contained within the reservation.
     * @return itinerary the reservation was made for
     */
    public Itinerary getItinerary(){
        return itinerary;
    }

    /**
     * Reservation maintains the passenger's name, details of the reserved itinerary including:
     * the price for the intinerary, flight information
     * (flight number, origin and destination airports, departure and arrival times)
     * for each flight in the itinerary.
     *
     * increase number of itinerary;
     *
     * @param itinerarys
     */
    public void addItinerary(Itinerary itinerarys){
        reservationList.put(passengerName,itinerarys);
        numberOfItinerary += 1;
    }

    /**
     * Retrieves the origin airport for the reservation's itinerary.
     * @return 3-letter airport code for origin airport
     */
    public String getOrigin(){
        return itinerary.getOrigin();
    }

    /**
     * Retrieves the destination airport for the reservation's itinerary.
     * @return 3-letter airport code for destination airport
     */
    public String getDestination(){
        return itinerary.getDestination();
    }

    /**
     * Checks if a given passenger name, origin code, and destination code match the current reservation.
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Reservation)) return false;
        Reservation that = (Reservation) o;
        return Objects.equals(passengerName, that.getPassengerName()) &&
                Objects.equals(origin, that.getOrigin()) &&
                Objects.equals(destination, that.getDestination());
    }

    /**
     * toString(): # itineraries, itinerary.toString()
     * @return
     */
    @Override
    public String toString() {
        return numberOfItinerary + ",\n" + itinerary.toString();
    }
}