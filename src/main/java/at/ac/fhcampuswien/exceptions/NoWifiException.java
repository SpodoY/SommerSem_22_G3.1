package at.ac.fhcampuswien.exceptions;

import at.ac.fhcampuswien.apiHandling.PopUp;

public class NoWifiException extends Exception{

    private final static String MESSAGE = "Please check your internet Connection";

    public NoWifiException(){
        super(MESSAGE);
        PopUp.createAlert(MESSAGE);
    }

    NoWifiException(String message){
        super(message);
    }



}
