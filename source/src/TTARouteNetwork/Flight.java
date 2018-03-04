package src.TTARouteNetwork;

public class Flight {
    private String originAirport;
    private String destinationAirport;
    private String depatureTime;
    private String arrivalTime;
    private int flightNumber;
    private int airfare;

    /**
     * flight constructor
     *
     * @param originAirport origin airport
     * @param destinationAirport destination airport
     * @param depatureTime departure time
     * @param arrivalTime arrival time
     * @param flightNumber flight number
     * @param airfare airfare
     */
    public Flight(String originAirport, String destinationAirport,
                  String depatureTime, String arrivalTime,
                  int flightNumber, int airfare) {
        this.originAirport = originAirport;
        this.destinationAirport = destinationAirport;
        this.depatureTime = depatureTime;
        this.arrivalTime = arrivalTime;
        this.flightNumber = flightNumber;
        this.airfare = airfare;
    }

    /**
     * origin air port getter
     * @return
     */
    public String getOriginAirport() {
        return originAirport;
    }

    /**
     * destination airport getter
     * @return
     */
    public String getDestinationAirport() {
        return destinationAirport;
    }

    /**
     * departure time getter
     * @return
     */
    public String getDepatureTime() {
        return depatureTime;
    }

    /**
     * arrival time getter
     * @return
     */
    public String getArrivalTime() {
        return arrivalTime;
    }

    /**
     * flight number getter
     * @return
     */
    public int getFlightNumber() {
        return flightNumber;
    }

    /**
     * airfare getter
     * @return
     */
    public int getAirfare() {
        return airfare;
    }
}

