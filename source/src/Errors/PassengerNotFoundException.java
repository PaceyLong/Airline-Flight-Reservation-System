package Errors;

/**
 * @author Joshua Ehling
 * Exception thrown when system tries to perform an operation but cannot locate the specified Passenger.
 */
public class PassengerNotFoundException extends Exception{
    public PassengerNotFoundException(){
        super("error, passenger not found");
    }
}
