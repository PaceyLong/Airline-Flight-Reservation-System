package source.src.Itinerary;

import source.src.TTARouteNetwork.Flight;

import java.util.ArrayList;
import java.util.List;

public class Itinerary{
    private List<Flight> flightlist = new ArrayList<>();
    private int airfairCostSum = 0;

    public Itinerary() {
    }

    public void addFlight(Flight flight){
        flightlist.add(flight);
        airfairCostSum += flight.getAirfare();
    }

}
