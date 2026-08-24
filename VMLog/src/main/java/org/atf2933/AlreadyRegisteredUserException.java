package org.atf2933;

public class AlreadyRegisteredUserException extends Exception {
    public AlreadyRegisteredUserException(String message){
        super(message);
    }
}
