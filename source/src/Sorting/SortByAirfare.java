package Sorting;

import Reservations.Itinerary;

import java.util.Comparator;

/**
 * @author Nick Asermily, nja9798@g.rit.edu
 */
public class SortByAirfare implements Comparator<Itinerary>{

    public int compare(Itinerary itinerary0, Itinerary itinerary1){
        return itinerary0.getTotalPrice() - itinerary1.getTotalPrice();
    }
}
