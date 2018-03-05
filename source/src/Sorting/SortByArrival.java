package Sorting;
import Reservations.Itinerary;

import java.util.Comparator;

/**
 * @author Nick Asermily, nja9798@g.rit.edu
 */
public class SortByArrival implements Comparator<Itinerary>{

    public int compare(Itinerary itinerary0, Itinerary itinerary1){
        return createTime(itinerary0.getArrivalTime()) - createTime(itinerary1.getArrivalTime());
    }

    /**
     * Creates an integer relational to the time given in 24-hour form. Used to compare later for sorting.
     * @param s - string of time
     * @return - int in 24hour time
     */
    public int createTime(String s){
        int time = 0;
        String[] split = s.split(":");
        int hour = Integer.parseInt(split[0]);
        int min = Integer.parseInt(split[1].substring(0, split[1].length() - 1));
        String amOrPm = split[1].substring(split[1].length() - 1);
        if(hour == 12 && amOrPm.equals("a")){
            time = min;
        }else if(amOrPm.equals("a")){
            time = (hour*100)+min;
        }else if(hour == 12 && amOrPm.equals("p")){
            time = (hour*100)+min;
        }else if(amOrPm.equals("p")){
            time = ((hour+12)*100)+min;
        }else{
            System.out.println("ERROR in time");
        }

        return time;
    }
}
