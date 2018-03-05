package csv.parseTypes;

import Reservations.ReservationsDB;
import TTARouteNetwork.Flight;
import Reservations.Itinerary;

public class ReservationParse extends CSVParse {

    @Override
    public void useCSVLine(String[] strLineArr) {

        //create new Itinerary without any flights
        Itinerary itinerary = new Itinerary();

        //extract data from string array
        String passengerName = strLineArr[0];
        for(int x=1; x < strLineArr.length; x+=6){
            String airfare = strLineArr[x];
            String flightNumber = strLineArr[x+1];
            String originAirport = strLineArr[x+2];
            String departureTime = strLineArr[x+3];
            String destinationAirport = strLineArr[x+4];
            String arrivalTime = strLineArr[x+5];
            Flight flight = new Flight(originAirport, destinationAirport, departureTime, arrivalTime, Integer.parseInt(flightNumber), Integer.parseInt(airfare));
            itinerary.addFlight(flight);
        }
        ReservationsDB.getInstance().reserveItinerary(passengerName, itinerary);

    }
}
