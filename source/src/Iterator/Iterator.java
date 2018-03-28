package Iterator;

/**
 * @author Ethan Della Posta
 * Iterator interface to be used by Weather, Flights, Itineraries, and Reservations
 */
public interface Iterator {
    public boolean hasNext();
    public Object next();
}
