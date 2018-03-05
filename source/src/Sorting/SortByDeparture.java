package Sorting;

import Reservations.Itinerary;

import java.sql.Time;
import java.util.Comparator;

/**
 * @author Nick Asermily, nja9798@g.rit.edu
 * Comparator for Flight Information requests that sort Itineraries by ascending departure time
 */
public class SortByDeparture implements Comparator<Itinerary>{

    /**
     * Compares Itineraries by ascending origin airports
     * @param itinerary0: First Itinerary
     * @param itinerary1: Second Itinerary
     * @return
     */
    public int compare(Itinerary itinerary0, Itinerary itinerary1){
        return createTime(itinerary0.getDepartureTime()).compareTo(createTime(itinerary1.getDepartureTime()));
    }

    public Time createTime(String s){
        Time t = null;
        String[] split = s.split(":");
        int hour = Integer.parseInt(split[0]);
        int min = Integer.parseInt(split[1].substring(0, split[1].length() - 1));
        String amOrPm = split[1].substring(split[1].length() - 1);
        if(hour == 12 && amOrPm.equals("a")){
            t = new Time(0, min, 0);
        }else if(amOrPm.equals("a")){
            t = new Time(hour, min, 0);
        }else if(hour == 12 && amOrPm.equals("p")){
            t = new Time(hour, min, 0);
        }else if(amOrPm.equals("p")){
            t = new Time(hour + 12, min, 0);
        }else{
            System.out.println("ERROR in time");
        }

        return t;
    }
}
