package Errors;

public class NullClientException extends Exception {
    public NullClientException(){
        super("ERROR: Client not currently connected. Please enter 'connect' to use the AFRS System");
    }
}
