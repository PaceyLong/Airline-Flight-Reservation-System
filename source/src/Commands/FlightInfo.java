package Commands;

import Airports.AirportsDB;
import Reservations.Itinerary;
import Sorting.SortByAirfare;
import Sorting.SortByArrival;
import Sorting.SortByDeparture;
import TTARouteNetwork.Flight;
import TTARouteNetwork.FlightsDB;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.Collection;

/**
 * Command block that handles Flight Info Request
 *
 * Legend: {keyword literal}, [optional input], data
 * Flight information request: {info},origin,destination[,connections[,sort-order]];
 *      origin: three-letter code for the origin airport
 *      destination: three-letter code for the destination airport
 *      connections: # of connections allowed in the returned itineraries. [0, 1, 2]
 *          Default connection limit: 2; Omitted fields are empty strings between commas
 *          Sort-order is one of: departure, arrival, airfare.
 *              Sorting will always be from lowest to highest by nature ordering for the field.
 *              The default sort order, if this field is omitted, is by time of departure.
 */
public class FlightInfo implements Command{
    private String origin;
    private static final int ORIGIN_INDEX = 1;
    private String destination;
    private static final int DESTINATION_INDEX = 2;
    private int connections;
    private static final int CONNECTIONS_INDEX = 3;
    private String sortOrder;
    private Comparator sortOrderComparator;
    private static final int SORT_ORDER_INDEX = 4;
    private static final String DEFAULT_CONNECTION_LIMIT = "2";
    private static final String DEFAULT_SORT_ORDER = "departure";

    @Override
    public void execute(ArrayList<String> input) {
        origin = input.get(ORIGIN_INDEX);
        destination = input.get(DESTINATION_INDEX);
        if(input.size() >= 4) {
            connections = Integer.parseInt(input.get(CONNECTIONS_INDEX));
        }else{
            connections = Integer.parseInt(DEFAULT_CONNECTION_LIMIT);
        }
        if(input.size() >= 5) {
            sortOrder = input.get(SORT_ORDER_INDEX);
        }else{
            sortOrder = DEFAULT_SORT_ORDER;
        }

        //create comparator object based on string passed in
        if (sortOrder.equals("airfare")){
            this.sortOrderComparator = new SortByAirfare();
        }else if(sortOrder.equals("arrival")){
            this.sortOrderComparator = new SortByArrival();
        }else{
            this.sortOrderComparator = new SortByDeparture();
        }

        ArrayList<Itinerary> relevantItineraries =  getRelevantItineraries(origin, destination, sortOrderComparator, connections);
        if(relevantItineraries.isEmpty()){
            System.out.println("There are no flights/lists of flights that match your request.");
        }else{
            for( Itinerary itinerary : relevantItineraries){
                System.out.println(itinerary.toString());
            }
        }

    }

    /**
     *
     * Get all itineraries of either 0,1, or 2 flights connection airports a and b
     *
     * @param originAirport
     * @param destinationAirport
     * @param sortBy
     * @param limitConnections
     * @return relItineraries
     */
    public ArrayList getRelevantItineraries(String originAirport, String destinationAirport,
                                Comparator sortBy, int limitConnections){
        ArrayList<Itinerary> relItineraries = new ArrayList<>();
        Collection<Flight> allFlights =  FlightsDB.getInstance().getFlightsHashMap().values();

        //first add any direct flights
        relItineraries = addDirectFlights(relItineraries, originAirport, destinationAirport, allFlights);

        //if number of connections is 0, end here
        if(limitConnections == 0)
            return returnList(sortBy, relItineraries);

        //define necessary array lists for flights
        Collection<Flight> middleFlights = new ArrayList<>();
        Collection<Flight> originFlights = new ArrayList<>();
        Collection<Flight> destinationFlights = new ArrayList<>();
        for(Flight flight : allFlights) {
            boolean oa = originAirport.equals(flight.getOriginAirport());
            boolean da = destinationAirport.equals(flight.getDestinationAirport());
            if (oa) {
                originFlights.add(flight);
            }
            if (da) {
                destinationFlights.add(flight);
            }
            if(!oa || !da){
                middleFlights.add(flight);
            }
        }



        relItineraries = addDoubleFlights(relItineraries, originFlights, destinationFlights );

        //if number of connections is 1, end here
        if(limitConnections == 1)
            return returnList(sortBy, relItineraries);

        relItineraries = addTripleFlights(relItineraries, originFlights, destinationFlights, middleFlights);


        return relItineraries;

    }


    /**
     *
     * Ensure that aiports are not re-traveled to within a three flight itinerary
     *
     * @param oFlight
     * @param mFlight
     * @param dFlight
     */
    public boolean checkNotAirportRepeated(Flight oFlight, Flight mFlight, Flight dFlight){
        boolean oOriginmDestination = !oFlight.getOriginAirport().equals(mFlight.getDestinationAirport());
        boolean mOrigindDestination = !mFlight.getOriginAirport().equals(dFlight.getDestinationAirport());
        return oOriginmDestination && mOrigindDestination;
    }

    /**
     *
     * return and sort the list of objects to be printed out in the terminal (end the getRelevantItineraries method)
     *
     * @param sortBy
     * @param relItineraries
     * @return relItineraries
     */
    public ArrayList<Itinerary> returnList(Comparator sortBy, ArrayList<Itinerary> relItineraries){
        relItineraries.sort(sortBy);
        return relItineraries;
    }

    /**
     *
     * Add any direct flights (Itinerary with only one Flight) for the specified origin and destination airport
     *
     * @param relItineraries
     * @param originAirport
     * @param destinationAirport
     * @return relItineraries
     */
    public ArrayList<Itinerary> addDirectFlights(ArrayList<Itinerary> relItineraries, String originAirport, String destinationAirport, Collection<Flight> allFlights){
        for(Flight flight : allFlights){
            if(originAirport.equals(flight.getOriginAirport()) && destinationAirport.equals(flight.getDestinationAirport())){
                Itinerary itinerary = new Itinerary();
                itinerary.addFlight(flight);
                relItineraries.add(itinerary);
            }
        }
        return relItineraries;
    }

    /**
     *
     * Add any itineraries of size 2 for the specified origin and destination airport
     *
     * @param relItineraries
     * @param originFlights
     * @param destinationFlights
     * @return relItineraries
     */
    public ArrayList<Itinerary> addDoubleFlights(ArrayList<Itinerary> relItineraries,
                                                 Collection<Flight> originFlights, Collection<Flight> destinationFlights ){


        for(Flight oFlight : originFlights){
            for(Flight dFlight : destinationFlights){
                if(checkConnection(oFlight, dFlight) && checkValidConnection(oFlight, dFlight)){
                    Itinerary itinerary = new Itinerary();
                    itinerary.addFlight(oFlight);
                    itinerary.addFlight(dFlight);
                    relItineraries.add(itinerary);
                }
            }
        }

        return relItineraries;
    }

    /**
     *
     * Add any itineraries of size 3 for the specified origin and destination airport
     *
     * @param relItineraries
     * @param originFlights
     * @param destinationFlights
     * @param middleFlights
     * @return relItineraries
     */
    public ArrayList<Itinerary> addTripleFlights(ArrayList<Itinerary> relItineraries,
                                                 Collection<Flight> originFlights, Collection<Flight> destinationFlights,
                                                 Collection<Flight> middleFlights){
        for( Flight oFlight : originFlights){
            for (Flight dFlight : destinationFlights){
                //if they don't match up in the middle, try and find a middle man
                if(!checkConnection(oFlight, dFlight)){
                    for(Flight mFlight : middleFlights){
                        if(checkConnection(oFlight, mFlight) && checkConnection(mFlight, dFlight)){
                            if(checkValidConnection(oFlight,mFlight) && checkValidConnection(mFlight,dFlight ) &&
                                        checkNotAirportRepeated(oFlight,mFlight,dFlight)){
                                Itinerary itinerary = new Itinerary();
                                itinerary.addFlight(oFlight);
                                itinerary.addFlight(mFlight);
                                itinerary.addFlight(dFlight);
                                relItineraries.add(itinerary);
                            }
                        }
                    }
                }
            }
        }
        return relItineraries;
    }

    /**
     *
     * check if the two flights satisfy the connection time,delay time with respect to layover time requirement
     *
     * @param originFlight
     * @param destinationFlight
     */
    public boolean checkValidConnection(Flight originFlight, Flight destinationFlight ){
        Date originFlightDate = stringToDate(originFlight.getArrivalTime());
        Date destinationFlightDate = stringToDate(destinationFlight.getDepatureTime());

        String intermediateAirportCode = originFlight.getDestinationAirport();
        int delayTime = AirportsDB.getInstance().getAirport(intermediateAirportCode).getDelayTime();
        int minConnectionTime = AirportsDB.getInstance().getAirport(intermediateAirportCode).getMinConnectionTime();

        long layoverTime = Math.abs(destinationFlightDate.getTime() - originFlightDate.getTime()) / (60 * 1000);
        return  delayTime + layoverTime <= minConnectionTime;
    }

    /**
     *
     * check if the two flights are actually connecting flights (share a common middle airport)
     *
     * @param originFlight
     * @param destinationFlight
     */
    public boolean checkConnection(Flight originFlight, Flight destinationFlight ){
        String originFlightDestination = originFlight.getDestinationAirport();
        String destinationFlightOrigin = destinationFlight.getOriginAirport();
        return (originFlightDestination.equals(destinationFlightOrigin));
    }


    /**
     *
     * convert given string to a Date Object
     *
     * @param strDate
     */
    public Date stringToDate(String strDate){
        String timeType = String.valueOf(strDate.charAt(strDate.length()-1));
        DateFormat format = new SimpleDateFormat("h:mm a");
        try{
            return format.parse(strDate.substring(0,strDate.length()-2) + " " + timeType + "m");
        }catch(ParseException e){
            e.printStackTrace();
        }
        return null;
    }

}
