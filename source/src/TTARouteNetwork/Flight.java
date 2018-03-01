package source.src.TTARouteNetwork;

public class Flight {
    private String originAirport;
    private String destinationAirport;
    private String depatureTime;
    private String arrivalTime;
    private int flightNumber;
    private int airfare;

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

    public String getOriginAirport() {
        return originAirport;
    }

    public String getDestinationAirport() {
        return destinationAirport;
    }

    public String getDepatureTime() {
        return depatureTime;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public int getFlightNumber() {
        return flightNumber;
    }

    public int getAirfare() {
        return airfare;
    }
}

