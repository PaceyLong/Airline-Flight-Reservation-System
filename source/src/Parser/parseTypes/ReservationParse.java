package Parser.parseTypes;

import Reservations.ReservationsDB;
import TTARouteNetwork.Flight;
import Reservations.Itinerary;

/**
 * Adds each reservation which is present in the reservations csv file and adds it to the reservation hashmap
 */
public class ReservationParse extends CSVParse {

    private final static int PASSENGER_NAME = 0;
    private final static int AIRFARE = 1;
    private final static int FLIGHT_NUMBER = 2;
    private final static int ORIGIN_AIRPORT = 3;
    private final static int DEPARTURE_TIME = 4;
    private final static int DESTINATION_AIRPORT = 5;
    private final static int ARRIVAL_TIME = 6;


    @Override
    public void useCSVLine(String[] strLineArr) {

        //create new Itinerary without any flights
        Itinerary itinerary = new Itinerary();
        String passengerName = strLineArr[PASSENGER_NAME];

        //extract data from string array
        for(int x=0; x<strLineArr.length-1; x+=6) {
            String airfare = strLineArr[x + AIRFARE];
            String flightNumber = strLineArr[x + FLIGHT_NUMBER];
            String originAirport = strLineArr[x + ORIGIN_AIRPORT];
            String departureTime = strLineArr[x + DEPARTURE_TIME];
            String destinationAirport = strLineArr[x + DESTINATION_AIRPORT];
            String arrivalTime = strLineArr[x + ARRIVAL_TIME];
            Flight flight = new Flight(originAirport, destinationAirport, departureTime, arrivalTime, Integer.parseInt(flightNumber), Integer.parseInt(airfare));
            itinerary.addFlight(flight);
        }
        try{
            ReservationsDB.getInstance().reserveItinerary(itinerary, passengerName);
        } catch (Exception e){
            System.out.println(e.getMessage());
        }

    }
}
