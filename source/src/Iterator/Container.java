package Iterator;

/**
 *
 * @author Ethan Della Posta
 * Interface to be implemented by all objects which need Iterators (Flights, Itineraries, Reservations, Weather)
 */
public interface Container {
    public Iterator getIterator();
}
