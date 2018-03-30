package Airports;

/**
 * Interface for the two Airport Info Services: AirportsDB and AirportsDBProxy
 * This allows the two to be treated interchangeably depending on which service has been specified
 */
public interface AirportInfoService {
    abstract Airport getAirport(String airportCode);
}
